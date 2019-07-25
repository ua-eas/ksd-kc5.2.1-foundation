/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol.onlinereview;

import org.apache.ojb.broker.query.Criteria;
import org.drools.core.util.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.protocol.onlinereview.lookup.ProtocolOnlineReviewLookupConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

//import org.kuali.rice.core.framework.persistence.platform;
//import org.kuali.rice.core.framework.persistence;


///

//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.ojb.broker.query.*;
//import org.kuali.kra.protocol.actions.ProtocolActionBase;
//import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
//import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
//import org.kuali.kra.protocol.noteattachment.TypedAttachment;
//import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
//import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
//import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
//import org.kuali.rice.kns.service.DataDictionaryService;
//import org.kuali.rice.krad.bo.PersistableBusinessObject;
//import org.kuali.rice.krad.dao.LookupDao;
//import org.kuali.rice.krad.service.util.OjbCollectionAware;
//import org.kuali.rice.krad.util.KRADConstants;
//
//import java.sql.Date;
//import java.util.*;
//import java.util.Map.Entry;

///

//import org.apache.commons.lang.StringUtils;
//import org.kuali.rice.kew.api.KewApiConstants;
//import org.kuali.rice.kns.lookup.HtmlData;
//import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
//import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
//import org.kuali.rice.kns.web.struts.form.LookupForm;
//import org.kuali.rice.kns.web.ui.Field;
//import org.kuali.rice.krad.bo.BusinessObject;
//import org.kuali.rice.krad.document.Document;
//import org.kuali.rice.krad.util.KRADConstants;
//import org.kuali.rice.krad.util.ObjectUtils;
//import org.kuali.rice.krad.util.UrlFactory;
//
//import java.util.*;
//import java.util.Map.Entry;

///
//
//import org.kuali.rice.core.framework.persistence.jdbc.sql;
//import org.kuali.rice.core.framework.persistence.platform.DatabasePlatform;
//
//import org.kuali.rice.core.framework.persistence.jdbc.sql.getDbPlatform;


import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
//import org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewBase;
//import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
//import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;

import org.apache.ojb.broker.query.*;

//import org.apache.ojb.broker.query.


//////////


//import org.kuali.kra.dao.KraLookupDao;


import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

// I thought I'd get getPersistenceBrokerTemplate() from here??
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;



import org.kuali.kra.award.subcontracting.reporting.dao.SubcontractingExpenditureCategoryDetailsDao;
import org.kuali.kra.protocol.onlinereview.dao.ProtocolOnlineReviewDao;
import org.kuali.kra.protocol.onlinereview.dao.ProtocolOnlineReviewDaoOjb;

// ////



import java.text.ParseException;
import java.util.*;

public abstract class ProtocolOnlineReviewLookupableHelperServiceImplBase extends KraLookupableHelperServiceImpl {


    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7269604308213091097L;


    private DictionaryValidationService dictionaryValidationService;
    
    //field names
    private static final String REVIEWER_EMPLOYEE="lookupReviewerPersonId";
    private static final String REVIEWER_NONEMPLOYEE="lookupReviewerRolodexId";
    private static final String LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES="lookupProtocolOnlineReviewStatusCode";
    private static final String PROTOCOL_NUMBER="lookupProtocol.protocolNumber";
    private static final String PROTOCOL_REVIEW_DUE_DATE="lookupDateDue";
    //translateed field names to object graph
    private static final String OBJ_PROTOCOLREVIEWER_REVIEWER_EMPLOYEE="protocolReviewer.personId";
    private static final String OBJ_PROTOCOLREVIEWER_NONEMPLOYEE="protocolReviewer.personId";
    private static final String OBJ_PROTOCOL_PROTOCOL_NUMBER="protocol.protocolNumber";
    private static final String OBJ_PROTOCOL_ONLINE_REVIEW_STATUS_CODE="protocolOnlineReviewStatusCode";
    private static final String OBJ_PROTOCOL_REVIEW_DUE_DATE="protocol.dateDue";    
    
    @Override
    protected abstract String getDocumentTypeName();

    @Override
    protected abstract String getHtmlAction();

    @Override
    protected String getKeyFieldName() {
        return "protocolOnlineReviewId";
    }

    private ProtocolOnlineReviewDao protocolOnlineReviewDao;

    public void setProtocolOnlineReviewDao(ProtocolOnlineReviewDao protocolOnlineReviewDao) {
        this.protocolOnlineReviewDao = protocolOnlineReviewDao;
    }

    public ProtocolOnlineReviewDao getProtocolOnlineReviewDao() {
        return protocolOnlineReviewDao;
    }


//    protected KraLookupDao kraLookupDao;
//
//    /**
//     * Set the KRA Lookup DAO.
//     * @param kraLookupDao
//     */
//    public void setKraLookupDao(KraLookupDao kraLookupDao) {
//        this.kraLookupDao = kraLookupDao;
//    }
//
//
//    /**
//     * Set the KRA Lookup DAO.
//     * @param kraLookupDao
//     */
//    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
//        this.protocolFinderDao = protocolFinderDao;
//    }
//


   /**
    * 
    * This method returns an instance of a DictionaryValidationService implementation
    * @return
    */
   public DictionaryValidationService getDictionaryValidationService() {
       if (dictionaryValidationService == null) {
           dictionaryValidationService =  KNSServiceLocator.getKNSDictionaryValidationService();
       }
       return dictionaryValidationService;
   }

   protected boolean validateDate(String dateFieldName, String dateFieldValue) {
       try{
           CoreApiServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
           return true;
       } catch (ParseException e) {
           GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
           return false;
       } catch (Exception e) {
           e.printStackTrace();
           GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
           return false;
       }
   }

   @Override
   public void validateSearchParameters(Map fieldValues) {
       Map<String,String> fvalues = (Map<String,String>)fieldValues;
       super.validateSearchParameters(fieldValues);
       Set<String> keys = fieldValues.keySet();
       for (String key : keys) {
           String value = fieldValues.get(key).toString();
           if (key.toUpperCase().indexOf("DATE") > 0) {
               //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
               if(value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                   if( !StringUtils.isEmpty(value)) {
                       validateDate(key, value);
                   }
               }
           }
       }
       

       if (!StringUtils.isEmpty(fvalues.get(REVIEWER_NONEMPLOYEE)) && !StringUtils.isEmpty(fvalues.get(REVIEWER_EMPLOYEE))) {
           //we can only search for one at a time.
           GlobalVariables.getMessageMap().putError(ProtocolOnlineReviewLookupConstants.Property.REVIEWER_NONEMPLOYEE, KeyConstants.ERROR_PROTOCOL_ONLINE_REVIEW_INVALID_ONE_PERSON_ONLY);
           
       } 
       
   }
  
   @Override
   public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
       validateSearchParameters(fieldValues);
       List<ProtocolOnlineReviewBase> results;

       if (!StringUtils.isEmpty(fieldValues.get(REVIEWER_EMPLOYEE))) {
           fieldValues.put(OBJ_PROTOCOLREVIEWER_REVIEWER_EMPLOYEE, fieldValues.get(REVIEWER_EMPLOYEE));
       } else if (!StringUtils.isEmpty(fieldValues.get(REVIEWER_NONEMPLOYEE))) {
           fieldValues.put(OBJ_PROTOCOLREVIEWER_NONEMPLOYEE, fieldValues.get(REVIEWER_NONEMPLOYEE));
       }
       
       if (!StringUtils.isEmpty(fieldValues.get(PROTOCOL_REVIEW_DUE_DATE))) {
           fieldValues.put(OBJ_PROTOCOL_REVIEW_DUE_DATE, fieldValues.get(PROTOCOL_REVIEW_DUE_DATE));
       }
       
       if (!StringUtils.isEmpty(fieldValues.get(PROTOCOL_NUMBER))) {
           fieldValues.put( OBJ_PROTOCOL_PROTOCOL_NUMBER, fieldValues.get(PROTOCOL_NUMBER));
       }
       
       if (!StringUtils.isEmpty(fieldValues.get(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES))) {
           fieldValues.put(OBJ_PROTOCOL_ONLINE_REVIEW_STATUS_CODE, fieldValues.get(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES));
       }
       
       fieldValues.remove(PROTOCOL_REVIEW_DUE_DATE);
       fieldValues.remove(REVIEWER_NONEMPLOYEE);
       fieldValues.remove(REVIEWER_EMPLOYEE);
       fieldValues.remove(PROTOCOL_NUMBER);
       fieldValues.remove(LOOKUP_PROTOCOL_ONLINE_REVIEW_STATUS_CODES);

       super.setBackLocationDocFormKey(fieldValues);

       if (((String)fieldValues.get("rangeLowerBoundKeyPrefix_dateDue")).isEmpty() &&
               ((String)fieldValues.get("rangeLowerBoundKeyPrefix_dateRequested")).isEmpty()) {
           results = (List<ProtocolOnlineReviewBase>)this.getCustomSearchResults(fieldValues);
       } else {
           results = (List<ProtocolOnlineReviewBase>)super.getSearchResults(fieldValues);
       }


//       results = (List<ProtocolOnlineReviewBase>)super.getSearchResults(fieldValues);
       return filterResults(results);
   }

   protected abstract List<ProtocolOnlineReviewBase> filterResults(List<ProtocolOnlineReviewBase> results);
   
   protected abstract String getProtocolSubmissionApprovedStatusCodeHook();

  
    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
        ProtocolOnlineReviewBase protocolOnlineReview = (ProtocolOnlineReviewBase) businessObject;
        
        if (getProtocolOLRSavedStatusCodeHook().equals(protocolOnlineReview.getProtocolOnlineReviewStatusCode())) {
            htmlDataList.add(getEditLink(protocolOnlineReview.getProtocolOnlineReviewDocument()));
        } else {
            htmlDataList.add(getViewLink(protocolOnlineReview.getProtocolOnlineReviewDocument()));
        }
    }
    
    protected abstract String getProtocolOLRSavedStatusCodeHook();

    @Override
    protected AnchorHtmlData getViewLink(Document document) {
        Properties parameters = getLinkProperties(document);
        parameters.put("viewDocument", "true");
        String displayText = "view";
        
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private AnchorHtmlData getEditLink(Document document) {
        Properties parameters = getLinkProperties(document);
        String displayText = KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL;
        
        return getAnchorHtmlData(parameters, displayText);
    }
    
    private Properties getLinkProperties(Document document) {
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "redirectToProtocolFromReview");
        parameters.put(KRADConstants.PARAMETER_COMMAND, "displayDocSearchView");
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("docId", document.getDocumentNumber());
        return parameters;
    }
    
    private AnchorHtmlData getAnchorHtmlData(Properties parameters, String displayText) {
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        return new AnchorHtmlData(href, KRADConstants.DOC_HANDLER_METHOD, displayText);
    }






    private List<ProtocolOnlineReviewBase> getCustomSearchResults(Map<String, String> fieldValues) {
//
//        Collection <ProtocolOnlineReview> searchResults = new ArrayList();
//
        List<ProtocolOnlineReviewBase> searchResults = new ArrayList();
//
//    private List<ProtocolOnlineReview> getCustomSearchResults(Map<String, String> fieldValues) {
//        List<ProtocolOnlineReview> searchResults = new ArrayList();

        Long matchingResultsCount = null;



//        --- Base SQL for Existing OJB Criteria
//        --- UPPER(protocolReviewer.personId) LIKE 115605455407, dateDue <= 2019-07-18, UPPER(protocolOnlineReviewStatusCode) LIKE S
//        SELECT *
//                FROM PROTOCOL_ONLN_RVWS
//        WHERE UPPER(PROTOCOL_REVIEWER_FK) IN (SELECT Distinct PROTOCOL_REVIEWER_ID FROM "PROTOCOL_REVIEWERS" WHERE PERSON_ID='115605455407')
//        AND DATE_DUE  <= '23-JUL-19'
//        AND UPPER(PROTOCOL_ONLN_RVW_STATUS_CODE) LIKE (SELECT STATUS_CODE FROM PROTOCOL_ONLN_RVW_STATUS WHERE DESCRIPTION = 'SAVED')
//        ORDER BY DATE_DUE DESC

        Criteria criteria1 = new Criteria();
        criteria1.addLike("UPPER(protocolReviewer.personId)", fieldValues.get("protocolReviewer.personId").toUpperCase());
        if (!((String)fieldValues.get("dateDue")).isEmpty() &&
                ((String)fieldValues.get("dateDue")).startsWith("<=")) {
            String dateDueString = (fieldValues.get("dateDue")).substring(2);
            criteria1.addLessOrEqualThanField("dateDue", dateDueString);
            criteria1.addOrderByDescending("dateDue");
        }

        if (!((String)fieldValues.get("dateRequested")).isEmpty() &&
                ((String)fieldValues.get("dateRequested")).startsWith("<=")) {
            String dateRequestedString = (fieldValues.get("dateRequested")).substring(2);
            criteria1.addLessOrEqualThanField("dateRequested", dateRequestedString);
            criteria1.addOrderByDescending("dateRequested");
        }

        criteria1.addLike("UPPER(protocolOnlineReviewStatusCode)", fieldValues.get("protocolOnlineReviewStatusCode").toUpperCase());

        ReportQueryByCriteria porQuery1;

        // Above is basic currently existing OJB criteria

        // Below Add filterresults criteria
        Criteria criteria2 = criteria1; /// Make a copy of the base criteria for reuse


//        ---- Filter Query 1 subquery
//        SELECT Count(*)
//        FROM PROTOCOL_ONLN_RVWS POR
//        WHERE UPPER(PROTOCOL_REVIEWER_FK) IN (SELECT Distinct PROTOCOL_REVIEWER_ID FROM "PROTOCOL_REVIEWERS" WHERE PERSON_ID='115605455407')
//        AND DATE_DUE  <= '23-JUL-19'
//                -- AND UPPER(POR.PROTOCOL_ONLN_RVW_STATUS_CODE) LIKE (SELECT STATUS_CODE FROM PROTOCOL_ONLN_RVW_STATUS WHERE DESCRIPTION = 'SAVED')
//                -- AND UPPER(POR.PROTOCOL_ONLN_RVW_STATUS_CODE) LIKE ProtocolOnlineReviewStatus.SAVED_STATUS_CD
//        AND REVIEWER_APPROVED = 'Y'
//        AND ADMIN_ACCEPTED = 'Y'
//        AND PROTOCOL_ONLN_RVW_STATUS_CODE='F'-- ProtocolOnlineReviewStatus.FINAL_STATUS_CD
//        AND ( SUBMISSION_ID_FK  IN ( SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION PS WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM "KRAOWNER"."SUBMISSION_STATUS" WHERE DESCRIPTION IN ('Approved','Exemption Granted','Specific Minor Revisions Requested','Substantive Revisions Required','Returned To PI') ) )
//        )
//        ORDER BY DATE_DUE DESC
//        /*
//        COUNT(*)
//        6357
//        */


//        ReportQueryByCriteria psQuery1A;
//        psQuery1A = QueryFactory.newReportQuery(ProtocolSubmission.class, criteria1);

        criteria1.addLike("UPPER(protocolOnlineReview.reviewerApproved)", "Y");
        criteria1.addLike("UPPER(protocolOnlineReview.adminAccepted)", "Y");
        criteria1.addLike("UPPER(protocolOnlineReview.protocolOnlineReviewStatusCode)", "F");
//        criteria1.addLike("UPPER(protocolOnlineReview.protocolOnlineReviewStatusCode)", ProtocolOnlineReviewStatus.FINAL_STATUS_CD);

//        public static final String PROTOCOL_SUBMISSION_SUB_QUERY="SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION " +
//                "WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM SUBMISSION_STATUS " +
//                "WHERE DESCRIPTION IN ('Approved','Exemption Granted','Specific Minor Revisions Requested', " +
//                "'Substantive Revisions Required','Returned To PI') )";
//
//        String PROTOCOL_SUBMISSION_SUB_QUERY="SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION " +
//                "WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM SUBMISSION_STATUS " +
//                "WHERE DESCRIPTION IN ('Approved','Exemption Granted','Specific Minor Revisions Requested', " +
//                "'Substantive Revisions Required','Returned To PI') )";

        String PROTOCOL_SUBMISSION_SUB_QUERY="SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION " +
                "WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM SUBMISSION_STATUS " +
                "WHERE DESCRIPTION IN ('Approved','Exemption Granted','Specific Minor Revisions Requested', " +
                "'Substantive Revisions Required','Returned To PI') )";


//        "select negotiation_id from (" +
//                " select negotiation_id, NEGOTIATION_START_DATE, (case when NEGOTIATION_END_DATE IS NULL then SYSDATE else NEGOTIATION_END_DATE end) AS NEGOTIATION_END_DATE from negotiation )" +
//                " where ";

        /// Using example org.kuali.kra.negotiations.lookup.NegotiationDaoOjb#getNegotiationAgeCriteria
        //create subquery for the associated object with the above criteria
        StringBuffer protocolSubmissionSqlQuery = new StringBuffer(PROTOCOL_SUBMISSION_SUB_QUERY);

        QueryBySQL ps1sqlSubQuery = QueryFactory.newQuery(ProtocolSubmission.class, protocolSubmissionSqlQuery.toString());

        criteria1.addIn("SUBMISSION_ID_FK", ps1sqlSubQuery);
        criteria1.addIn
//        criteria1.addIn(ProtocolOnlineReview.submissionIdFk, ps1sqlSubQuery);


        porQuery1 = QueryFactory.newReportQuery(ProtocolOnlineReview.class, criteria1);


//        //add orderBy NegotiationId to show the newest on top to query and DISTINCT = true to avoid duplicate results.
//        QueryByCriteria query = QueryFactory.newQuery(ProtocolOnlineReview.class, searchCriteria, Boolean.TRUE);
//        query.addOrderByDescending(NEGOTIATION_ID);
//        matchingResultsCount = new Long(getPersistenceBrokerTemplate().getCount(query));


//        if (!((String)fieldValues.get("dateDue")).isEmpty() &&
//                ((String)fieldValues.get("dateDue")).startsWith("<=")) {
////            criteria1.addLessOrEqualThanField("dateDue", fieldValues.get("dateDue"));
//            porQuery1.addOrderByDescending("dateDue");
//        }
//
//        if (!((String)fieldValues.get("dateRequested")).isEmpty() &&
//                ((String)fieldValues.get("dateRequested")).startsWith("<=")) {
////            criteria1.addLessOrEqualThanField("dateRequested", fieldValues.get("dateRequested"));
//            porQuery1.addOrderByDescending("dateRequested");
//        }

//        searchResults = getPersistenceBrokerTemplate().getCollectionByQuery(porQuery1);

//        searchResults = getProtocolOnlineReviewDao().getProtocolOnlineReviewsByQuery();
        searchResults = getProtocolOnlineReviewDao().getProtocolOnlineReviewsByQuery(porQuery1);


//        kraLookupDao.




        // Do Filter Query 2
//        ---- Filter Query 2 subquery
//        SELECT *
//                FROM PROTOCOL_ONLN_RVWS
//        WHERE UPPER(PROTOCOL_REVIEWER_FK) IN (SELECT Distinct PROTOCOL_REVIEWER_ID FROM "PROTOCOL_REVIEWERS" WHERE PERSON_ID='115605455407')
//        AND DATE_DUE  <= '23-JUL-19'
//        AND UPPER(PROTOCOL_ONLN_RVW_STATUS_CODE) LIKE (SELECT STATUS_CODE FROM PROTOCOL_ONLN_RVW_STATUS WHERE DESCRIPTION = 'SAVED')
//                -- AND UPPER(POR.PROTOCOL_ONLN_RVW_STATUS_CODE) LIKE ProtocolOnlineReviewStatus.SAVED_STATUS_CD
//        AND NOT(PROTOCOL_ONLN_RVW_STATUS_CODE='F')-- NOT(ProtocolOnlineReviewStatus.FINAL_STATUS_CD)
//        AND ( SUBMISSION_ID_FK  IN ( SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION PS WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM "KRAOWNER"."SUBMISSION_STATUS" WHERE DESCRIPTION IN ('In Agenda','Submitted to Committee')))
//        )
//        ORDER BY DATE_DUE DESC
//        /*
//        PROTOCOL_ONLN_RVW_ID	DOCUMENT_NUMBER	PROTOCOL_ID	SUBMISSION_ID_FK	PROTOCOL_REVIEWER_FK	PROTOCOL_ONLN_RVW_STATUS_CODE	REVIEW_DETERM_RECOM_CD	DATE_REQUESTED	DATE_DUE	UPDATE_TIMESTAMP	UPDATE_USER	VER_NBR	OBJ_ID	ACTIONS_PERFORMED	REVIEWER_APPROVED	ADMIN_ACCEPTED
//        4749162	720915	4747383	4749159	4749161	S		27-JUN-19	09-JUL-19	01-JUL-19	jwinters1	0	7e1cb8b6-ddbb-4e05-b226-17af2a342051		N	N
//        ...
//        */



        criteria2.addNotLike("UPPER(protocolOnlineReview.protocolOnlineReviewStatusCode)", "F");

        String PROTOCOL_SUBMISSION_SUB_QUERY2="SELECT SUBMISSION_ID FROM PROTOCOL_SUBMISSION " +
                "WHERE SUBMISSION_STATUS_CODE IN (SELECT  DISTINCT SUBMISSION_STATUS_CODE FROM SUBMISSION_STATUS " +
                "WHERE DESCRIPTION IN ('In Agenda','Submitted to Committee') )";


        StringBuffer protocolSubmissionSqlQuery2 = new StringBuffer(PROTOCOL_SUBMISSION_SUB_QUERY2);

        QueryBySQL ps2sqlSubQuery = QueryFactory.newQuery(ProtocolSubmission.class, protocolSubmissionSqlQuery2.toString());

        criteria2.addIn("SUBMISSION_ID_FK", ps2sqlSubQuery);

        ReportQueryByCriteria porQuery2;
        porQuery2 = QueryFactory.newReportQuery(ProtocolOnlineReview.class, criteria2);

//        if (!((String)fieldValues.get("dateDue")).isEmpty() &&
//                ((String)fieldValues.get("dateDue")).startsWith("<=")) {
////            criteria2.addLessOrEqualThanField("dateDue", fieldValues.get("dateDue"));
//            porQuery2.addOrderByDescending("dateDue");
//        }
//
//        if (!((String)fieldValues.get("dateRequested")).isEmpty() &&
//                ((String)fieldValues.get("dateRequested")).startsWith("<=")) {
////            criteria2.addLessOrEqualThanField("dateRequested", fieldValues.get("dateRequested"));
//            porQuery2.addOrderByDescending("dateRequested");
//        }

//        searchResults.addAll( getPersistenceBrokerTemplate().getCollectionByQuery(porQuery2));

        searchResults.addAll( getProtocolOnlineReviewDao().getProtocolOnlineReviewsByQuery(porQuery2));


        return searchResults;


//        ((ArrayList<ProtocolOnlineReview>) searchResults).addAll()

//        return (List<ProtocolSubmissionBase>) getPersistenceBrokerTemplate().getCollectionByQuery(q);

//
//
//        porQuery1
//
//
//
//
//        ProtocolSubmission protocolSubmission =
//
//        ReportQueryByCriteria subQuery1A;
//        Criteria subCriteria1A = new Criteria();
//        subQuery1A = QueryFactory.newReportQuery(ProtocolSubmission.class, subCriteria1A);
//        subCriteria1A.addLike(protocolSubmission.getSubmissionStatusCode());
//
//
//        ReportQueryByCriteria pssQuery1B;
//        pssQuery1B = QueryFactory.newReportQuery(ProtocolSubmissionStatus.class, criteria1);
////        Criteria subCriteria1B = new Criteria();
////        subCriteria1B.addLike(ProtocolSubmissionStatus. .getSubmissionStatusCode());
////
////
//        List protocolSubmissionStatusList = new ArrayList<String>(
//                Arrays.asList(ProtocolSubmissionStatus.APPROVED,
//                        ProtocolSubmissionStatus.EXEMPT,
//                        ProtocolSubmissionStatus.SPECIFIC_MINOR_REVISIONS_REQUIRED,
//                        ProtocolSubmissionStatus.SUBSTANTIVE_REVISIONS_REQUIRED,
//                        ProtocolSubmissionStatus.RETURNED_TO_PI));
////        List protocolSubmissionStatusList = new ArrayList<String>(Arrays.asList("Approved","Exemption Granted","Specific Minor Revisions Requested","Substantive Revisions Required","Returned To PI"));
////        protocolSubmissionStatusList = ("Approved","Exemption Granted","Specific Minor Revisions Requested","Substantive Revisions Required","Returned To PI");
//        subCriteria1A.addColumnIn("SUBMISSION_STATUS_CODE", protocolSubmissionStatusList);
//
//        Criteria subCriteria1B = new Criteria();
//        subCriteria1B.addLike(ProtocolSubmissionStatusC .getSubmissionStatusCode());
//
//
//        criteria1.addColumnIn("SUBMISSION_ID_FK",(Collection) subCriteria1A);
//
//        Criteria subCriteria1B = new Criteria();
//
//
////
////        broker.getCollectionByQuery(porQuery);
//
//        Collection results = broker.getCollectionByQuery(porQuery);




        /// !!! Keep Above Below Scratch
//
//
//        org.kuali.rice.core.framework.persistence.platform..getUpperCaseFunction()
//        fieldValues.get("protocolReviewer.personId")
//
//
//        org.kuali.rice.core.framework.persistence.jdbc.sql.SqlBuilder.getDbPlatform()
//                PlatformAwareDaoBaseOjb.getDbPlatform()
//
//
//                SqlBuilder.getDbPlatform()
//
//        SqlBuilder.getDbPlatform()
//
//        // need to use upper case
//        String propertyName = getDbPlatform().getUpperCaseFunction() + "(" + fieldValues.get("protocolReviewer.personId") + ")";
//
//        String propertyName = "UPPER(" + fieldValues.get("protocolReviewer.personId") + ")";
//
//
//        criteria.addLike(propertyName, nameValue.toUpperCase());
//
//
//        criteria.addLike("UPPER(protocolReviewer.personId)", fieldValues.get("protocolReviewer.personId").toUpperCase()); /// !!!
//
//
//
//
//
//        fieldValues.get("dateDue")
//
//        fieldValues.get("protocolOnlineReviewStatusCode")
//
//        fieldValues.get("protocolReviewer.personId")



    }






//
////    /**
////     * To force to it to show action links, such as 'edit' if it is not 'lookup' to search of return value.
//////     * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#performLookup(org.kuali.core.web.struts.form.LookupForm, java.util.Collection, boolean)
////     */
//    @Override
//    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
//        Map lookupFormFields = lookupForm.getFieldsForLookup();
//
////        Map<String, String> protocolOnlineReviewLookupFields = buildCriteriaForProtocolOnlineReviewLookup(lookupFormFields);
////
////
////        lookupForm.setFieldsForLookup(protocolOnlineReviewLookupFields);
//
////        if (!lookupForm.isHideReturnLink()) {
////            lookupForm.setSuppressActions(true);
////        } else {
////            lookupForm.setShowMaintenanceLinks(true);
////        }
//        return super.performLookup(lookupForm, resultTable, bounded);
//    }
//




//
//    /**
//     * Pulls fields which can go directly to lookup from the given lookupFormFields and turns them into a Map of only those fields which should be included in the lookup
//     *
//     * @param lookupFormFields the fields directly from the lookup form to tweak into the fields the lookup service is going to want to use
//     * @return the fields as the lookup service will positively react to them
//     */
//    protected Map<String, String> buildCriteriaForProtocolOnlineReviewLookup(Map lookupFormFields) {
//        Map<String, String> lookupFields = new HashMap<String, String>();
//
//
//
//
//
//        String lowerBoundDateDue = "";
////        if (!(lookupFormFields.get(PROTOCOL_REVIEW_DUE_DATE)).isEmpty()) {
////            fieldValues.put(OBJ_PROTOCOL_REVIEW_DUE_DATE, fieldValues.get(PROTOCOL_REVIEW_DUE_DATE));
//        if (!(lookupFormFields.get("dateDue")).isEmpty()) {
//            lowerBoundDateDue = (String) lookupFormFields.remove(lookupFormFields.get("dateDue"));
//        }
//
//
//        String upperBoundDateDue = "";
////        if (!(lookupFormFields.get(PROTOCOL_REVIEW_DUE_DATE)).isEmpty()) {
////            fieldValues.put(OBJ_PROTOCOL_REVIEW_DUE_DATE, fieldValues.get(PROTOCOL_REVIEW_DUE_DATE));
//        if (!(lookupFormFields.get("dateDue")).isEmpty()) {
//            upperBoundDateDue = (String) lookupFormFields.remove(lookupFormFields.get("dateDue"));
//        }
//
////        final String lowerBoundDateDue = (String) lookupFormFields.remove(lookupFormFields.get("dateDue").  ArPropertyConstants.ContractsGrantsAgingReportFields.INVOICE_DATE_FROM);
//
//
////        if (!StringUtils.isEmpty(fieldValues.get(PROTOCOL_REVIEW_DUE_DATE))) {
////            fieldValues.put(OBJ_PROTOCOL_REVIEW_DUE_DATE, fieldValues.get(PROTOCOL_REVIEW_DUE_DATE));
////        }
//
//
////        final String upperBoundDateDue = (String) lookupFormFields.remove(ArPropertyConstants.ContractsGrantsAgingReportFields.INVOICE_DATE_TO);
////
//
////        final String invoiceDateCriteria = getContractsGrantsReportHelperService().fixDateCriteria(lowerBoundInvoiceDate, upperBoundInvoiceDate, true);
//
//        final String invoiceDateCriteria = getContractsGrantsReportHelperService().fixDateCriteria(lowerBoundInvoiceDate, upperBoundInvoiceDate, true);
//
//
//        if (!StringUtils.isBlank(invoiceDateCriteria)) {
//            lookupFields.put(KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.WORKFLOW_CREATE_DATE, invoiceDateCriteria);
//        }
//
//        final String invoiceAmount = (String) lookupFormFields.remove(ArPropertyConstants.TransmitContractsAndGrantsInvoicesLookupFields.INVOICE_AMOUNT);
//        if (!StringUtils.isBlank(invoiceAmount)) {
//            lookupFields.put(KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.FINANCIAL_DOCUMENT_TOTAL_AMOUNT, invoiceAmount);
//        }
//
//        final String customerNumber = (String) lookupFormFields.remove(ArPropertyConstants.CustomerFields.CUSTOMER_NUMBER);
//        if (!StringUtils.isBlank(customerNumber)) {
//            lookupFields.put(ArPropertyConstants.ACCOUNTS_RECEIVABLE_DOCUMENT_HEADER + "." + ArPropertyConstants.CustomerFields.CUSTOMER_NUMBER, customerNumber);
//        }
//
//        final String invoiceType = (String) lookupFormFields.remove(ArPropertyConstants.INVOICE_TYPE);
//        if (!StringUtils.isBlank(invoiceType)) {
//            lookupFields.put(KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.WORKFLOW_DOCUMENT_TYPE_NAME, invoiceType);
//        }
//
//        final String upperBoundInvoiceDueDate = (String) lookupFormFields.remove(ArPropertyConstants.CustomerInvoiceDocumentFields.INVOICE_DUE_DATE);
//        final String lowerBoundInvoiceDueDate = (String) lookupFormFields.remove(KRADConstants.LOOKUP_RANGE_LOWER_BOUND_PROPERTY_PREFIX + ArPropertyConstants.CustomerInvoiceDocumentFields.INVOICE_DUE_DATE);
//        final String invoiceDueDateCriteria = getContractsGrantsReportHelperService().fixDateCriteria(lowerBoundInvoiceDueDate, upperBoundInvoiceDueDate, false);
//        if (!StringUtils.isBlank(invoiceDueDateCriteria)) {
//            lookupFields.put(ArPropertyConstants.CustomerInvoiceDocumentFields.INVOICE_DUE_DATE, invoiceDueDateCriteria);
//        }
//
//        final String proposalNumber = (String) lookupFormFields.remove(KFSPropertyConstants.PROPOSAL_NUMBER);
//        if (!StringUtils.isBlank(proposalNumber)) {
//            lookupFields.put(ArPropertyConstants.ContractsGrantsInvoiceDocumentFields.PROPOSAL_NUMBER, proposalNumber);
//        }
//
//        final String documentNumber = (String) lookupFormFields.remove(KFSPropertyConstants.DOCUMENT_NUMBER);
//        if (!StringUtils.isBlank(documentNumber)) {
//            lookupFields.put(KFSPropertyConstants.DOCUMENT_NUMBER, documentNumber);
//        }
//
//        return lookupFields;
//    }
//








}
