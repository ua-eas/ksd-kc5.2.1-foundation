insert into FP_DOC_TYPE_T (FDOC_TYP_CD, VER_NBR, FDOC_NM, FDOC_TYP_ACTIVE_CD)
values ('AWRD', 1, 'AWARD', 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 1, 'Y', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 2, 'N', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 3, 'N', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 4, 'Y', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 5, 'N', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 6, 'N', sysdate, 'quickstart', 1, 'Y');

insert into CUSTOM_ATTRIBUTE_DOCUMENT (DOCUMENT_TYPE_CODE, CUSTOM_ATTRIBUTE_ID, IS_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, ACTIVE_FLAG)
values ('AWRD', 7, 'N', sysdate, 'quickstart', 1, 'Y');



commit;