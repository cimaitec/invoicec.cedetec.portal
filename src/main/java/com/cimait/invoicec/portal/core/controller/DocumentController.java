package com.cimait.invoicec.portal.core.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.Document;
import com.cimait.invoicec.core.entity.DocumentType;
import com.cimait.invoicec.core.repository.DocumentRepository;
import com.cimait.invoicec.core.repository.DocumentTypeRepository;
import com.cimait.invoicec.portal.core.dto.DocumentInfo;
import com.cimait.invoicec.portal.core.helpers.DocumentFilter;
import com.cimait.invoicec.portal.core.helpers.Formatting;


@Controller
public class DocumentController{

	@Autowired
	protected DocumentRepository documentRepository;
		
	@Autowired
	protected DocumentTypeRepository documentTypeRepository;

	@Autowired 
	protected GlobalConfig globalConfig;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/list")	
	public @ResponseBody List<DocumentInfo> getAll(@RequestParam(value="emitterId") String emitterId) {		
			List<Document> docs = (List<Document>)documentRepository.findAll();
			List<DocumentInfo> docsInfo = new ArrayList<DocumentInfo>();
			Iterator i = docs.iterator();
			Document doc;
			while(i.hasNext()){
				doc = (Document)i.next();
				docsInfo.add(mapData(doc));
			}			
			return docsInfo;
	}	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/type/list")
	public @ResponseBody List<DocumentType> getAllTypes(){
		return (List<DocumentType>) documentTypeRepository.findAll();
	}

	

	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document/listFilter")
	public @ResponseBody List<DocumentInfo> getAllFilter(@RequestBody DocumentFilter documentFilter, HttpServletRequest request) throws ParseException{
		List<DocumentInfo> lDocumentsReturn = new ArrayList<DocumentInfo>();
		if (documentFilter.getCustomerId()==null) documentFilter.setCustomerId("%");
		if (documentFilter.getBeginIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setBeginIssueDate(new java.sql.Date(sdf.parse("01/01/1900").getTime()));
		}
		if (documentFilter.getEndIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setEndIssueDate(new java.sql.Date(sdf.parse("31/12/2090").getTime()));
		}
		//cuando llega tiene 1 dia menos ?? , se usa joda para +1
		LocalDate date = new LocalDate(documentFilter.getBeginIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setBeginIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));
		date = new LocalDate(documentFilter.getEndIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setEndIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));

		
		if (documentFilter.getBeginSequence() == null) documentFilter.setBeginSequence(new BigDecimal("0"));
		if (documentFilter.getEndSequence() == null) documentFilter.setEndSequence(new BigDecimal("999999999"));
		
		if (documentFilter.getDocumentType() == null) documentFilter.setDocumentType("%"); 
		
		/**List<Document> lDocuments = documentRepository.findAllByFilter(globalConfig.getGlobalId(), 
				documentFilter.getEnvironment(), documentFilter.getCustomerId(), 
				documentFilter.getBeginIssueDate(), documentFilter.getEndIssueDate(), 
				documentFilter.getBeginSequence(), documentFilter.getEndSequence(), 
				documentFilter.getDocumentType());**/
		
		List<Document> lDocuments = (List<Document>)documentRepository.findAll();
		
		for (Document document : lDocuments) {
			lDocumentsReturn.add(mapData(document));
		}
		return lDocumentsReturn;
	}
	
	
	
	
	
	
	
	
	
	
	private DocumentInfo mapData(Document in) {
		DocumentInfo docInfo = new DocumentInfo();
		docInfo.setFechaEmision(Formatting.formatDate(in.getIssueDate()));
		docInfo.setNroDocumento(Formatting.formatNumeroDocumento(in.getLegalNumber()));
		docInfo.setCodigoDocumento("0" + in.getDocTypeId().toString());
		docInfo.setMoneda(in.getCurrency());
		docInfo.setImporteTotal(in.getAmount());		
		docInfo.setEstadoTransaccion(in.getStatus());
		docInfo.setImporteTotal(in.getAmount());
		docInfo.setIsActive(in.getActive().toString());
		
		docInfo.setIdentificacionComprador("asdfasd");
		docInfo.setRazonSocialComprador("RazonSocial Comprador");		
		docInfo.setTipIdentificacionComprador("asdfasd");
		return docInfo;
	}
	
	
	
	
	/**
	private MessageSource messageSource;
	
	@Autowired
	protected MailReader mailReader;

	@Autowired
	protected EmailSender emailSender;
	
	@Autowired 
	protected GlobalConfig globalConfig;
	
	@Autowired
	protected EmitterRepository emitterRepository ;
	
	@Autowired
	protected DocumentRepository documentRepository;
	
	@Autowired
	protected DocumentTypeRepository documentTypeRepository;
	
	@Autowired 
	protected CustomerRepository CustomerRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected RetentionDocumentDetailRepository retentionDocumentDetailRepository;

	@Autowired 
	protected DocumentAdditionalInfoRepository documentAdditionalInfoRepository;
	
	@Autowired
	protected DocumentDetailDebitRepository documentDetailDebitRepository;
	
	@Autowired
	protected AuthHelper authHelper;
	
	@Autowired
	protected DocumentDetailRepository documentDetailRepository;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/list")
	public List<DocumentInfo> getAll(@RequestParam(value="emitterId") String emitterId) {
		List<DocumentInfo> lDocumentsReturn = new ArrayList<DocumentInfo>();
		List<Document> lDocuments = documentRepository.findAllByRuc(emitterId);
		for (Document document : lDocuments) {
			lDocumentsReturn.add(mapData(document));
		}
		return lDocumentsReturn;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/type/list")
	public List<DocumentType> getAllTypes(){
		return (List<DocumentType>) documentTypeRepository.findAll();
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document/listFilter")
	public List<DocumentInfo> getAllFilter(@RequestBody DocumentFilter documentFilter, HttpServletRequest request) throws ParseException{
		List<DocumentInfo> lDocumentsReturn = new ArrayList<DocumentInfo>();
		if (documentFilter.getCustomerId()==null) documentFilter.setCustomerId("%");
		if (documentFilter.getBeginIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setBeginIssueDate(new java.sql.Date(sdf.parse("01/01/1900").getTime()));
		}
		if (documentFilter.getEndIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setEndIssueDate(new java.sql.Date(sdf.parse("31/12/2090").getTime()));
		}
		//cuando llega tiene 1 dia menos ?? , se usa joda para +1
		LocalDate date = new LocalDate(documentFilter.getBeginIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setBeginIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));
		date = new LocalDate(documentFilter.getEndIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setEndIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));

		
		if (documentFilter.getBeginSequence() == null) documentFilter.setBeginSequence(new BigDecimal("0"));
		if (documentFilter.getEndSequence() == null) documentFilter.setEndSequence(new BigDecimal("999999999"));
		
		if (documentFilter.getDocumentType() == null) documentFilter.setDocumentType("%"); 
		List<Document> lDocuments = documentRepository.findAllByFilter(globalConfig.getGlobalId(), documentFilter.getEnvironment(), documentFilter.getCustomerId(), documentFilter.getBeginIssueDate(), documentFilter.getEndIssueDate(), documentFilter.getBeginSequence(), documentFilter.getEndSequence(), documentFilter.getDocumentType());
		for (Document document : lDocuments) {
			lDocumentsReturn.add(mapData(document));
		}
		return lDocumentsReturn;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/download", produces="application/octet")
	public void getFile(@RequestParam(value="id") String id , HttpServletResponse resp) throws IOException {
		//capturo ruta de archivos autorizados :
		Emitter emitter = emitterRepository.findOne(globalConfig.getGlobalId());
		String pathAuth = emitter.getPathCompAutorizados(); 
		
		String fileName= pathAuth + globalConfig.getGlobalId() + "-" + id;
		System.out.println("Download de archivo : " + fileName);
		
		resp.setContentType("application/octet");
		resp.setHeader("Content-Disposition","attachment; filename=\"" + globalConfig.getGlobalId() + "-" + id +"\"");
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = new FileInputStream(new File(fileName));
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
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/rew")
	public void reprocessFile(@RequestParam(value="id") String id){
		System.out.println("Reprocesando documento : " + id);
		//se evalua primero si el documento esta en CT
		//Centelsa
		String codDoc = id.substring(0,2);
		String codEst = id.substring(3,4);
		String codPto = id.substring(4,7);
		String seq = id.substring(8);
		//Cedetec 
		//String codDoc = id.substring(0,2);
		//String CodEst = id.substring(3,6);
		//String CodPto = id.substring(6,9);
		//String seq = id.substring(10);
		
		DocumentPK docPK = new DocumentPK() ;
		docPK.setRuc(globalConfig.getGlobalId());
		docPK.setCodEstablecimiento(codEst);
		docPK.setCodPuntEmision(codPto);
		docPK.setSecuencial(seq);
		docPK.setCodigoDocumento(codDoc);
		
		Document doc = documentRepository.findOne(docPK);
		
		if (doc == null ) {
			throw new EntityNotFoundException();
		}
		
		//Centelsa , se busca el archivo XML, ZIP en firmados y se eliminan  
		//borro el .txt de recibidos , se deja el _backup
		
		Emitter emitter = emitterRepository.findOne(globalConfig.getGlobalId());
		
		String pathSigned = emitter.getPathCompFirmados();
		String pathReceived = emitter.getPathInfoRecibida();
		
		String xmlFile = pathSigned + globalConfig.getGlobalId() + "-" +  id + ".XML";
		String zipFile = pathSigned + globalConfig.getGlobalId() + "-" +  id + ".ZIP";
		String txtFile = pathReceived + globalConfig.getGlobalId() + "-" +  id + ".txt";
		
		File xmlFF = new File(xmlFile);
		if (xmlFF.exists()) {
			xmlFF.delete();
			System.out.println("archivo eliminado XML : " + xmlFile);
		}
		
		File zipFF = new File(zipFile); 
		if (zipFF.exists()) {
			zipFF.delete();
			System.out.println("archivo eliminado ZIP : " + zipFile);
		}
		
		File txtFF = new File(txtFile);
		if (txtFF.exists()) {
			txtFF.delete();
			System.out.println("archivo eliminado TXT : " + txtFile);
		}
		
		System.out.println("Cambiado status a RR de documento : " + id);
		//cambio status del documento a RR -reproceso , para que aparezca asi en cada refresco de consulta hasta cuando se coloque el archivo .TXT en su ubicacion.
		doc.setEstadoTransaccion("RR");
		documentRepository.save(doc);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document/send")
	public void sendFile(@RequestParam(value="id") String id, @RequestParam(value="emails") String emails) {
		//datos de correo : 
		emails = "ealmonte@cimait.com.pe";
		Emitter emitter = emitterRepository.findOne(globalConfig.getGlobalId());
		emailSender.setHost(emitter.getServidorSmtp());
		emailSender.setFrom(emitter.getEmailEnvio());
		emailSender.setPassword(emitter.getPassSmtp());
		emailSender.setUser(emitter.getUserSmtp());
		emailSender.setAutentificacion(globalConfig.getSMTPAutType()); //con password
		emailSender.setTipoMail(globalConfig.getSMTPContentType());
		emailSender.setPuerto(globalConfig.getSMTPPort());

		emailSender.setSubject(messageSource.getMessage("invoicec.mail.common.subject",null,  new Locale(globalConfig.getLocale())));
		String emailMsg = messageSource.getMessage("invoicec.mail.common.content",null,  new Locale(globalConfig.getLocale()));
		
		//centelsa archivo de respuesta de SUNAT
		String fileXML =  emitter.getPathCompAutorizados() + "R-" + globalConfig.getGlobalId() + "-" + id + ".ZIP";
		
		//cedetec archivo de respuesta de SRI
		//String fileXML = emitter.getPathCompAutorizados() + globalEmitter.getGlobalId() + "-" + id + ".xml";
		
		String filePDF = emitter.getPathCompAutorizados() + globalConfig.getGlobalId() + "-" + id + ".PDF";
		
		emailSender.send(emails, emailSender.getSubject(), emailMsg, fileXML, filePDF);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document/customer/list")
	public List<DocumentInfo> getAllCustomerFilter(@RequestBody DocumentFilter documentFilter, HttpServletRequest request) throws ParseException, JOSEException{
		List<DocumentInfo> lDocumentsReturn = new ArrayList<DocumentInfo>();

		//valido si usuario que pide es el mismo del token
		if (request.getHeader("Authorization") == null) {
			throw new UnAuthorizedException();
		}
		
		if (!authHelper.userInToken(documentFilter.getCustomerId(),request.getHeader("Authorization"))) {
			throw new UnAuthorizedException();
		}
		
		//extraigo RUC Cliente del usuario
		User user = userRepository.findByRucAndCodUsuarioAndTipoUsuario(globalConfig.getGlobalId(), documentFilter.getCustomerId(), "C");
		
		if (user == null) {
			System.out.println("usuario no encontrado " + documentFilter.getCustomerId());
			throw new EntityNotFoundException();
		}
		
		
		CustomerPK custPK = new CustomerPK();
		custPK.setRuc(globalConfig.getGlobalId());
		custPK.setRucCliente(user.getRucEmpresa().trim());
		
		Customer cust = CustomerRepository.findOne(custPK);
		
		if (cust == null) {
			System.out.println("Empresa cliente no encontrado " + user.getRucEmpresa());
			throw new EntityNotFoundException();
		}
		
		if (documentFilter.getBeginIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setBeginIssueDate(new java.sql.Date(sdf.parse("01/01/1900").getTime()));
		}
		if (documentFilter.getEndIssueDate()==null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			documentFilter.setEndIssueDate(new java.sql.Date(sdf.parse("31/12/2090").getTime()));
		}
		LocalDate date = new LocalDate(documentFilter.getBeginIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setBeginIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));
		date = new LocalDate(documentFilter.getEndIssueDate().getTime(), DateTimeZone.UTC);
		documentFilter.setEndIssueDate(new java.sql.Date(date.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).getMillis()));

		
		if (documentFilter.getBeginSequence() == null) documentFilter.setBeginSequence(new BigDecimal("0"));
		if (documentFilter.getEndSequence() == null) documentFilter.setEndSequence(new BigDecimal("999999999"));
		
		if (documentFilter.getDocumentType() == null) documentFilter.setDocumentType("%"); 

		List<Document> lDocuments = documentRepository.findAllCustomerFilter(globalConfig.getGlobalId(), cust.getRucCliente().trim(), documentFilter.getBeginIssueDate(), documentFilter.getEndIssueDate(), documentFilter.getBeginSequence(), documentFilter.getEndSequence(), documentFilter.getDocumentType());
		
		for (Document document : lDocuments) {
			DocumentInfo documentInfo = mapData(document);
			documentInfo.setRazonSocialComprador("");
			lDocumentsReturn.add(documentInfo);
		}
		return lDocumentsReturn;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/document")
	public DocumentEmitterInfo getDocumentRetention(@RequestParam(value="ruc") String ruc, @RequestParam(value="identificacionComprador") String identificacionComprador,
			@RequestParam(value="codEstablecimiento") String codEstablecimiento, @RequestParam(value="codPuntEmision") String codPuntEmision, 
			@RequestParam(value="secuencial") String secuencial, @RequestParam(value="codigoDocumento") String codigoDocumento) {
		
		DocumentEmitterInfo tmpReturn = new DocumentEmitterInfo();
		
		//TODO : manejo de pais? 0 = pe / 1,2 = ec
		int ambiente = 0;
		Document tmpDocument = documentRepository.findByKey(ruc, ambiente, identificacionComprador, codEstablecimiento, codPuntEmision, secuencial, codigoDocumento);
		if (tmpDocument != null){
			
			//generales
			tmpReturn.setAmbiente(tmpDocument.getAmbiente());
			tmpReturn.setRuc(tmpDocument.getRuc());
			tmpReturn.setCodEstablecimiento(tmpDocument.getCodEstablecimiento());
			tmpReturn.setCodPuntEmision(tmpDocument.getCodPuntEmision());
			tmpReturn.setSecuencial(tmpDocument.getSecuencial());
			tmpReturn.setAutorizacion(tmpDocument.getAutorizacion());
			tmpReturn.setFechaautorizacion(tmpDocument.getFechaautorizacion());
			tmpReturn.setTipoEmision(tmpReturn.getTipoEmision());
			tmpReturn.setClaveAcceso(tmpDocument.getClaveAcceso());
			tmpReturn.setFechaEmision(tmpDocument.getFechaEmision());
			tmpReturn.setRazonSocialComprador(tmpDocument.getRazonSocialComprador());
			tmpReturn.setIdentificacionComprador(tmpDocument.getIdentificacionComprador());
			tmpReturn.setTipoIdentificacion(tmpDocument.getTipoIdentificacion());
			tmpReturn.setRazonSocialDestinatario(tmpDocument.getRazonSocialDestinatario());
			
			//factura
			
			tmpReturn.setGuiaRemision(tmpDocument.getGuiaRemision());
			tmpReturn.setPropina(tmpDocument.getPropina());
			tmpReturn.setTotalDescuento(tmpDocument.getTotalDescuento());
			
			//retention
			
			tmpReturn.setPeriodoFiscal(tmpDocument.getPeriodoFiscal());
			
			//datos de nota de debito y NC
			//TODO eliminar los repetidos
			tmpReturn.setRise(tmpDocument.getRise());
			tmpReturn.setCodDocModificado(tmpDocument.getCodDocModificado());
			tmpReturn.setNumDocModificado(tmpDocument.getNumDocModificado());
			tmpReturn.setFechaEmisionDocSustento(tmpDocument.getFechaEmisionDocSustento());
			
			
			// ND, NC y Factura
			tmpReturn.setTotalSinImpuesto(tmpDocument.getTotalSinImpuesto());
			tmpReturn.setImporteTotal(tmpDocument.getImporteTotal());
			tmpReturn.setIva12(tmpDocument.getIva12());
			tmpReturn.setSubtotal0(tmpDocument.getSubtotal0());
			tmpReturn.setSubtotal12(tmpDocument.getSubtotal12());
			tmpReturn.setSubtotalNoIva(tmpDocument.getSubtotalNoIva());
			tmpReturn.setTotalvalorIce(tmpDocument.getTotalvalorIce());
			
			//detalles factura y nota de credito
			
			List<DocumentDetail> ldocumentDetail = documentDetailRepository.findAllByFilter(ruc, codEstablecimiento, codPuntEmision, codigoDocumento);
			tmpReturn.ldocumentDetail = new ArrayList<DocumentDetail>();
			for (DocumentDetail documentDetail : ldocumentDetail) {
				tmpReturn.ldocumentDetail.add(documentDetail);
			}
			
			
			//si esta busco sus detalles retencion
			List<DocumentDetailRetention> lDetailRetention = retentionDocumentDetailRepository.findAllByFilter(ruc, codEstablecimiento, codPuntEmision, secuencial);
			//instancio lista de detalle de la clase a devolver
			tmpReturn.ldocumentRetentionDetail = new ArrayList<DocumentDetailRetention>();
			//lleno la lista a devolver del tmp
            for (DocumentDetailRetention documentDetailRetention : lDetailRetention) {
            	tmpReturn.ldocumentRetentionDetail.add(documentDetailRetention);
			} 
            
            //nota de debito detalle
            
            List<DocumentDetailDebit> ldetailDebit = documentDetailDebitRepository.findAllByFilter(ruc, codEstablecimiento, codPuntEmision, secuencial);
			tmpReturn.lmotivosDebit = new ArrayList<DocumentDetailDebit>();
			for (DocumentDetailDebit documentDetailDebit : ldetailDebit) {
				tmpReturn.lmotivosDebit.add(documentDetailDebit);
			}
			

            //informacion adicional aplica a todos los tipos de documento
            List<DocumentAdditionalInfo> linfoAdic = documentAdditionalInfoRepository.findAllByFilter(ruc, codEstablecimiento, codPuntEmision, secuencial, codigoDocumento);
            tmpReturn.lcampoAdicional = new ArrayList<DocumentAdditionalInfo>();
            
            for (DocumentAdditionalInfo infoAdicionalDocuments : linfoAdic) {
            	tmpReturn.lcampoAdicional.add(infoAdicionalDocuments);
			}
            return tmpReturn;
		}else {
				System.out.println("El documento no encontrado" + codEstablecimiento+"-"+codPuntEmision+"-"+secuencial);
				throw new EntityNotFoundException();
		}
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/document")
	public void deleteRetentionDocument(@RequestParam(value="ruc") String ruc ,@RequestParam(value="codEstablecimiento") String codEstablecimiento, @RequestParam(value="codPuntEmision") String codPuntEmision, 
	@RequestParam(value="secuencial") String secuencial, @RequestParam(value="codigoDocumento") String codigoDocumento, @RequestParam(value="identificacionComprador") String identificacionComprador){
		
	DocumentPK tmp = new DocumentPK();
	tmp.setRuc(ruc);
	tmp.setCodEstablecimiento(codEstablecimiento);
	tmp.setCodPuntEmision(codPuntEmision);
	tmp.setSecuencial(secuencial);
	tmp.setCodigoDocumento(codigoDocumento);
	
	Document resulset = documentRepository.findOne(tmp);
	
	if (resulset != null){
		if (codigoDocumento.equals("07")) {
			//si es documento de retencion , elimino sus detalles
			deleteRetentionDetail(resulset);
		}
		
		if (codigoDocumento.equals("05")) {
			deleteDebitDetail(resulset);
		}
		
		if (codigoDocumento.equals("01") || (codigoDocumento.equals("04"))){
			deleteDoumentDetail(resulset);
		}
		
		deleteInfoAdicional(resulset);
		documentRepository.delete(tmp);
	}else{
		System.out.println("No se encontro el documento" );
		throw new EntityNotFoundException();
	}	
}

	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/document")
	public void saveRetentionDocument(@RequestBody DocumentEmitterInfo documentInfo, HttpServletRequest request){
		Document tmpDoc = documentRepository.findByKey(documentInfo.getRuc(), documentInfo.getAmbiente(), documentInfo.getIdentificacionComprador(), documentInfo.getCodEstablecimiento(), documentInfo.getCodPuntEmision(), documentInfo.getSecuencial(), documentInfo.getCodigoDocumento());
		
		if (tmpDoc == null) {
			tmpDoc = new Document();
			//TODO : resto de campos por tipo de documento.
			
			
			//Para todos los documentos
			
			tmpDoc.setAmbiente(documentInfo.getAmbiente());
			tmpDoc.setRuc(documentInfo.getRuc());
			tmpDoc.setCodEstablecimiento(documentInfo.getCodEstablecimiento());
			tmpDoc.setCodPuntEmision(documentInfo.getCodPuntEmision());
			tmpDoc.setSecuencial(documentInfo.getSecuencial());
			tmpDoc.setCodigoDocumento(documentInfo.getCodigoDocumento());
			tmpDoc.setFechaEmision(documentInfo.getFechaEmision());
			tmpDoc.setTipoEmision(documentInfo.getTipoEmision());
			tmpDoc.setRazonSocialComprador(documentInfo.getRazonSocialComprador());
			tmpDoc.setIdentificacionComprador(documentInfo.getIdentificacionComprador());
			tmpDoc.setEmail(documentInfo.getEmail());
			tmpDoc.setDireccionDestinatario(documentInfo.getDireccionDestinatario());
			tmpDoc.setTipoIdentificacion(documentInfo.getTipoIdentificacion());
			tmpDoc.setClaveAcceso(documentInfo.getClaveAcceso());
			tmpDoc.setInfoAdicional(documentInfo.getInfoAdicional());
			
			
			//Documento factura
			
			tmpDoc.setGuiaRemision(documentInfo.getGuiaRemision());
			tmpDoc.setTotalDescuento(documentInfo.getTotalDescuento());
			tmpDoc.setPropina(documentInfo.getPropina());
			
			
			//documento de retencion
			
			tmpDoc.setPeriodoFiscal(documentInfo.getPeriodoFiscal());
			
			//nota de debito //credito
			tmpDoc.setCodDocModificado(documentInfo.getCodDocModificado());
			tmpDoc.setNumDocModificado(documentInfo.getNumDocModificado());
			tmpDoc.setFechaEmisionDocSustento(documentInfo.getFechaEmisionDocSustento());
			tmpDoc.setRise(documentInfo.getRise());
			
			//Nota de debito, nota de crédito, factura
			tmpDoc.setImporteTotal(documentInfo.getImporteTotal());
			tmpDoc.setIva12(documentInfo.getIva12());
			tmpDoc.setSubtotal0(documentInfo.getSubtotal0());
			tmpDoc.setSubtotal12(documentInfo.getSubtotal12());
			tmpDoc.setSubtotalNoIva(documentInfo.getSubtotalNoIva());
			tmpDoc.setTotalvalorIce(documentInfo.getTotalvalorIce());
			tmpDoc.setTotalSinImpuesto(documentInfo.getTotalSinImpuesto());
			
			
			//Nota de credito, factura.
			tmpDoc.setMoneda(documentInfo.getMoneda());
			
			documentRepository.save(tmpDoc);
			
			if (documentInfo.getCodigoDocumento().equals("07")) {
				//solo para documento de retencion.
				saveRetentionDetail(documentInfo);
			}
			
			if (documentInfo.getCodigoDocumento().equals("05")) {
				//solo para documento de retencion.
				saveDebitDetail(documentInfo);
			}
			
			if (documentInfo.getCodigoDocumento().equals("01") || (documentInfo.getCodigoDocumento().equals("04"))){
				// factura y nota de credito
				saveDocumentDetail(documentInfo);
			}
			
			//aplica para todos los tipos de documento.
			saveAdditionalInfo(documentInfo);
		}
	}
			

	public void saveDebitDetail(DocumentEmitterInfo doc) {
		for (int i = 0; i < doc.lmotivosDebit.size(); i++) {
			DocumentDetailDebit tmpDetailDebit = new DocumentDetailDebit();
			tmpDetailDebit.setRuc(doc.lmotivosDebit.get(i).getRuc());
			tmpDetailDebit.setCodEstablecimiento(doc.lmotivosDebit.get(i).getCodEstablecimiento());
			tmpDetailDebit.setCodPuntEmision(doc.lmotivosDebit.get(i).getCodPuntEmision());
			tmpDetailDebit.setSecuencial(doc.lmotivosDebit.get(i).getSecuencial());
			tmpDetailDebit.setCodigoDocumento(doc.lmotivosDebit.get(i).getCodigoDocumento());
			tmpDetailDebit.setSecuencialDetalle(doc.lmotivosDebit.get(i).getSecuencialDetalle());
			tmpDetailDebit.setRazon(doc.lmotivosDebit.get(i).getRazon());
			tmpDetailDebit.setValor(doc.lmotivosDebit.get(i).getValor());
			tmpDetailDebit.setCodImpuesto(doc.lmotivosDebit.get(i).getCodImpuesto());
			tmpDetailDebit.setCodPorcentaje(doc.lmotivosDebit.get(i).getCodPorcentaje());
			tmpDetailDebit.setTarifa(doc.lmotivosDebit.get(i).getTarifa());
			tmpDetailDebit.setBaseImponible(doc.lmotivosDebit.get(i).getBaseImponible());
			tmpDetailDebit.setTipoImpuestos(doc.lmotivosDebit.get(i).getTipoImpuestos());
			documentDetailDebitRepository.save(tmpDetailDebit);
		}
		
	}
	
	public void saveAdditionalInfo(DocumentEmitterInfo doc) {
		DocumentAdditionalInfoPK tmpPKInfo = new DocumentAdditionalInfoPK();
		
		for (int j = 0; j < doc.lcampoAdicional.size(); j++) {
			//tmpPKInfo.setSecuencialAdicional(documentInfo.lcampoAdicional.get(j).getSecuencialAdicional());
			//InfoAdicionalDocuments search = infoAdicionalDocumentsRepository.findOne(tmpPKInfo);
			DocumentAdditionalInfo tmpInfo = new DocumentAdditionalInfo();

			 tmpInfo.setRuc(doc.lcampoAdicional.get(j).getRuc());
			 tmpInfo.setCodEstablecimiento(doc.lcampoAdicional.get(j).getCodEstablecimiento());
			 tmpInfo.setCodPuntEmision(doc.lcampoAdicional.get(j).getCodPuntEmision());
			 tmpInfo.setSecuencial(doc.lcampoAdicional.get(j).getSecuencial());
			 tmpInfo.setCodigoDocumento(doc.lcampoAdicional.get(j).getCodigoDocumento());
			 tmpInfo.setCodigo(doc.lcampoAdicional.get(j).getCodigo());
			 tmpInfo.setSecuencialAdicional(doc.lcampoAdicional.get(j).getSecuencialAdicional());
			 tmpInfo.setValor(doc.lcampoAdicional.get(j).getValor());
			 documentAdditionalInfoRepository.save(tmpInfo);
	}	

	}
	
	public void saveRetentionDetail(DocumentEmitterInfo doc) {

		DocumentDetailRetentionPK tmpPKDetail = new DocumentDetailRetentionPK();
		
		DocumentDetailRetention tmpDetail = new DocumentDetailRetention();
		for (int i = 0; i < doc.ldocumentRetentionDetail.size(); i++) {
			
			tmpPKDetail.setSecuencialRetencion(doc.ldocumentRetentionDetail.get(i).getSecuencialRetencion());
			
			DocumentDetailRetention resulset = retentionDocumentDetailRepository.findOne(tmpPKDetail);
			if (resulset == null){
				tmpDetail.setRuc(doc.ldocumentRetentionDetail.get(i).getRuc ());
				tmpDetail.setCodEstablecimiento(doc.ldocumentRetentionDetail.get(i).getCodEstablecimiento());
				tmpDetail.setCodPuntEmision(doc.ldocumentRetentionDetail.get(i).getCodPuntEmision());
				tmpDetail.setSecuencial(doc.ldocumentRetentionDetail.get(i).getSecuencial());
				tmpDetail.setCodImpuesto(doc.ldocumentRetentionDetail.get(i).getCodImpuesto());
				tmpDetail.setCodPorcentaje(doc.ldocumentRetentionDetail.get(i).getCodPorcentaje());
				tmpDetail.setBaseImponible(doc.ldocumentRetentionDetail.get(i).getBaseImponible());
				tmpDetail.setTarifa(doc.ldocumentRetentionDetail.get(i).getTarifa());
				tmpDetail.setValor(doc.ldocumentRetentionDetail.get(i).getValor());
				tmpDetail.setPorcentajeRetencion(doc.ldocumentRetentionDetail.get(i).getPorcentajeRetencion());
				tmpDetail.setSecuencialRetencion(doc.ldocumentRetentionDetail.get(i).getSecuencialRetencion());
				tmpDetail.setCodigoDocumento(doc.ldocumentRetentionDetail.get(i).getCodigoDocumento());
				tmpDetail.setCodDocSustento(doc.ldocumentRetentionDetail.get(i).getCodDocSustento());
				tmpDetail.setFecDocSustento(doc.ldocumentRetentionDetail.get(i).getFecDocSustento());
				retentionDocumentDetailRepository.save(tmpDetail);
			}else{
				System.out.println("Ese detalle ya fue agregado al documento" );
				throw new DocumentInfoException();
			}
		} 
	}

	//Metodo para factura y NC
	public void saveDocumentDetail(DocumentEmitterInfo doc){
		
	DocumentDetailPK tmpPK = new DocumentDetailPK();
	DocumentDetail tmp = new DocumentDetail();
	
	for (int i = 0; i < doc.ldocumentDetail.size(); i++) {
	
		tmpPK.setSecuencialDetalle(doc.ldocumentDetail.get(i).getSecuencialDetalle());
		
		DocumentDetail resulset = documentDetailRepository.findOne(tmpPK);
		if (resulset == null){
		tmp.setRuc(doc.ldocumentDetail.get(i).getRuc());
		tmp.setCodEstablecimiento(doc.ldocumentDetail.get(i).getCodEstablecimiento());
		tmp.setCodPuntEmision(doc.ldocumentDetail.get(i).getCodPuntEmision());
		tmp.setSecuencial(doc.ldocumentDetail.get(i).getSecuencial());
		tmp.setCodigoDocumento(doc.ldocumentDetail.get(i).getCodigoDocumento());
		tmp.setSecuencialDetalle(doc.ldocumentDetail.get(i).getSecuencialDetalle());
		tmp.setCantidad(doc.ldocumentDetail.get(i).getCantidad());
		tmp.setCodAuxiliar(doc.ldocumentDetail.get(i).getCodAuxiliar());
		tmp.setCodPrincipal(doc.ldocumentDetail.get(i).getCodPrincipal());
		tmp.setDescripcion(doc.ldocumentDetail.get(i).getDescripcion());
		tmp.setDescuento(doc.ldocumentDetail.get(i).getDescuento());
		tmp.setPrecioTotalSinImpuesto(doc.ldocumentDetail.get(i).getPrecioTotalSinImpuesto());
		tmp.setPrecioUnitario(doc.ldocumentDetail.get(i).getPrecioUnitario());
		tmp.setValorIce(doc.ldocumentDetail.get(i).getValorIce());
		documentDetailRepository.save(tmp);
		
		
		}
	}
	
		
	}
	
	public void deleteInfoAdicional(Document doc) {
		List<DocumentAdditionalInfo> search = documentAdditionalInfoRepository.findAllByFilter(doc.getRuc(), doc.getCodEstablecimiento(), doc.getCodPuntEmision(), doc.getSecuencial(), doc.getCodigoDocumento());
		if(search != null){
				for (int i = 0; i < search.size(); i++) {
					DocumentAdditionalInfoPK tmpInfoAdic = new DocumentAdditionalInfoPK();
					tmpInfoAdic.setRuc(search.get(i).getRuc());
					tmpInfoAdic.setCodEstablecimiento(search.get(i).getCodEstablecimiento());
					tmpInfoAdic.setCodPuntEmision(search.get(i).getCodPuntEmision());
					tmpInfoAdic.setSecuencial(search.get(i).getSecuencial());
					tmpInfoAdic.setCodigoDocumento(search.get(i).getCodigoDocumento());
					tmpInfoAdic.setSecuencialAdicional(search.get(i).getSecuencialAdicional());
					documentAdditionalInfoRepository.delete(tmpInfoAdic);
				}
			}
	}
	
	public void deleteDebitDetail(Document doc) {
		List<DocumentDetailDebit> findby = documentDetailDebitRepository.findAllByFilter(doc.getRuc(), doc.getCodEstablecimiento(), doc.getCodPuntEmision(), doc.getSecuencial());
		if (!findby.isEmpty()){
			for (int i = 0; i < findby.size(); i++) {
				DocumentDetailDebitPK deleteMotivoPK = new DocumentDetailDebitPK();
				deleteMotivoPK.setRuc(findby.get(i).getRuc());
				deleteMotivoPK.setCodEstablecimiento(findby.get(i).getCodEstablecimiento());
				deleteMotivoPK.setCodPuntEmision(findby.get(i).getCodPuntEmision());
				deleteMotivoPK.setSecuencial(findby.get(i).getSecuencial());
				deleteMotivoPK.setCodigoDocumento(findby.get(i).getCodigoDocumento());
				deleteMotivoPK.setSecuencialDetalle(findby.get(i).getSecuencialDetalle());
				documentDetailDebitRepository.delete(deleteMotivoPK);
			}
		}else {
			System.out.println("No existen motivos para la nota de debito" +doc.getCodEstablecimiento()+"-"+doc.getCodPuntEmision()+"-"+doc.getSecuencial());
			throw new EntityNotFoundException();
		}
	}
	
	public void deleteRetentionDetail(Document doc) {
		List<DocumentDetailRetention> result = retentionDocumentDetailRepository.findAllByFilter(doc.getRuc(), doc.getCodEstablecimiento(), doc.getCodPuntEmision(), doc.getSecuencial());
		if (!result.isEmpty()){
			for (int i = 0; i < result.size(); i++) {
				DocumentDetailRetentionPK tmpPKDetail = new DocumentDetailRetentionPK();
				tmpPKDetail.setRuc(result.get(i).getRuc());
				tmpPKDetail.setCodEstablecimiento(result.get(i).getCodEstablecimiento());
				tmpPKDetail.setCodPuntEmision(result.get(i).getCodPuntEmision());
				tmpPKDetail.setSecuencial(result.get(i).getSecuencial());
				tmpPKDetail.setCodImpuesto(result.get(i).getCodImpuesto());
				tmpPKDetail.setSecuencialRetencion(result.get(i).getSecuencialRetencion());
				tmpPKDetail.setCodigoDocumento(result.get(i).getCodigoDocumento());
				retentionDocumentDetailRepository.delete(tmpPKDetail);
				}
			}else {
				System.out.println("No existen detalles para el documento de retención" +doc.getCodEstablecimiento()+"-"+doc.getCodPuntEmision()+"-"+doc.getSecuencial());
				throw new EntityNotFoundException();
			}

		}
	
	
	public void deleteDoumentDetail(Document doc){
		
		List<DocumentDetail> result = documentDetailRepository.findAllByFilter(doc.getRuc(), doc.getCodEstablecimiento(), doc.getCodPuntEmision(), doc.getSecuencial());
		if (!result.isEmpty()){
			for (int i = 0; i < result.size(); i++) {
				DocumentDetailPK tmp = new DocumentDetailPK();
				tmp.setRuc(result.get(i).getRuc());
				tmp.setCodEstablecimiento(result.get(i).getCodEstablecimiento());
				tmp.setCodPuntEmision(result.get(i).getCodPuntEmision());
				tmp.setSecuencial(result.get(i).getSecuencial());
				tmp.setSecuencialDetalle(result.get(i).getSecuencialDetalle());
				documentDetailRepository.delete(tmp);
				}
			
		}else {
			System.out.println("No existen motivos para la nota de debito" +doc.getCodEstablecimiento()+"-"+doc.getCodPuntEmision()+"-"+doc.getSecuencial());
			throw new EntityNotFoundException();
		}
	}
	
	
	
	private DocumentInfo mapData(Document in) {
		DocumentInfo docInfo = new DocumentInfo();
		docInfo.setAmbiente(in.getAmbiente());
		docInfo.setCodEstablecimiento(in.getCodEstablecimiento().trim());
		docInfo.setCodigoDocumento(in.getCodigoDocumento().trim());
		docInfo.setCodPuntEmision(in.getCodPuntEmision().trim());
		docInfo.setEstadoTransaccion(in.getEstadoTransaccion());
		docInfo.setFechaEmision(in.getFechaEmision());
		docInfo.setIdentificacionComprador(in.getIdentificacionComprador());
		docInfo.setImporteTotal(in.getImporteTotal());
		docInfo.setIsActive(in.getIsActive());
		docInfo.setMoneda(in.getMoneda());
		docInfo.setRazonSocialComprador(in.getRazonSocialComprador());
		docInfo.setRuc(in.getRuc());
		docInfo.setSecuencial(in.getSecuencial().trim());
		docInfo.setTipIdentificacionComprador(in.getTipIdentificacionComprador());
		return docInfo;
	}
	
	
	
  @RequestMapping(method=RequestMethod.POST, value="/api/v1/document/mail")
  public void getmaildata (@RequestBody AttachMail archive[], HttpServletRequest request){
		
		String [] tmp = new String [archive.length];
		
		Emitter emitter = emitterRepository.findOne(globalConfig.getGlobalId());
		
		String ruta = "C:\\";
		
		for (int i = 0; i < archive.length; i++) {
			
				//tmp[i] = archive[i].getArchive() + emitter;
			tmp[i] = ruta + archive[i].getArchive();
			System.out.println("impresion elemento" + archive[i].getArchive());
			System.out.println("impresion de prueba" + "" + ruta + archive[i].getArchive());
			
			}

		
		String subject = "Correo de Prueba";
		emailSender.setFirstmail(0);
		emailSender.setUser("mduo@cimait.com.pe");
		emailSender.setPassword("Cima2014");
		emailSender.setTipoMail("HTML");
		emailSender.setFrom("mariangelamichelena@gmail.com");
		emailSender.setHost("mail.cimait.com.pe");
		emailSender.setSubject(subject);
		emailSender.setAutentificacion("NORMAL");
		String to = "mduo@cimait.com.pe";
		
		String cabecera = "<HTML><BODY><H1>cabecera</H1> <img src='http://maginsynch.com/MAS/img1.JPG'/><br> <br/>";
		String pie = "<br/> <H1>pie del documento </H1> <br/></BODY></HTML> ";
		String body = "<H1>el documento</H1>" + emitter.getRuc() + "<br></br>" + "Revise su bandeja" + "<br><br/>";
		String message = cabecera + body + "<br></br>" + pie;
		emailSender.send(to, subject, message, tmp);
	
		//String cabecera =  "<HTML><BODY><H1>cabecera</H1> <img src='cid:cidcabecera'><br> <br/> ";
		//String pie = "<br/> <br/> <img src='cid:cidpie' /></BODY></HTML>";
		//String pie = "<br/> <H1>pie del documento </H1> <img src=\"cid:cidpie\" /> <br/></BODY></HTML> ";
		
		//String cabecera =  "<HTML><BODY><img src='cid:cidcabecera' WIDTH=178 HEIGHT=180 /> <br/> <br/>";
		//String cabecera =  "<H1>cabecera</H1>   <img src=\"C:\\images\\img1.jpg\"> <br> <br/> ";
		
		//String message = "<H1>el documento</H1>" + emitter.getRuc() + "<br></br>" + "Revise su bandeja" + "<br><br/>" + "<img src=\"cid:cidcabecera\">";
		//String message = "<H1>el documento</H1>" + "16894807" + "<br></br>" + "Revise su bandeja" + "<br><br/>";
		
}
  
  @RequestMapping(method=RequestMethod.GET, value="/api/v1/document/read")
  public void LeerCorreo (@RequestParam(value="ruc") String ruc) throws IOException{
 

	  mailReader.attachReader(ruc);

  }

@Override
public void setMessageSource(MessageSource messageSource) {
	this.messageSource = messageSource;
}
**/	  
 }
