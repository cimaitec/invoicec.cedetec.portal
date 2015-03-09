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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentLog;
import com.cimait.invoicec.core.repository.DocumentLogRepository;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentService;
import com.cimait.invoicec.portal.core.dto.CustomerDto;
import com.cimait.invoicec.portal.core.dto.DocumentDto;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;
import com.cimait.invoicec.portal.core.mail.EmailSender;


@Controller
public class BitacoraController {

	
		
		@Autowired
		protected DocumentService documentService;
		
		@Autowired
		protected DocumentRepository documentRepository;

		 @Autowired
		 	protected DocumentLogRepository documentLogRepository;
		
		 @RequestMapping(method=RequestMethod.GET, value="/api/v1/documentLog/list")
			@Transactional
			public @ResponseBody List<DocumentDto> getAll(){
				List<DocumentLog> log = (List<DocumentLog>) documentLogRepository.findAll();
				List<DocumentDto> logDtos = new ArrayList<DocumentDto>();
				Iterator i = log.iterator();
				DocumentLog docLog;
				while(i.hasNext()){
					docLog =(DocumentLog)i.next();
					logDtos.add(convertToDto(docLog));
				}
				return logDtos;
			}
		 
		 
		@RequestMapping(method=RequestMethod.GET, value="/api/v1/documentLog")
		@Transactional
		public @ResponseBody List<DocumentDto> get(@RequestParam(value="documentId") String documentId){
			
		List<DocumentDto> logDtos = new ArrayList<DocumentDto>();
		DocumentDto dto = new DocumentDto();
		
		Document result = documentRepository.findOneDocument(documentId);
		
	
		List<DocumentLog> docLog = (List<DocumentLog>)documentLogRepository.findAll();
		
		for (int i= 0; i<docLog.size(); i++){
		if (docLog.get(i).getDocument().getId() == result.getId()){
		dto.setMessage(docLog.get(i).getMsg());
		dto.setState(docLog.get(i).getState());
		dto.setLogIssueDate(docLog.get(i).getDttm());
		dto.setLegalNumber(docLog.get(i).getDocument().getLegalNumber());
		dto.setCustomer(docLog.get(i).getDocument().getCustomer().getName());
		dto.setDocumentTypeCode(docLog.get(i).getDocument().getDocumentType().getDescr());
		logDtos.add(dto);	
		}
			
		}
		
		
			
			return logDtos;
			
			
			
		}

		private DocumentDto convertToDto(DocumentLog docLog) {
		
			DocumentDto documentTypeLog = new DocumentDto();
			documentTypeLog.setState(docLog.getState());
			documentTypeLog.setMessage(docLog.getMsg());
			documentTypeLog.setLogIssueDate(docLog.getDttm());
			documentTypeLog.setLegalNumber(docLog.getDocument().getLegalNumber());
			documentTypeLog.setCustomer(docLog.getDocument().getCustomer().getName());
			documentTypeLog.setDocumentTypeCode(docLog.getDocument().getDocumentType().getDescr());
			documentTypeLog.setId_document(docLog.getDocument().getId());
			
			
			
			return documentTypeLog;
		}
		
}