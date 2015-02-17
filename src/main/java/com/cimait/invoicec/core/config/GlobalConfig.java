package com.cimait.invoicec.core.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cimait.invoicec.core.entity.Emitter;
import com.cimait.invoicec.core.repository.EmitterPropertyRepository;
import com.cimait.invoicec.core.repository.EmitterRepository;
import com.cimait.invoicec.core.utils.Builder;

@Component
public class GlobalConfig {
	
	@Autowired
	private EmitterRepository emitterRepository;
	
	@Autowired
	private EmitterPropertyRepository emitterPropertyRepository;

	@Autowired
	private Builder builder;

	
	//toma valores del application.properties
	@Value("${invoicec.emitterId}")
	public  String GlobalId;

	@Value("${invoicec.smtpAutType}")
	public String SMTPAutType;

	@Value("${invoicec.smtpContentType}")
	public String SMTPContentType;

	@Value("${invoicec.smtpPort}")
	public String SMTPPort ;

	@Value("${invoicec.signingKey}")
	public String signingKey;
	
	@Value("${invoicec.uploadFilePath}")
	public String uploadFilePath;
	
	@Value("${invoicec.security}")
	public String security;
	
	@Value("${invoicec.cors}")
	public String cors;
	
	@Value("${invoicec.locale}")
	public String locale;
	
	@Value("${msg}")
	public String msg;
	
	private Emitter emitter;

	public String getGlobalId() {
		return GlobalId;
	}

	public void setGlobalId(String globalId) {
		GlobalId = globalId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getSMTPPort() {
		return SMTPPort;
	}

	public void setSMTPPort(String sMTPPort) {
		SMTPPort = sMTPPort;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public String getSigningKey() {
		return signingKey;
	}

	public void setSigningKey(String signingKey) {
		this.signingKey = signingKey;
	}
	
	public String getSMTPAutType() {
		return SMTPAutType;
	}

	public void setSMTPAutType(String sMTPAutType) {
		SMTPAutType = sMTPAutType;
	}

	public String getSMTPContentType() {
		return SMTPContentType;
	}

	public void setSMTPContentType(String sMTPContentType) {
		SMTPContentType = sMTPContentType;
	}

	public String getCors() {
		return cors;
	}

	public void setCors(String cors) {
		this.cors = cors;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@PostConstruct
	public void initEmitter() {
		if (emitter == null) {			
			Emitter emitterDB = (Emitter)emitterRepository.findOneByIdentification(GlobalId);
			if(emitterDB != null){ emitter = emitterDB; }
			else {emitter =  builder.buildDefaultEmitter(); builder.buildDefaultDocument();}
		}
	}

	public Emitter getEmitter() {
		return this.emitter;
	}

	public String getEmitterProperty(String propertyType){
		return emitterPropertyRepository.findByCode(emitter.getId(), propertyType).getValue();
	}

	
}
