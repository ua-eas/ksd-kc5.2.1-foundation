/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* Table Script */ 
CREATE TABLE AWARD_SPECIAL_REVIEW ( 
	AWARD_SPECIAL_REVIEW_ID NUMBER(12,0) NOT NULL, 
	AWARD_ID NUMBER(12,0) NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	SPECIAL_REVIEW_NUMBER NUMBER(3,0) NOT NULL, 
	SPECIAL_REVIEW_CODE NUMBER(3,0) NOT NULL, 
	APPROVAL_TYPE_CODE NUMBER(3,0) NOT NULL, 
	PROTOCOL_NUMBER VARCHAR2(20), 
	APPLICATION_DATE DATE, 
	APPROVAL_DATE DATE, 
	COMMENTS CLOB, 
	UPDATE_USER VARCHAR2(60) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL);
/* Primary Key Constraint */ 
ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT PK_AWARD_SPECIAL_REVIEW 
PRIMARY KEY (AWARD_SPECIAL_REVIEW_ID);
/* *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT PK_AWARD_SPECIAL_REVIEW 
PRIMARY KEY (MIT_AWARD_NUMBER, SEQUENCE_NUMBER, SPECIAL_REVIEW_NUMBER);
*************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ */ 
 

/* *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************  
ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT UQ_AWARD_SPECIAL_REVIEW 
UNIQUE (MIT_AWARD_NUMBER, SEQUENCE_NUMBER, SPECIAL_REVIEW_NUMBER);
*/

/* Foreign Key Constraint(s) */ 
ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT FK_AWARD_SPECIAL_REVIEW 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID);
/* *************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ 
ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT FK_AWARD_SPECIAL_REVIEW 
FOREIGN KEY (MIT_AWARD_NUMBER, SEQUENCE_NUMBER) 
REFERENCES OSP$AWARD (MIT_AWARD_NUMBER, SEQUENCE_NUMBER);
*************** MODIFIED FOREIGN KEY COLUMN - Composite keys are removed from KCRA ************ */ 

/* Sequence for AWARD_SPECIAL_REVIEW table */
CREATE SEQUENCE SEQ_AWARD_SPECIAL_REVIEW_ID INCREMENT BY 1 START WITH 1;

CREATE TABLE AWARD_EXEMPT_NUMBER (	
		AWARD_EXEMPT_NUMBER_ID  NUMBER(12,0) NOT NULL,
   		AWARD_SPECIAL_REVIEW_ID NUMBER(12,0) NOT NULL, 
		EXEMPTION_TYPE_CODE VARCHAR2(3 BYTE) NOT NULL ENABLE, 
		UPDATE_USER VARCHAR2(60 BYTE) NOT NULL ENABLE, 
		UPDATE_TIMESTAMP DATE NOT NULL ENABLE, 
		VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE, 
		OBJ_ID VARCHAR2(36 BYTE) DEFAULT SYS_GUID() NOT NULL ENABLE
		);
/* Primary Key Constraint */ 
ALTER TABLE AWARD_EXEMPT_NUMBER 
ADD CONSTRAINT PK_AWARD_EXEMPT_NUMBER 
PRIMARY KEY (AWARD_EXEMPT_NUMBER_ID);
/* Foreign Key Constraint(s) */ 
ALTER TABLE AWARD_EXEMPT_NUMBER 
ADD CONSTRAINT FK_AWARD_SPECIAL_REVIEW_ID 
FOREIGN KEY (AWARD_SPECIAL_REVIEW_ID) 
REFERENCES AWARD_SPECIAL_REVIEW (AWARD_SPECIAL_REVIEW_ID);
ALTER TABLE AWARD_EXEMPT_NUMBER 
ADD CONSTRAINT FK_EXEMPTION_TYPE_CODE 
FOREIGN KEY (EXEMPTION_TYPE_CODE) 
REFERENCES EXEMPTION_TYPE (EXEMPTION_TYPE_CODE);
/* Sequence for AWARD_EXEMPT_NUMBER table */
CREATE SEQUENCE SEQ_AWARD_EXEMPT_NUMBER_ID INCREMENT BY 1 START WITH 1;
