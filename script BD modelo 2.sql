/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     11/4/2024 23:52:05                           */
/*==============================================================*/


drop index ES_UN_ALUMNO_FK;

drop index ALUMNO_PK;

drop table ALUMNO;

drop index PUBLICA_FK;

drop index ANUNCIO_PK;

drop table ANUNCIO;

drop index REGISTRADO_FK;

drop index CORRESPONDE_FK;

drop index ASIGNACION_PK;

drop table ASIGNACION;

drop index REGISTRADO_CON_FK;

drop index CUENTA_PK;

drop table CUENTA;

drop index ES_UN_DOCENTE_FK;

drop index DOCENTE_PK;

drop table DOCENTE;

drop index ASOCIADA_FK;

drop index TRAMITA_FK;

drop index INSCRIPCION_PK;

drop table INSCRIPCION;

drop index MATERIA_PK;

drop table MATERIA;

drop index PROPORCIONA_FK;

drop index MATERIAL_PK;

drop table MATERIAL;

drop index USUARIO_PK;

drop table USUARIO;

/*==============================================================*/
/* Table: ALUMNO                                                */
/*==============================================================*/
create table ALUMNO (
   ID_ALUMNO            INT4                 not null,
   ID_USUARIO           INT4                 null,
   TELEFONO_ALUMNO      VARCHAR(30)          not null,
   CARRERA_ALUMNO       VARCHAR(30)          not null,
   constraint PK_ALUMNO primary key (ID_ALUMNO)
);

/*==============================================================*/
/* Index: ALUMNO_PK                                             */
/*==============================================================*/
create unique index ALUMNO_PK on ALUMNO (
ID_ALUMNO
);

/*==============================================================*/
/* Index: ES_UN_ALUMNO_FK                                       */
/*==============================================================*/
create  index ES_UN_ALUMNO_FK on ALUMNO (
ID_USUARIO
);

/*==============================================================*/
/* Table: ANUNCIO                                               */
/*==============================================================*/
create table ANUNCIO (
   ID_ANUNCIO           INT4                 not null,
   ID_MATERIA           INT4                 null,
   DESCRIPCION_ANUNCIO  VARCHAR(1024)        not null,
   FECHA_ANUNCIO        DATE                 not null,
   HORA_ANUNCIO         TIME                 not null,
   constraint PK_ANUNCIO primary key (ID_ANUNCIO)
);

/*==============================================================*/
/* Index: ANUNCIO_PK                                            */
/*==============================================================*/
create unique index ANUNCIO_PK on ANUNCIO (
ID_ANUNCIO
);

/*==============================================================*/
/* Index: PUBLICA_FK                                            */
/*==============================================================*/
create  index PUBLICA_FK on ANUNCIO (
ID_MATERIA
);

/*==============================================================*/
/* Table: ASIGNACION                                            */
/*==============================================================*/
create table ASIGNACION (
   ID_ASIGNACION        INT4                 not null,
   ID_DOCENTE           INT4                 null,
   ID_MATERIA           INT4                 null,
   FECHA_ASIGNACION     DATE                 not null,
   constraint PK_ASIGNACION primary key (ID_ASIGNACION)
);

/*==============================================================*/
/* Index: ASIGNACION_PK                                         */
/*==============================================================*/
create unique index ASIGNACION_PK on ASIGNACION (
ID_ASIGNACION
);

/*==============================================================*/
/* Index: CORRESPONDE_FK                                        */
/*==============================================================*/
create  index CORRESPONDE_FK on ASIGNACION (
ID_MATERIA
);

/*==============================================================*/
/* Index: REGISTRADO_FK                                         */
/*==============================================================*/
create  index REGISTRADO_FK on ASIGNACION (
ID_DOCENTE
);

/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA (
   ID_CUENTA            INT4                 not null,
   ID_USUARIO           INT4                 null,
   LOGIN                VARCHAR(30)          not null,
   CONTRASENIA          VARCHAR(30)          not null,
   ESTADO               VARCHAR(30)          not null,
   FECHA_ASIG_PASSWORD  VARCHAR(30)          not null,
   constraint PK_CUENTA primary key (ID_CUENTA)
);

/*==============================================================*/
/* Index: CUENTA_PK                                             */
/*==============================================================*/
create unique index CUENTA_PK on CUENTA (
ID_CUENTA
);

/*==============================================================*/
/* Index: REGISTRADO_CON_FK                                     */
/*==============================================================*/
create  index REGISTRADO_CON_FK on CUENTA (
ID_USUARIO
);

/*==============================================================*/
/* Table: DOCENTE                                               */
/*==============================================================*/
create table DOCENTE (
   ID_DOCENTE           INT4                 not null,
   ID_USUARIO           INT4                 null,
   TITULO_DOCENTE       VARCHAR(30)          not null,
   UNIVERSIDAD_EGRESO   VARCHAR(30)          not null,
   constraint PK_DOCENTE primary key (ID_DOCENTE)
);

/*==============================================================*/
/* Index: DOCENTE_PK                                            */
/*==============================================================*/
create unique index DOCENTE_PK on DOCENTE (
ID_DOCENTE
);

/*==============================================================*/
/* Index: ES_UN_DOCENTE_FK                                      */
/*==============================================================*/
create  index ES_UN_DOCENTE_FK on DOCENTE (
ID_USUARIO
);

/*==============================================================*/
/* Table: INSCRIPCION                                           */
/*==============================================================*/
create table INSCRIPCION (
   ID_INSCRIPCION       INT4                 not null,
   ID_ALUMNO            INT4                 null,
   ID_MATERIA           INT4                 null,
   FECHA_INSCRIPCION    DATE                 not null,
   CODIGO_ACCESO        VARCHAR(30)          not null,
   constraint PK_INSCRIPCION primary key (ID_INSCRIPCION)
);

/*==============================================================*/
/* Index: INSCRIPCION_PK                                        */
/*==============================================================*/
create unique index INSCRIPCION_PK on INSCRIPCION (
ID_INSCRIPCION
);

/*==============================================================*/
/* Index: TRAMITA_FK                                            */
/*==============================================================*/
create  index TRAMITA_FK on INSCRIPCION (
ID_ALUMNO
);

/*==============================================================*/
/* Index: ASOCIADA_FK                                           */
/*==============================================================*/
create  index ASOCIADA_FK on INSCRIPCION (
ID_MATERIA
);

/*==============================================================*/
/* Table: MATERIA                                               */
/*==============================================================*/
create table MATERIA (
   ID_MATERIA           INT4                 not null,
   NOMBRE_MATERIA       VARCHAR(30)          not null,
   AREA_MATERIA         VARCHAR(30)          not null,
   NIVEL_MATERIA        VARCHAR(30)          not null,
   constraint PK_MATERIA primary key (ID_MATERIA)
);

/*==============================================================*/
/* Index: MATERIA_PK                                            */
/*==============================================================*/
create unique index MATERIA_PK on MATERIA (
ID_MATERIA
);

/*==============================================================*/
/* Table: MATERIAL                                              */
/*==============================================================*/
create table MATERIAL (
   ID_MATERIAL          INT4                 not null,
   ID_MATERIA           INT4                 null,
   FORMATO_MATERIAL     VARCHAR(10)          not null,
   URL_MATERIAL         VARCHAR(30)          not null,
   FECHA_ENVIO_MATERIAL DATE                 not null,
   constraint PK_MATERIAL primary key (ID_MATERIAL)
);

/*==============================================================*/
/* Index: MATERIAL_PK                                           */
/*==============================================================*/
create unique index MATERIAL_PK on MATERIAL (
ID_MATERIAL
);

/*==============================================================*/
/* Index: PROPORCIONA_FK                                        */
/*==============================================================*/
create  index PROPORCIONA_FK on MATERIAL (
ID_MATERIA
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USUARIO           INT4                 not null,
   NOMBRES              VARCHAR(30)          not null,
   APELLIDO_PATERNO     VARCHAR(30)          not null,
   APELLIDO_MATERNO     VARCHAR(30)          not null,
   FECHA_NACIMIENTO     DATE                 not null,
   EMAIL                VARCHAR(50)          not null,
   constraint PK_USUARIO primary key (ID_USUARIO)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
ID_USUARIO
);

alter table ALUMNO
   add constraint FK_ALUMNO_ES_UN_ALU_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table ANUNCIO
   add constraint FK_ANUNCIO_PUBLICA_MATERIA foreign key (ID_MATERIA)
      references MATERIA (ID_MATERIA)
      on delete restrict on update restrict;

alter table ASIGNACION
   add constraint FK_ASIGNACI_CORRESPON_MATERIA foreign key (ID_MATERIA)
      references MATERIA (ID_MATERIA)
      on delete restrict on update restrict;

alter table ASIGNACION
   add constraint FK_ASIGNACI_REGISTRAD_DOCENTE foreign key (ID_DOCENTE)
      references DOCENTE (ID_DOCENTE)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_REGISTRAD_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table DOCENTE
   add constraint FK_DOCENTE_ES_UN_DOC_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table INSCRIPCION
   add constraint FK_INSCRIPC_ASOCIADA_MATERIA foreign key (ID_MATERIA)
      references MATERIA (ID_MATERIA)
      on delete restrict on update restrict;

alter table INSCRIPCION
   add constraint FK_INSCRIPC_TRAMITA_ALUMNO foreign key (ID_ALUMNO)
      references ALUMNO (ID_ALUMNO)
      on delete restrict on update restrict;

alter table MATERIAL
   add constraint FK_MATERIAL_PROPORCIO_MATERIA foreign key (ID_MATERIA)
      references MATERIA (ID_MATERIA)
      on delete restrict on update restrict;

