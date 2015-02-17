--tabla de tipos de propiedades 
CREATE TABLE iv_property_type
(
	id serial,
	type_id character varying(15) NOT NULL,
	code character varying(25),
	descr character varying(100) NOT NULL,
	encrypt boolean,
	mandatory boolean, -- Si campo es obligatorio en el caso que se use en datos de doc, cancelar operacion si no cumple .
	country char(2) , 
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iv_property_type
  ADD CONSTRAINT iv_property_type_pk PRIMARY KEY (id);
ALTER TABLE iv_property_type
  OWNER TO postgres;

--tabla de tipos de documento
CREATE TABLE iv_document_type
(
	id serial,
	type_id character varying(4),
	descr	character varying(100),
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_document_type
  ADD CONSTRAINT iv_document_type_pk PRIMARY KEY (id);
ALTER TABLE iv_document_type
  OWNER TO postgres;

--tabla de scheduling cabecera
CREATE TABLE iv_sched
(
	id serial,
	descr character varying(50),
	active boolean,
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_sched
  ADD CONSTRAINT iv_sched_pk PRIMARY KEY (id);
ALTER TABLE iv_sched
  OWNER TO postgres;

--tabla de scheduling detalle
CREATE TABLE iv_sched_dtl
(
	id serial,
	sched_id bigint,
	day character varying(2),
	hour_ini character varying(2),
	min_ini  character varying(2),
	hour_end character varying(2),
	min_end  character varying(2)
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_sched_dtl
  ADD CONSTRAINT iv_sched_dtl_fk FOREIGN KEY (sched_id) REFERENCES iv_sched (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_sched_dtl
  ADD CONSTRAINT iv_sched_dtl_pk PRIMARY KEY (id);
ALTER TABLE iv_sched_dtl
  OWNER TO postgres;

--tabla de empresa emisor
CREATE TABLE iv_company
(
  id serial,	
  identification character varying(20) NOT NULL, -- id de la empresa emisora
  name character varying(300), -- razon social
  business_name character varying(300), -- razon comercial
  address character varying(300), -- direccion
  sched_id bigint, --a que rango de horas de envio esta asociado.
  country_id char(2), --pais , fk?
  archive boolean, --si va a manejar archiving
  archive_time integer, --tiempo a dejar información
  archive_type_time character(1) , --tipo de tiempo , 'M' = Mes , 'Y' = año  
  active boolean, --activa
  certificate_in_db boolean, --si el certificado se almacena en la BD "true" o a nivel de FS "false".
  certificate_file bytea, --certificado digital almacenado en byte
  created_date timestamp without time zone DEFAULT current_timestamp,
  created_user character varying(50),
  updated_date timestamp without time zone,
  updated_user character varying(50)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iv_company
  ADD CONSTRAINT iv_company_pk PRIMARY KEY (id);
ALTER TABLE iv_company
  OWNER TO postgres;

--tabla de tipos de documento a trabajar por company
CREATE TABLE iv_company_doc_type
(
	--id serial,
	company_id bigint NOT NULL,
	doc_type_id bigint NOT NULL
) WITH (
  OIDS=FALSE
);
--ALTER TABLE iv_company_doc_type
  --ADD CONSTRAINT iv_company_doc_type_pk PRIMARY KEY (id);
ALTER TABLE iv_company_doc_type
  ADD CONSTRAINT iv_company_doc_type_company_fk FOREIGN KEY (company_id) REFERENCES iv_company (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_doc_type
  ADD CONSTRAINT iv_company_doc_type_doc_type_fk FOREIGN KEY (doc_type_id) REFERENCES iv_document_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_doc_type
  OWNER TO postgres;

--tabla de propiedades de empresa basado en iv_property_type ie : 
CREATE TABLE iv_company_prop
(
	id serial,
	company_id bigint NOT NULL,
	property_type_id bigint NOT NULL, 
	value character varying(3000)
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_company_prop
  ADD CONSTRAINT iv_company_prop_company_fk FOREIGN KEY (company_id) REFERENCES iv_company (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_prop
  ADD CONSTRAINT iv_company_prop_property_fk FOREIGN KEY (property_type_id) REFERENCES iv_property_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_prop
  ADD CONSTRAINT iv_company_prop_pk PRIMARY KEY (id);
ALTER TABLE iv_company_prop
  OWNER TO postgres;

--tabla de clientes
CREATE TABLE iv_customer
(
	id serial, 
	identification character varying(20) NOT NULL,
	company_id bigint,
	name character varying(300), -- razon social
  	business_name character varying(300), -- razon comercial
  	address character varying(300), -- direccion
  	email character varying(50), --correo principal
  	active boolean, --activa
  	type char(1), --C = Cliente , P = Proveedor, B = Cliente/Proveedor
  	update_client boolean , --true = datos de cliente se actualizan con los datos de entrada (txt, ws, etc), false = no
  	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iv_customer
  ADD CONSTRAINT iv_customer_pk PRIMARY KEY (id);
ALTER TABLE iv_customer
  ADD CONSTRAINT iv_customer_company_fk FOREIGN KEY (company_id) REFERENCES iv_company (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_customer
  OWNER TO postgres;

--tabla propiedades de cliente
CREATE TABLE iv_customer_prop
(
	id serial,
	customer_id bigint,
	property_type_id bigint, 
	value character varying(3000),
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_customer_prop
  ADD CONSTRAINT iv_customer_prop_customer_fk FOREIGN KEY (customer_id) REFERENCES iv_customer (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_customer_prop
  ADD CONSTRAINT iv_customer_prop_property_fk FOREIGN KEY (property_type_id) REFERENCES iv_property_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_customer_prop
  ADD CONSTRAINT iv_customer_prop_pk PRIMARY KEY (id);
ALTER TABLE iv_customer_prop
  OWNER TO postgres;

--tabla relacion empresa - cliente
/**CREATE TABLE iv_company_customer
(
	id serial,
	company_id bigint,
	customer_id bigint,
	creation_type char(1) NOT NULL, -- creado interactivo "P" o desde interface "I". 
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_company_customer
  ADD CONSTRAINT iv_company_customer_company_fk FOREIGN KEY (company_id) REFERENCES iv_company (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_customer
  ADD CONSTRAINT iv_company_customer_customer_fk FOREIGN KEY (customer_id) REFERENCES iv_customer (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_company_customer
  ADD CONSTRAINT iv_company_customer_pk PRIMARY KEY (id);
ALTER TABLE iv_company_customer
  OWNER TO postgres;
**/
  
  
  
  
--tabla de documentos cabecera
CREATE TABLE iv_doc_hdr
(
	id serial,
	customer_id bigint,
	legal_number character varying(30), --numero de documento de entidad tributaria .
	doc_type_id bigint,
	issue_date date,
	currency character varying(3),
	amount   double precision,
	active boolean, --flag de ser procesado o no.
	status char(2) , --status transaccion hacia entidad tributaria, 'AT' = Autorizado , 'NA' = No Autorizado, 'CT' = Contingencia (error network/etc), 'LT' = Proceso Lote, 'SC' = Scheduler
	source char(2) , --origen , 'FS' = FileSystem , 'WS' = WebService , 'PS'  = POS, 'OT' = Other, 'PR' = Provider
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_hdr
  ADD CONSTRAINT iv_doc_hdr_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_hdr
  ADD CONSTRAINT iv_doc_hdr_customer_fk FOREIGN KEY (customer_id) REFERENCES iv_customer (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_hdr
  OWNER TO postgres;

--tabla de resumen de boletas .
CREATE TABLE iv_doc_hdr_summary_rel
(
	id bigint,
	doc_id bigint,  --resumen de boletas
	doc_id_rel bigint, --boletas asociadas, nd asociadas.
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
);
ALTER TABLE iv_doc_hdr_summary_rel
  OWNER TO postgres;
ALTER TABLE iv_doc_hdr_summary_rel
  ADD CONSTRAINT iv_doc_hdr_summary_doc_fk FOREIGN KEY (doc_id) REFERENCES iv_doc_hdr (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_hdr_summary_rel
  ADD CONSTRAINT iv_doc_hdr_summary_doc_rel_fk FOREIGN KEY (doc_id_rel) REFERENCES iv_doc_hdr (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_hdr_summary_rel
  ADD CONSTRAINT iv_doc_hdr_summary_rel_pk PRIMARY KEY (id);

--tabla de resumen de boletas - archiving
CREATE TABLE iv_doc_hdr_summary_rel_arch
(
	id bigint,
	rel_id bigint,
	doc_id bigint,  --resumen de boletas
	doc_id_rel bigint, --boletas asociadas, nd asociadas.
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50),	
  	arch_dttm timestamp without time zone DEFAULT current_timestamp --fecha de archiving
);
ALTER TABLE iv_doc_hdr_summary_rel
  OWNER TO postgres;
ALTER TABLE iv_doc_hdr_summary_rel_arch
  ADD CONSTRAINT iv_doc_hdr_summary_rel_arch_pk PRIMARY KEY (id);

--tabla de documentos cabecera - archiving
CREATE TABLE iv_doc_hdr_arch
(
	id serial,
	doc_id bigint,
	company_customer_id bigint,
	legal_number character varying(30), --numero de documento de entidad tributaria .
	doc_type_id character varying(04),
	issue_date date,
	currency character varying(3),
	amount   double precision,
	active boolean, --flag de ser procesado o no.
	status char(2) , --status transaccion hacia entidad tributaria, 'AT' = Autorizado , 'NA' = No Autorizado, 'CT' = Contingencia (error network/etc), 'LT' = Proceso Lote, 'SC' = Scheduler
	source char(2) , --origen , 'FS' = FileSystem , 'WS' = WebService , 'PS'  = POS, 'OT' = Other, 'PR' = Provider
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50),
  	arch_dttm timestamp without time zone DEFAULT current_timestamp --fecha de archiving
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_hdr_arch
  ADD CONSTRAINT iv_doc_hdr_arch_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_hdr_arch
  OWNER TO postgres;

--tabla de propiedades documento cabecera
CREATE TABLE iv_doc_hdr_prop
(
	id serial,
	doc_id bigint,
	property_type_id bigint,
	value_string character varying(200),
	value_amount double precision,
	value_date date
) WITH (
  OIDS=FALSE
);

ALTER TABLE iv_doc_hdr_prop
  ADD CONSTRAINT iv_doc_hdr_prop_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_hdr_prop
  ADD CONSTRAINT iv_doc_hdr_prop_property_fk FOREIGN KEY (property_type_id) REFERENCES iv_property_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_hdr_prop
  ADD CONSTRAINT iv_doc_hdr_prop_doc_fk FOREIGN KEY (doc_id) REFERENCES iv_doc_hdr (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_hdr_prop
  OWNER TO postgres;

--tabla de propiedades documento cabecera - archiving
CREATE TABLE iv_doc_hdr_prop_arch
(
	id serial,
	--hdr_prop_id bigint,
	doc_id bigint,
	property_type_id bigint,
	value_string character varying(200),
	value_amount double precision,
	value_date date,
	arch_dttm timestamp without time zone DEFAULT current_timestamp --fecha de archiving
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_hdr_prop_arch
  ADD CONSTRAINT iv_doc_hdr_prop_arch_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_hdr_prop_arch
  OWNER TO postgres;

--tabla de documento detalle
CREATE TABLE iv_doc_dtl
(
	id serial,
	doc_id bigint,
	sequence character varying(15),
	code character varying(25),
	descr character varying(200),
	quantity double precision,
	amount double precision,
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50)	
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_dtl
  ADD CONSTRAINT iv_doc_dtl_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_dtl
  ADD CONSTRAINT iv_doc_dtl_doc_fk FOREIGN KEY (doc_id) REFERENCES iv_doc_hdr (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_dtl
  OWNER TO postgres;

--tabla de documento detalle - archiving
CREATE TABLE iv_doc_dtl_arch
(
	id serial,
	--dtl_id bigint,
	doc_id bigint,
	sequence character varying(15),
	code character varying(25),
	descr character varying(200),
	quantity double precision,
	amount double precision,
	created_date timestamp without time zone DEFAULT current_timestamp,
  	created_user character varying(50),
  	updated_date timestamp without time zone,
  	updated_user character varying(50),
  	arch_dttm timestamp without time zone DEFAULT current_timestamp
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_dtl_arch
  ADD CONSTRAINT iv_doc_dtl_arch_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_dtl_arch
  OWNER TO postgres;

--tabla de propiedades de documento detalle
CREATE TABLE iv_doc_dtl_prop
(
	id serial,
	dtl_id bigint,
	property_type_id bigint NOT NULL,
	value_string character varying(200),
	value_amount double precision,
	value_date date
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_dtl_prop
  ADD CONSTRAINT iv_doc_dtl_prop_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_dtl_prop
  ADD CONSTRAINT iv_doc_dtl_prop_dtl_fk FOREIGN KEY (dtl_id) REFERENCES iv_doc_dtl (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_dtl_prop
  ADD CONSTRAINT iv_doc_dtl_prop_property_fk FOREIGN KEY (property_type_id) REFERENCES iv_property_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_dtl_prop
  OWNER TO postgres;

--tabla de propiedades de documento detalle - archiving
CREATE TABLE iv_doc_dtl_prop_arch
(
	id serial,
	dtl_id bigint,
	property_type_id character varying(15) NOT NULL,
	value_string character varying(200),
	value_amount double precision,
	value_date date,
	arch_dttm timestamp without time zone DEFAULT current_timestamp
)  WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_dtl_prop_arch
  ADD CONSTRAINT iv_doc_dtl_prop_arch_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_dtl_prop_arch
  OWNER TO postgres;

--tabla bitacora , audit
CREATE TABLE iv_doc_log
(
	id bigint,
	doc_id bigint,
	dttm timestamp without time zone DEFAULT current_timestamp,
	state character(2) , --estado del documento o estado en el flujo de proceso.
	msg text --iria mensaje de error / excepcion / respuesta entidad tributaria (solo si no se graba en disco), 
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_log
  ADD CONSTRAINT iv_doc_log_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_log
  ADD CONSTRAINT iv_doc_log_doc_fk FOREIGN KEY (doc_id) REFERENCES iv_doc_hdr (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE iv_doc_log
  OWNER TO postgres;

--tabla bitacora, audit - archiving
CREATE TABLE iv_doc_log_arch
(
	id bigint,
	log_id bigint,
	doc_id bigint,
	dttm timestamp without time zone DEFAULT current_timestamp,
	state character(2) , --estado del documento o estado en el flujo de proceso.
	msg text, --iria mensaje de error / excepcion / respuesta entidad tributaria (solo si no se graba en disco), 
	arch_dttm timestamp without time zone DEFAULT current_timestamp
) WITH (
  OIDS=FALSE
);
ALTER TABLE iv_doc_log_arch
  ADD CONSTRAINT iv_doc_log_arch_pk PRIMARY KEY (id);
ALTER TABLE iv_doc_log_arch
  OWNER TO postgres;


CREATE TABLE iv_mapfield
(
  id serial,
  document_type character(2),								--"TipoDocumento" character(2) NOT NULL,
  doc_level character(2),							--"NivelDocumento" character(2) NOT NULL,
  source character varying(300) NOT NULL, 			--"Origen" character varying(300) NOT NULL,
  target character varying(300) NOT NULL, 			--"Destino" character varying(300) NOT NULL,
  length integer,									--"Longitud" integer,
  data_type character varying(40), 					--"TipoDato" character varying(40),
  table_reference character varying(50),			--"TablaReferencia" character varying(50),
  field_reference character varying(50),			--"CampoReferencia" character varying(50),
  country character varying(2),						--"Pais" character varying(2),
  source_type character varying(10),										--"TipoOrigen" character varying(10),
  CONSTRAINT iv_mapfield_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iv_mapfield
  OWNER TO postgres;
GRANT ALL ON TABLE iv_mapfield TO postgres;  
  

CREATE TABLE fac_general
(
   id serial,
   cod_table character varying(5),
   value character varying(10),
   descripcion character varying(500),
   active boolean
  ) WITH (
  OIDS=FALSE
);
ALTER TABLE fac_general
   ADD CONSTRAINT fac_general_pk PRIMARY KEY (id);
ALTER TABLE fac_general
  OWNER TO postgres;
