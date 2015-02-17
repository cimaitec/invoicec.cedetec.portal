


-- Table: fac_opcion_menu
-- DROP TABLE fac_opcion_menu;
CREATE TABLE fac_opcion_menu
(
  "CodOpcionMenu" character varying(5) NOT NULL, -- Codigo de Opciones de Menu
  "Descripcion" character varying(300), -- Descripcion de la Opcion del Menu
  "UrlPages" character(300), -- Url o Pagina de Enlace
  "ParamDefault" character(300), -- Parametros Default
  "isActive" character(1), -- Estado
  "codOpcionMenuPadre" character varying(5), -- codigo padre de las opciones
  CONSTRAINT fac_opcion_menu_pk PRIMARY KEY ("CodOpcionMenu") -- Primary Key de Opcion Menu
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fac_opcion_menu
  OWNER TO postgres;
GRANT ALL ON TABLE fac_opcion_menu TO postgres;
COMMENT ON TABLE fac_opcion_menu
  IS 'Opciones de Menu';
COMMENT ON COLUMN fac_opcion_menu."CodOpcionMenu" IS 'Codigo de Opciones de Menu';
COMMENT ON COLUMN fac_opcion_menu."Descripcion" IS 'Descripcion de la Opcion del Menu';
COMMENT ON COLUMN fac_opcion_menu."UrlPages" IS 'Url o Pagina de Enlace';
COMMENT ON COLUMN fac_opcion_menu."ParamDefault" IS 'Parametros Default';
COMMENT ON COLUMN fac_opcion_menu."isActive" IS 'Estado';
COMMENT ON COLUMN fac_opcion_menu."codOpcionMenuPadre" IS 'codigo padre de las opciones';
COMMENT ON CONSTRAINT fac_opcion_menu_pk ON fac_opcion_menu IS 'Primary Key de Opcion Menu';


-- Table: fac_roles
-- DROP TABLE fac_roles;
CREATE TABLE fac_roles
(
  "CodRol" character(5) NOT NULL, -- Codigo de Roles
  "Descripcion" character varying(100), -- Descripcion de Roles
  "isActive" character(1), -- Estado de Roles
  CONSTRAINT fac_roles_pk PRIMARY KEY ("CodRol") -- Primary Key de Roles
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fac_roles
  OWNER TO postgres;
GRANT ALL ON TABLE fac_roles TO postgres;
COMMENT ON TABLE fac_roles
  IS 'Roles de Usuarios';
COMMENT ON COLUMN fac_roles."CodRol" IS 'Codigo de Roles';
COMMENT ON COLUMN fac_roles."Descripcion" IS 'Descripcion de Roles';
COMMENT ON COLUMN fac_roles."isActive" IS 'Estado de Roles';
COMMENT ON CONSTRAINT fac_roles_pk ON fac_roles IS 'Primary Key de Roles';


-- Table: fac_roles_opcion_menu

-- DROP TABLE fac_roles_opcion_menu;

CREATE TABLE fac_roles_opcion_menu
(
  "codRol" character(5) NOT NULL, -- Codigo de Rol
  "CodOpcionMenu" character(5) NOT NULL, -- Codigo de Opcion Menu
  CONSTRAINT fac_roles_opcion_menu_pk PRIMARY KEY ("codRol", "CodOpcionMenu"), -- Primary Key de Roles Opcion Menu
  CONSTRAINT "fac_roles_opcion_menu_Opcion_Menu_fk" FOREIGN KEY ("CodOpcionMenu")
      REFERENCES fac_opcion_menu ("CodOpcionMenu") MATCH FULL
      ON UPDATE NO ACTION ON DELETE NO ACTION, -- Foranea de roles_opcion_menu - Opcion_Menu
  CONSTRAINT "fac_roles_opcion_menu_Roles_fk" FOREIGN KEY ("codRol")
      REFERENCES fac_roles ("CodRol") MATCH FULL
      ON UPDATE NO ACTION ON DELETE NO ACTION -- Foranea de roles_opcion_menu - roles
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fac_roles_opcion_menu
  OWNER TO postgres;
GRANT ALL ON TABLE fac_roles_opcion_menu TO postgres;
COMMENT ON TABLE fac_roles_opcion_menu
  IS 'Roles Opcion Menu';
COMMENT ON COLUMN fac_roles_opcion_menu."codRol" IS 'Codigo de Rol';
COMMENT ON COLUMN fac_roles_opcion_menu."CodOpcionMenu" IS 'Codigo de Opcion Menu';

COMMENT ON CONSTRAINT fac_roles_opcion_menu_pk ON fac_roles_opcion_menu IS 'Primary Key de Roles Opcion Menu';
COMMENT ON CONSTRAINT "fac_roles_opcion_menu_Opcion_Menu_fk" ON fac_roles_opcion_menu IS 'Foranea de roles_opcion_menu - Opcion_Menu';
COMMENT ON CONSTRAINT "fac_roles_opcion_menu_Roles_fk" ON fac_roles_opcion_menu IS 'Foranea de roles_opcion_menu - roles';



-- Table: fac_usuarios

-- DROP TABLE fac_usuarios;

CREATE TABLE fac_usuarios
(
  "Ruc" character varying(20) NOT NULL, -- Ruc de la empresa Emisora
  "CodUsuario" character(13) NOT NULL, -- Codigo de Usuario
  "Nombre" character varying(100), -- Nombre del Usuario
  "TipoUsuario" character(1), -- Tipo de Usuario
  "isActive" character(1),
  "Password" text,
  "RucEmpresa" character(13),
  "email" character varying(100),
  CONSTRAINT fac_usuarios_pk PRIMARY KEY ("Ruc", "CodUsuario")
  --CONSTRAINT fac_empresa_usuarios_fk FOREIGN KEY ("Ruc")
    --  REFERENCES fac_empresa ("Ruc") MATCH FULL
      --ON UPDATE CASCADE ON DELETE CASCADE -- Foranea de Empresa - Usuarios
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fac_usuarios
  OWNER TO postgres;
GRANT ALL ON TABLE fac_usuarios TO postgres;
COMMENT ON TABLE fac_usuarios
  IS 'Usuarios de la Empresa Emisora';
COMMENT ON COLUMN fac_usuarios."Ruc" IS 'Ruc de la empresa Emisora';
COMMENT ON COLUMN fac_usuarios."CodUsuario" IS 'Codigo de Usuario';
COMMENT ON COLUMN fac_usuarios."Nombre" IS 'Nombre del Usuario';
COMMENT ON COLUMN fac_usuarios."TipoUsuario" IS 'Tipo de Usuario';

--COMMENT ON CONSTRAINT fac_empresa_usuarios_fk ON fac_usuarios IS 'Foranea de Empresa - Usuarios';



CREATE TABLE fac_usuarios_roles
(
  "Ruc" character varying(20) NOT NULL, -- Ruc de la empresa
  "CodUsuario" character(13) NOT NULL, -- Codigo de Usuario
  "CodRol" character(5) NOT NULL, -- Codigo de Rol
  "isActive" character(1), -- Estado
  CONSTRAINT fac_usuario_roles_pk PRIMARY KEY ("CodUsuario", "CodRol", "Ruc"),
  CONSTRAINT "fac_usuarios_roles_Roles_fk" FOREIGN KEY ("CodRol")
      REFERENCES fac_roles ("CodRol") MATCH FULL
      ON UPDATE NO ACTION ON DELETE NO ACTION, -- Foranea de Usuarios_roles - Roles
  CONSTRAINT "fac_usuarios_roles_Usuarios_fk" FOREIGN KEY ("CodUsuario", "Ruc")
      REFERENCES fac_usuarios ("CodUsuario", "Ruc") MATCH FULL
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fac_usuarios_roles
  OWNER TO postgres;
GRANT ALL ON TABLE fac_usuarios_roles TO postgres;
COMMENT ON TABLE fac_usuarios_roles
  IS 'Tabla Intermedia Usuarios - Roles';
COMMENT ON COLUMN fac_usuarios_roles."Ruc" IS 'Ruc de la empresa';
COMMENT ON COLUMN fac_usuarios_roles."CodUsuario" IS 'Codigo de Usuario';
COMMENT ON COLUMN fac_usuarios_roles."CodRol" IS 'Codigo de Rol';
COMMENT ON COLUMN fac_usuarios_roles."isActive" IS 'Estado';
COMMENT ON CONSTRAINT "fac_usuarios_roles_Roles_fk" ON fac_usuarios_roles IS 'Foranea de Usuarios_roles - Roles';

