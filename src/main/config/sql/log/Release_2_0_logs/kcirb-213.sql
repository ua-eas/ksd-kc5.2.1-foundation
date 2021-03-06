CREATE TABLE COMM_RESEARCH_AREAS (
    ID NUMBER(12, 0),
    COMMITTEE_ID NUMBER(12,0) NOT NULL,
    RESEARCH_AREA_CODE VARCHAR2(8) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);

ALTER TABLE COMM_RESEARCH_AREAS 
ADD CONSTRAINT PK_COMM_RESEARCH_AREAS 
PRIMARY KEY (ID);

ALTER TABLE COMM_RESEARCH_AREAS 
ADD CONSTRAINT FK_COMM_RESEARCH_AREAS1
FOREIGN KEY (COMMITTEE_ID) 
REFERENCES COMMITTEE (ID);

ALTER TABLE COMM_RESEARCH_AREAS 
ADD CONSTRAINT FK_COMM_RESEARCH_AREAS2
FOREIGN KEY (RESEARCH_AREA_CODE) 
REFERENCES RESEARCH_AREAS (RESEARCH_AREA_CODE);

CREATE SEQUENCE SEQ_COMM_RESEARCH_AREAS_ID INCREMENT BY 1 START WITH 1;

CREATE OR REPLACE VIEW OSP$COMM_RESEARCH_AREAS AS SELECT 
  c.COMMITTEE_ID,
  cra.RESEARCH_AREA_CODE,
  cra.UPDATE_TIMESTAMP, 
  cra.UPDATE_USER
FROM COMM_RESEARCH_AREAS cra, COMMITTEE c
where cra.COMMITTEE_ID = c.ID;

COMMIT;
