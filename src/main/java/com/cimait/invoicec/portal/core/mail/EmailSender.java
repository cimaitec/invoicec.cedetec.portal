
package com.cimait.invoicec.portal.core.mail;

//import java.sql.Connection;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.configuration.Configuration;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;



//import com.sun.directory.examples.MailSSLSocketFactoryMailing;
import com.sun.mail.util.MailSSLSocketFactory;
//import java.security.GeneralSecurityException;

/**
 * @author Raul Collahuazo G.
 *05/03/2012
 */

@Component
public class EmailSender {
	

	
	private String from = null;
	private String host = null;
	//private Logger log;
	private String user;
	private String password = null;
	private String subject = null;
	private String autentificacion = null;
	private String tipoMail	= null;
	private String puerto;
	
	public String getSubject() {
		return subject;
	
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	//private BitacoraScp scp;
	//private Connection axis;
	private int firstmail;
	public EmailSender (String host, String from) {
		this.host = host;
		this.from = from;
	}
	
	/**public EmailSender (Logger log) {
		this.log=log;
	}**/
	
	public EmailSender () {
		
	}
	
	
	public String send (String to, String subject, String message, String fileAttachXml, String fileAttachPdf) {
		String result = "Enviado";
		Session session=null;
		System.out.println();
		System.out.println("mail.smtp.host::"+(host==null?"NULL":host));
		System.out.println("mail.stmp.user::"+(user==null?"NULL":user));
		System.out.println("mail.smtp.password::"+(password==null?"NULL":password));
		
		try {
			Properties prop = System.getProperties();
			//autentificacion ="SSL";
			if (autentificacion.equals("NONE")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto);
				prop.put("mail.smtp.auth", "no");
				session = Session.getDefaultInstance(prop,null);
			}
			if (autentificacion.equals("NORMAL")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
			}
			if (autentificacion.equals("TLS")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
				
				//To use SSL
		        //props.put("mail.smtp.socketFactory.port", "465");
		        //prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        //prop.put("mail.smtp.port", "25");
		        //props.put("mail.smtp.auth", "true");
		        //props.put("mail.smtp.port", "465");
		        
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
				//session = Session.getDefaultInstance(prop,null);
			}
			if (autentificacion.equals("SSL")){				
				MailSSLSocketFactory sf = null;
		        try {
		            sf = new MailSSLSocketFactory();
		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		        /*
				MailSSLSocketFactoryMailing sf = null;
		        try {
		            sf = new MailSSLSocketFactoryMailing();
		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		        */
		        sf.setTrustAllHosts(true);
		        prop.put("mail.smtp.ssl.socketFactory", sf);
		        prop.put("mail.smtp.allow8bitmime", "true");
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
				prop.put("mail.smtp.starttls.enable", "true");
				//To use SSL
		        //prop.put("mail.smtp.socketFactory.port", "465");
		        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        prop.put("mail.smtp.port", puerto);
		        //props.put("mail.smtp.auth", "true");
		        //props.put("mail.smtp.port", "465");
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
			}
			
			/*
			  props.put("mail.stmp.user" , "username");

		        //To use TLS
		        props.put("mail.smtp.auth", "true"); 
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.password", "password");
		        //To use SSL
		        props.put("mail.smtp.socketFactory.port", "465");
		        props.put("mail.smtp.socketFactory.class", 
		            "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.port", "465");
			 */
			
			
			//Session session = Session.getDefaultInstance(prop,null);
			MimeMessage msg = new MimeMessage(session);
			msg.setHeader("Content-Type", "text/plain; charset=utf-8");
			
			msg.setFrom(new InternetAddress(from));
			String[] receivers = to.split(";");
			for (int i=0; i < receivers.length; i++){
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers[i]));
			}
			msg.setSubject(subject,"utf-8");
			BodyPart messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPartFile = new MimeBodyPart();
			BodyPart messageBodyPartFilePdf = new MimeBodyPart();
			if (tipoMail.equals("TEXT")) {
				messageBodyPart.setHeader("Content-Type", "text/plain; charset=UTF-8");
				messageBodyPart.setText(message);
			}
			
			if (tipoMail.equals("HTML")) {
				messageBodyPart.setHeader("Content-Type", "text/plain; charset=UTF-8");
				messageBodyPart.setContent(message, "text/html");
			}
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			if ((fileAttachXml!=null)&&(!fileAttachXml.equals("")))
			{
				DataSource source =	new FileDataSource(fileAttachXml);
				messageBodyPartFile.setDataHandler(new DataHandler(source));
				messageBodyPartFile.setFileName(FilenameUtils.getName(fileAttachXml));
				multipart.addBodyPart(messageBodyPartFile);
	            
			}			
			if ((fileAttachPdf!=null)&&(!fileAttachPdf.equals("")))
			{
				DataSource source =	new FileDataSource(fileAttachPdf);
				messageBodyPartFilePdf.setDataHandler(new DataHandler(source));
				messageBodyPartFilePdf.setFileName(FilenameUtils.getName(fileAttachPdf));
				multipart.addBodyPart(messageBodyPartFilePdf);
	            
			}
			msg.setContent(multipart);
			Transport.send(msg);
			
		} catch (javax.mail.MessagingException me) {
			me.printStackTrace();
			result = me.toString();
		}
		return result;
	}
	
	public String send (String to, String subject, String message) {
		String result = "Enviado";
		try {
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host",host);
			Session sesion = Session.getDefaultInstance(prop,null);
			MimeMessage msg = new MimeMessage(sesion);
			msg.setFrom(new InternetAddress(from));
			String[] receivers = to.split(";");
			for (int i=0; i < receivers.length; i++){
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers[i]));
			}
			msg.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);			
			Transport.send(msg);
			
		} catch (javax.mail.MessagingException me) {
			result = me.toString();
		}
		return result;
	}
	
	
	
	public String send (String to, String subject, String message, String[] attachFiles) {
		String result = "Enviado";
		Session session=null;
		System.out.println();
		System.out.println("mail.smtp.host::"+(host==null?"NULL":host));
		System.out.println("mail.stmp.user::"+(user==null?"NULL":user));
		System.out.println("mail.smtp.password::"+(password==null?"NULL":password));
		
		try {
			Properties prop = System.getProperties();
			//autentificacion ="SSL";
			if (autentificacion.equals("NONE")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", "25");
				prop.put("mail.smtp.auth", "no");
				session = Session.getDefaultInstance(prop,null);
			}
			if (autentificacion.equals("NORMAL")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
				//prop.put("mail.smtp.starttls.enable", "true");
				//To use SSL
		        //props.put("mail.smtp.socketFactory.port", "465");
		        //prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        //prop.put("mail.smtp.port", "25");
		        //props.put("mail.smtp.auth", "true");
		        //props.put("mail.smtp.port", "465");
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
			}
			if (autentificacion.equals("TLS")){
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", "25");
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
				
				//To use SSL
		        //props.put("mail.smtp.socketFactory.port", "465");
		        //prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        //prop.put("mail.smtp.port", "25");
		        //props.put("mail.smtp.auth", "true");
		        //props.put("mail.smtp.port", "465");
		        
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
				//session = Session.getDefaultInstance(prop,null);
			}
			if (autentificacion.equals("SSL")){				
				MailSSLSocketFactory sf = null;
		        try {
		            sf = new MailSSLSocketFactory();
		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		        /*
				MailSSLSocketFactoryMailing sf = null;
		        try {
		            sf = new MailSSLSocketFactoryMailing();
		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		        */
		        sf.setTrustAllHosts(true);
		        prop.put("mail.smtp.ssl.socketFactory", sf);
				
				prop.put("mail.smtp.host",host);
				prop.put("mail.smtp.socketFactory.port", puerto );
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.stmp.user" , user);
				prop.put("mail.smtp.password", password);
				prop.put("mail.smtp.starttls.enable", "true");
				//To use SSL
		        //prop.put("mail.smtp.socketFactory.port", "465");
		        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        prop.put("mail.smtp.port", puerto);
		        //props.put("mail.smtp.auth", "true");
		        //props.put("mail.smtp.port", "465");
		        session = Session.getInstance(prop,
						  new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(user, password);}});
			}
			

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			String[] receivers = to.split(";");
			for (int i=0; i < receivers.length; i++){
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers[i]));
			}
			msg.setSubject(subject);
			BodyPart messageBodyPart = new MimeBodyPart();
			
			if (tipoMail.equals("TEXT"))
			messageBodyPart.setText(message);
			if (tipoMail.equals("HTML"))
				messageBodyPart.setContent(message, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			//add imagenes
			DataSource fds = new FileDataSource
			          ("C:\\images\\img1.jpg");
			BodyPart messageBodyPartImage = new MimeBodyPart();
			messageBodyPartImage.setDataHandler(new DataHandler(fds));
			messageBodyPartImage.setHeader("Content-Type", "image/jpeg");
			messageBodyPartImage.setHeader("Content-ID","cidcabecera");
			//messageBodyPartImage.setHeader("Content - ID", "&#60;cidcabecera1&#62;");
			//messageBodyPartImage.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(messageBodyPartImage);
			DataSource fds1 = new FileDataSource
			         ("C:\\images\\img1.jpg");
			BodyPart messageBodyPartImage1 = new MimeBodyPart();
			messageBodyPartImage1.setDataHandler(new DataHandler(fds1));
			messageBodyPartImage.setHeader("Content-Type", "image/jpeg");
			messageBodyPartImage1.setHeader("Content-ID","<cidpie>");
			//messageBodyPartImage1.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(messageBodyPartImage1);
			
			 // adds attachments
	        if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	        
	        
	    
			msg.setContent(multipart);
			//Transport.send(msg, msg.getAllRecipients());
			Transport.send(msg);
			
		} catch (javax.mail.MessagingException me) {
			me.printStackTrace();
			
			result = me.toString();
		}
		return result;
		
		
	}

	
	
	
	
	/**
	 * Funcion que envia  mensaje de correo  
	 * */
	/*
	public String Envia_Mensaje(String cuerpo, String fileAttach, String fileAttachPdf){
		String result=""; 
		if (Util.enablemail.equals("Y")) {
			EmailSender e = new EmailSender(Util.host, Util.from);
		    result = e.send(Util.list_email, Util.subject, cuerpo+Util.pieMensaje, fileAttach, fileAttachPdf);
		    if(result.equals("Enviado"))
		    {
		    	System.out.println("Estado del correo: "+result);
		    }else{
		    	System.err.println("No se procedio a enviar el correo "+result);
		    }
		    
		    
	     }
	
	
	return result;
	}*/
	
	
	/**
	 * Funcion que permite enviar mensaje cada cierto tiempo
	 * configurado en el xml
	 * */
//	public Date Verifica_Tiempo_Mensaje(String cuerpo,Date ld_fecha,int firstmail, String fileAttach, String fileAttachPdf){
//		String result="";
//		double fecha_i;
//		int li_time_minutes_alarm = 0;
//		String mensaje="";
//		try{
//			//li_time_minutes_alarm = Util.time_mail;
//		}catch(Exception e){
//			li_time_minutes_alarm = 10; //Valor por default
//		}
//		
//		fecha_i=Util.calcTimeMin(ld_fecha,new Date());
//		if (fecha_i>li_time_minutes_alarm || firstmail==1){
//        	try{        		
//                //safirCli.sendAlarm("DESGSM: **ERROR DE CONEXION ", "Supero el numero de reintentos de conexion ");
//                EmailSender e = new EmailSender(Util.host, Util.from);
//                //mensaje="**Error de Conexion a B.D. Supero el numero de reintentos "+Util.reintentos_base+" de conexion ";
//               // result=e.Envia_Mensaje(cuerpo, fileAttach, fileAttachPdf);
//                if(result.equals("Enviado")){
//                	log.info("**Se procedio a enviar el correo a los siguientes destinatarios: "+Util.list_email);
//                	ld_fecha = new Date();
//                	//firstmail=0;
//                	setFirstmail(0);
//                }else{
//                	log.info("**No se puede enviar correos a los destinatarios: "+Util.list_email);
//                }
//                //ld_fecha = new Date();
//        	}catch(Exception e){
//                System.err.println("microcontainer..GenericTransaction::**Error de conexion-->"+e.getMessage());
//                log.error("microcontainer..GenericTransaction::**Error de conexion-->"+e.getMessage());
//        	} 
//    	}else{
//    		//log.warn(new StringBuffer().append("**El tiempo  minimo de envio entre correo no ha sido superado"));
//    		System.out.println("**El tiempo  minimo de envio entre correo no ha sido superado");
//    	}
//	
//	
//	return ld_fecha;
//	}

	public int getFirstmail() {
		return firstmail;
	}

	public void setFirstmail(int firstmail) {
		this.firstmail = firstmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAutentificacion() {
		return autentificacion;
	}

	public void setAutentificacion(String autentificacion) {
		this.autentificacion = autentificacion;
	}

	public String getTipoMail() {
		return tipoMail;
	}

	public void setTipoMail(String tipoMail) {
		this.tipoMail = tipoMail;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}	
	
	
}

