package com.cimait.invoicec.portal.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.repository.DocumentService;
import com.cimait.invoicec.portal.core.dto.DocumentDto;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;
import com.cimait.invoicec.portal.core.helpers.Formatting;


@Controller
public class CustomerDocumentController {

	@Autowired
	protected DocumentService documentService;

	@RequestMapping(method=RequestMethod.POST, value="/api/v1/customer/document/filter")
	public @ResponseBody List<DocumentDto> getAllFilter(@RequestBody DocumentFilter documentFilter, HttpServletRequest request) throws ParseException{
				List<Document> docs = (List<Document>)documentService.findAllByFilter(documentFilter);
				List<DocumentDto> docDtos = new ArrayList<DocumentDto>();
				for (Document doc : docs) {
										docDtos.add(convertToDto(doc));
				}
				return docDtos;
	}
	
	
	
	private DocumentDto convertToDto(Document in) {
		DocumentDto docDto = new DocumentDto();
		docDto.setIssueDate(Formatting.formatDate(in.getIssueDate()));
		docDto.setCurrency(in.getCurrency());
		docDto.setAmount(in.getAmount());
		docDto.setStatus(in.getStatus());
		docDto.setActive(in.getActive().toString());
		docDto.setLegalNumber(in.getLegalNumber());				
		docDto.setCustomerName(in.getCustomer().getName());
		docDto.setDocumentTypeCode(in.getDocumentType().getTypeId());
		return docDto;
	}
	
}
