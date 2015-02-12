package com.cimait.invoicec.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
	
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


	public String getGlobalId() {
		return GlobalId;
	}

	public void setGlobalId(String globalId) {
		GlobalId = globalId;
	}
	
	public String getCors() {
		return cors;
	}

	public void setCors(String cors) {
		this.cors = cors;
	}

	
	
}
