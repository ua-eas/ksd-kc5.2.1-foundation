ALTER TABLE QUESTION RENAME COLUMN ANSWER_DATA_TYPE TO QUESTION_TYPE_ID;
ALTER TABLE QUESTION MODIFY QUESTION_TYPE_ID NUMBER(12,0); 

CREATE OR REPLACE VIEW OSP$QUESTION AS SELECT 
    A.QUESTION_ID, 
    A.QUESTION, 
    A.MAX_ANSWERS, 
    A.VALID_ANSWER, 
    A.LOOKUP_NAME, 
    B.QUESTION_TYPE_NAME AS ANSWER_DATA_TYPE, 
    A.ANSWER_MAX_LENGTH, 
    A.LOOKUP_GUI, 
    A.UPDATE_TIMESTAMP, 
    A.UPDATE_USER, 
    A.GROUP_TYPE_CODE
FROM QUESTION A,
     QUESTION_TYPES B
WHERE A.QUESTION_TYPE_ID = B.QUESTION_TYPE_ID;
