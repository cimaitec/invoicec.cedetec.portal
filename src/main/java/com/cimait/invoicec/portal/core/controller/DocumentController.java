package com.cimait.invoicec.portal.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.repository.DocumentClientService;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentService;
import com.cimait.invoicec.portal.core.dto.DocumentDto;
import com.cimait.invoicec.portal.core.helpers.DocumentClientFilter;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;
import com.cimait.invoicec.portal.core.mail.EmailSender;


@Controller
public class DocumentController implements MessageSourceAware{

	
	private MessageSource messageSource;
	
	@Autowired
	protected DocumentService documentService;
	
	@Autowired
	protected DocumentRepository documentRepository;

	@Autowired 
	protected GlobalConfig globalConfig;
	
	@Autowired
	protected EmailSender emailSender;
	
	@Autowired
	protected DocumentClientService documentClientService;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/list")	
	public @ResponseBody List<DocumentDto> getAll(@RequestParam(value="emitterId") String emitterId) {		
				List<Document> docs = (List<Document>)documentRepository.findAll();
				List<DocumentDto> docDtos = new ArrayList<DocumentDto>();
				for (Document doc : docs) {
										docDtos.add(convertToDto(doc));
				}
				return docDtos;
	}	

	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document/filter")
	public @ResponseBody List<DocumentDto> getAllFilter(@RequestBody DocumentFilter documentFilter, HttpServletRequest request) throws ParseException{
				List<Document> docs = (List<Document>)documentService.findAllByFilter(documentFilter);
				List<DocumentDto> docDtos = new ArrayList<DocumentDto>();
				for (Document doc : docs) {
										docDtos.add(convertToDto(doc));
				}
				return docDtos;
	}
	
	
	   @RequestMapping(method=RequestMethod.POST, value="/api/v1/client/document/filter")
	  	public @ResponseBody List<DocumentDto> getAllClientDocumentFilter(@RequestBody DocumentClientFilter documentFilter, HttpServletRequest request) throws ParseException{
		   		List<Document> docs = (List<Document>)documentClientService.findDocumentClientByFilter(documentFilter);
		   		List<DocumentDto> docDtos = new ArrayList<DocumentDto>();
		   		for (Document doc : docs) {	
		   					docDtos.add(convertToDto(doc));
		   		}
		   		return docDtos;
	   }
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/download", produces="application/octet")
	public void getFile(@RequestParam(value="documentTypeCode") String documentTypeCode, @RequestParam(value="legalNumber") String legalNumber, HttpServletResponse resp) throws IOException {
				
				String emitter = "20565812948";//obtenerlo del gloal config (archivo propertie)
				String pathAuth = "C://mario//invoice//repository//04-authorized//";//obtener el path de la tabla de propiedades mediante el ruc el cual se obtedra del archivo de propiedades
				String fileName= emitter + "-" + documentTypeCode + "-" + legalNumber;
				String absoluteFileName = pathAuth + fileName;
				
				System.out.println("Download de archivo : " + fileName);
				resp.setContentType("application/octet");
				resp.setHeader("Content-Disposition","attachment; filename=\"" + "mario.pdf" +"\"");
				InputStream inputStream = null;
				OutputStream outputStream = null;
				try {
						inputStream = new FileInputStream(new File(absoluteFileName));
						outputStream = resp.getOutputStream();
						IOUtils.copy(inputStream, outputStream);
				} catch (Exception e) {
						System.out.println("Error al obtener archivo " + fileName);
						e.printStackTrace();
				} finally {
						inputStream.close();
						outputStream.close();
				}				
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/send")
	public void sendFile(@RequestParam(value="id") String id, @RequestParam(value="emails") String emails) {
		//datos de correo : 
		//Emitter emitter = emitterRepository.findOne("20565812948");		
		emailSender.setUser("mtorres@cimait.com.pe");
		emailSender.setPassword("cima$.2015");		
		emailSender.setHost("mail.cimait.com.pe");
		emailSender.setFrom("mtorres@cimait.com.pe");
		emailSender.setAutentificacion("NORMAL"); 		
		emailSender.setTipoMail("HTML");
		emailSender.setPuerto("25");
		emails = "mtorres@cimait.com.pe";
		//emailSender.setSubject(messageSource.getMessage("invoicec.mail.common.subject", null,  new Locale("es_PE")));
		emailSender.setSubject("Hola Mundo Mail");
		//String emailMsg = messageSource.getMessage("invoicec.mail.common.content",null,  new Locale("es_PE"));
		String emailMsg = "Hola Duke";
		//centelsa archivo de respuesta de SUNAT
		//String fileXML =  emitter.getPathCompAutorizados() + "R-" + globalConfig.getGlobalId() + "-" + id + ".ZIP";
		//cedetec archivo de respuesta de SRI
		//String fileXML = emitter.getPathCompAutorizados() + globalEmitter.getGlobalId() + "-" + id + ".xml";
		//String filePDF = emitter.getPathCompAutorizados() + globalConfig.getGlobalId() + "-" + id + ".PDF";
		String emitter = "20565812948";//obtenerlo del gloal config (archivo propertie)
		String pathAuth = "C://mario//invoice//repository//04-authorized//";//obtener el path de la tabla de propiedades mediante el ruc el cual se obtedra del archivo de propiedades
		String fileName= emitter + "-" + id;
		String absoluteFileName = pathAuth + fileName;
		String fileXML = absoluteFileName + ".xml";
		String filePDF = absoluteFileName + ".zip";
		emailSender.send(emails, emailSender.getSubject(), emailMsg, fileXML, filePDF);
	}
	
	private DocumentDto convertToDto(Document in) {
		DocumentDto docDto = new DocumentDto();
		//docDto.setIssueDate(Formatting.formatDate(in.getIssueDate()));
		docDto.setIssueDate(in.getIssueDate());
		docDto.setCurrency(in.getCurrency());
		docDto.setAmount(in.getAmount());
		docDto.setStatus(in.getStatus());
		docDto.setLegalNumber(in.getLegalNumber());				
		docDto.setCustomerName(in.getCustomer().getName());
		docDto.setDocumentTypeCode(in.getDocumentType().getTypeId());
		return docDto;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

 }
