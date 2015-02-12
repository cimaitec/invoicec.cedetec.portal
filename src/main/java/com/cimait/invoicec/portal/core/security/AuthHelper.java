package com.cimait.invoicec.portal.core.security;

import java.text.ParseException;
import java.util.Calendar;

import net.minidev.json.JSONObject;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

@Component
public class AuthHelper {
	private static final String ISSUER = "com.cimait.invoicec.portal";
	
	@Autowired
	private GlobalConfig globalEmitter;
	
	public String createJsonWebToken(String userId, String ip, Long durationDays) throws JOSEException{
		JWSSigner signer = new MACSigner(globalEmitter.getSigningKey());
		
		Calendar cal = Calendar.getInstance();
		JSONObject jo = new JSONObject();
		jo.put("userId", userId);
		jo.put("issuer", ISSUER);
		jo.put("sourceIP", ip);
		
		Instant issuedAt = new org.joda.time.Instant(cal.getTimeInMillis());
		Instant expiresAt = new org.joda.time.Instant(cal.getTimeInMillis()+ 1000L * 60L * 60L * 24L * durationDays);
		
		jo.put("issuedAt", issuedAt.toString());
		jo.put("expiresAt", expiresAt.toString());
		
		JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(jo));
		
		jwsObject.sign(signer);
		return jwsObject.serialize();
	}
	
	public JSONObject verify(String s) throws ParseException, JOSEException {
		JWSObject jwsObject = JWSObject.parse(s);
		JWSVerifier verifier = new MACVerifier(globalEmitter.getSigningKey());
		if (jwsObject.verify(verifier)) {
			return jwsObject.getPayload().toJSONObject();
		}  
		return null;
	}
	
	public boolean userInToken(String userId, String token) throws ParseException, JOSEException { 
		JSONObject jo = verify(token);
		 return ((String) jo.get("userId")).trim().equals(userId.trim());
	}
}
