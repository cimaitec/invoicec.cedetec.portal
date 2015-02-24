package com.cimait.invoicec.portal.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentService;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.portal.core.dto.CustomerComboDto;
import com.cimait.invoicec.portal.core.dto.DocumentDto;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;
import com.cimait.invoicec.portal.core.helpers.Formatting;


@Controller
public class DocumentController{

	
	@Autowired
	protected DocumentService documentService;
	
	@Autowired
	protected DocumentRepository documentRepository;

	@Autowired 
	protected GlobalConfig globalConfig;
	
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
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/download", produces="application/octet")
	public void getFile(@RequestParam(value="legalNumber") String legalNumber, @RequestParam(value="tipoDocumento") String tipoDocumento, HttpServletResponse resp) throws IOException {
				
				String emitter = "20565812948";//obtenerlo del gloal config (archivo propertie)
				String pathAuth = "C://mario//invoice//repository//04-authorized//";//obtener el path de la tabla de propiedades mediante el ruc el cual se obtedra del archivo de propiedades
				String fileName= emitter + "-" + tipoDocumento + "-" + legalNumber + ".PDF";
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
	
	private DocumentDto convertToDto(Document in) {
		DocumentDto docDto = new DocumentDto();
		docDto.setIssueDate(Formatting.formatDate(in.getIssueDate()));
		docDto.setCurrency(in.getCurrency());
		docDto.setAmount(in.getAmount());
		docDto.setStatus(in.getStatus());
		docDto.setActive(in.getActive().toString());
		docDto.setLegalNumber(in.getLegalNumber());
		docDto.setCodigoDocumento(in.getDocumentType().getTypeId());
		docDto.setIdentificacionComprador("asdfasd");
		docDto.setRazonSocialComprador(in.getCustomer().getName());		
		docDto.setTipIdentificacionComprador("asdfasd");
		return docDto;
}
	
	
 }
