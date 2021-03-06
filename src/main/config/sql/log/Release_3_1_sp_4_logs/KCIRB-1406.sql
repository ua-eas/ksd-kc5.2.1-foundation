
INSERT INTO KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_ID_S.nextval,'KC-UNT','Modify all Protocols','Modify all Protocols',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',NULL,1,sys_guid());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Create Any Amendment','Create amendments on any protocol',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Create Any Renewal','Create renewals for any protocol',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Submit Any Protocol','Submit Any Protocol',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Maintain Protocol Review Comments','Maintain Protocol Review Comments',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Maintain Protocol Related Proj','Maintain Protocols link to award and proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Maintain Any Protocol Access','Maintain Any Protocol Access',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1,SYS_GUID());

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_PERM_ID_S.NEXTVAL,'KC-PROTOCOL','Add Any Protocol Notes','Add Any Protocol Notes',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Any Renewal'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Any Amendment'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Submit Any Protocol'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Review Comments'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Related Proj'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Any Protocol Access'),
'Y',1,SYS_GUID());

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Add Any Protocol Notes'),
'Y',1,SYS_GUID());


Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Modify all Protocols'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Any Protocol'),
'Y',1,SYS_GUID());