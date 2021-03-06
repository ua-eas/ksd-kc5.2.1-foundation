CREATE TABLE LOCATION_TYPE
(
   LOCATION_TYPE_CODE number(3) NOT NULL,
   LOCATION_TYPE_DESC varchar2(200) NOT NULL,
   UPDATE_TIMESTAMP date NOT NULL,
   UPDATE_USER varchar2(8) NOT NULL
)
;

alter table location_type
add constraint pk_location_type primary key (location_type_code);

INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (1,'Proposal Organization',to_date('2008-08-08','YYYY-MM-DD'),'KRADEV');
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (2,'Performing Organization',to_date('2008-08-08','YYYY-MM-DD'),'KRADEV');
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (3,'Other Organization',to_date('2008-08-08','YYYY-MM-DD'),'KRADEV');
INSERT INTO LOCATION_TYPE (LOCATION_TYPE_CODE,LOCATION_TYPE_DESC,UPDATE_TIMESTAMP,UPDATE_USER) VALUES (4,'Performance Site',to_date('2008-08-08','YYYY-MM-DD'),'KRADEV');
