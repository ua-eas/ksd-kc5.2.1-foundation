
  CREATE OR REPLACE PROCEDURE "GET_PROP_NARR_PDF_FOR_PROP" 
   ( AW_PROPOSAL_NUMBER IN OSP$NARRATIVE_PDF.PROPOSAL_NUMBER%TYPE,
--     AW_USER_ID IN OSP$NARRATIVE_USER_RIGHTS.USER_ID%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for

SELECT NR.PROPOSAL_NUMBER,
   		 NR.MODULE_NUMBER,
          --FILE_NAME,
		  NR.MODULE_TITLE FILE_NAME,
          NPD.UPDATE_TIMESTAMP,
          NPD.UPDATE_USER
     FROM OSP$NARRATIVE_PDF NPD,OSP$NARRATIVE NR
     WHERE NR.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
		  AND NR.PROPOSAL_NUMBER = NPD.PROPOSAL_NUMBER
		  AND NR.MODULE_NUMBER = NPD.MODULE_NUMBER
		  AND NR.MODULE_SEQUENCE_NUMBER = (SELECT max(NR.MODULE_SEQUENCE_NUMBER) from OSP$NARRATIVE
		  	  							    WHERE PROPOSAL_NUMBER = NPD.PROPOSAL_NUMBER
											AND MODULE_NUMBER = NPD.MODULE_NUMBER);

-- 		SELECT OSP$NARRATIVE_PDF.PROPOSAL_NUMBER,
-- 			OSP$NARRATIVE_PDF.MODULE_NUMBER		,
-- 		 	OSP$NARRATIVE_PDF.FILE_NAME 			,
-- 			OSP$NARRATIVE_PDF.UPDATE_USER,
-- 			OSP$NARRATIVE_PDF.UPDATE_TIMESTAMP
-- 		FROM OSP$NARRATIVE_PDF, OSP$NARRATIVE_USER_RIGHTS
-- 		WHERE OSP$NARRATIVE_PDF.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
-- 			AND OSP$NARRATIVE_PDF.PROPOSAL_NUMBER = OSP$NARRATIVE_USER_RIGHTS.PROPOSAL_NUMBER
-- 			AND OSP$NARRATIVE_PDF.MODULE_NUMBER = OSP$NARRATIVE_USER_RIGHTS.MODULE_NUMBER
-- 			AND UPPER(OSP$NARRATIVE_USER_RIGHTS.USER_ID) = UPPER(AW_USER_ID)
-- 			AND OSP$NARRATIVE_USER_RIGHTS.ACCESS_TYPE <> 'N';

end;
 
/
 