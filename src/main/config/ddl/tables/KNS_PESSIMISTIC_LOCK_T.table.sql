CREATE TABLE KNS_PESSIMISTIC_LOCK_T (
        LOCK_ID                        NUMBER(14) CONSTRAINT KNS_PESSIMISTIC_LOCK_TN1 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT KNS_PESSIMISTIC_LOCK_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT KNS_PESSIMISTIC_LOCK_TN3 NOT NULL,
        LOCK_DESCRIPTOR                VARCHAR2(4000)
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT KNS_PESSIMISTIC_LOCK_TN4 NOT NULL,             
        LOCK_GENERATED_TS              DATE CONSTRAINT KNS_PESSIMISTIC_LOCK_TN5 NOT NULL,
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT KNS_PESSIMISTIC_LOCK_TN6 NOT NULL,
     CONSTRAINT KNS_PESSIMISTIC_LOCK_TP1 PRIMARY KEY (LOCK_ID),
     CONSTRAINT KNS_PESSIMISTIC_LOCK_TC0 UNIQUE (OBJ_ID)
);
