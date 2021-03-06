drop table VISAINC if exists;
drop table IMPORTFILES if exists;
drop table CARDBRAND if exists;

CREATE TABLE VISAINC  (
	SEQ_NO BIGINT NOT NULL PRIMARY KEY,
	PROC_DATE CHAR(8) NOT NULL,
	TRXN_CD DECIMAL(2,0) ,
	CD_QFY DECIMAL(1,0) ,
	TRXN_SEQ DECIMAL(1,0) ,
	FILLER1 CHAR(54) ,
	RPT_GRP CHAR(1) ,
	CORP_RPT_ID CHAR(2) ,
	FILLER2 CHAR(85) ,
	USAGE CHAR(1) ,
	FILLER3 CHAR(21) ,
	CHECKSUM VARCHAR(20)
);

CREATE TABLE IMPORTFILES  (
	CARDBRAND_ID CHAR(1) NOT NULL PRIMARY KEY,
	IMPORT_FILENAME_PREFIX VARCHAR(120),
	CHECKSUM VARCHAR(20)
);

CREATE TABLE CARDBRAND  (
	CARDBRAND_ID CHAR(1) NOT NULL PRIMARY KEY,
	CARDBRAND_DESCRIPTION VARCHAR(120),
	CHECKSUM VARCHAR(20)
);
