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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.coi.personfinancialentity.FinEntityDataMatrixBean;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisclosureHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7441300028105575094L;
    protected static final String NAMESPACE_CODE = "KC-COIDISCLOSURE";
    protected static final String PARAMETER_CODE = "Document";
    private static final String CONFLICT_HEADER_LABEL_PARAMETER = "COI_DISCLOSURE_FE_CONFLICT_HEADER_LABEL";
    protected static final String DEFAULT_CONFLICT_HEADER_LABEL = "Related";
    private static final String ERROR_COI_ANNUAL_OPEN_DISCLOSURES_FOR_ADMIN = "error.coi.annual.open.disclosures.for.admin";
    private CoiDisclosureForm form;
    private DisclosurePersonUnit newDisclosurePersonUnit;
    private List<DisclosurePersonUnit> deletedUnits;
    private List<FinEntityDataMatrixBean> editRelationDetails;
    private List<FinEntityDataMatrixBean> newRelationDetails;
    private boolean canViewDisclosureFeHistory;
    private boolean canEditDisclosureFinancialEntity;
    private boolean canViewDisclosureCertification;
    private boolean canCertifyDisclosure;
    private boolean canUpdateFEStatusAdmin;
    private String conflictHeaderLabel;
    private CoiDisclProject newCoiDisclProject;
    private String protocolType;
    // new protocols for disclosure
    private List<Protocol> newProtocols;
    private List<IacucProtocol> newIacucProtocols;
    private Long newProtocolId;
    private Long newIacucProtocolId;
    private String newProposalNumber;
    private Long newAwardId;
    private List<DevelopmentProposal> newProposals;
    private List<InstitutionalProposal> newInstitutionalProposals;
    private List<Award> newAwards;
    private String newProjectId;
    private String newModuleItemKey;
    private String eventTypeCode;
    private String proposalType;
    private boolean modifyReporter;
    MasterDisclosureBean masterDisclosureBean;
    private CoiNotification viewNotification;    
    private transient CoiDisclosureService disclosureService;
    private boolean unresolvedEventsPresent;
    private String annualCertApprovalErrorMsgForAdmin;
    private boolean disclosureGroupedByEvent;
    private List<CoiGroupedMasterDisclosureBean> allDisclosuresGroupedByProjects;
    
    public DisclosureHelper(CoiDisclosureForm form) {
        this.form = form;
        setNewDisclosurePersonUnit(new DisclosurePersonUnit());
        deletedUnits = new ArrayList<DisclosurePersonUnit>(); 
        newRelationDetails = getFinancialEntityService().getFinancialEntityDataMatrix();
        editRelationDetails = new ArrayList<FinEntityDataMatrixBean>(); 
        CoiDisclosure coiDisclosure = form.getCoiDisclosureDocument().getCoiDisclosure();
        newCoiDisclProject = new CoiDisclProject(coiDisclosure.getCoiDisclosureNumber(), coiDisclosure.getSequenceNumber());
        newProtocols = new ArrayList<Protocol>();
        newIacucProtocols = new ArrayList<IacucProtocol>();
        initConflictHeaderLabel();
        setDisclosureGroupedByEvent(true);
        setAllDisclosuresGroupedByProjects(new ArrayList<CoiGroupedMasterDisclosureBean>());
   }

    public CoiDisclosureForm getForm() {
        return form;
    }
    public void prepareView() {
        initializePermissions(getCoiDisclosure());    
        initializeOldFeStatii();
        checkForUnresolvedEvents();
    }
    
    private void initializePermissions(CoiDisclosure coiDisclosure) {
        initializeModifyCoiDisclosurePermission(coiDisclosure);
        canEditDisclosureFinancialEntity = hasCanEditDisclosureFinancialEntityPermission(coiDisclosure);
        canViewDisclosureFeHistory = canEditDisclosureFinancialEntity || hasCanViewDisclosureFeHistoryPermission(coiDisclosure);
        canViewDisclosureCertification = hasCanViewDisclosureCertificationPermission();
        canCertifyDisclosure = hasCanCertifyDisclosurePermission();
        canUpdateFEStatusAdmin = hasCanUpdateFEStatusAdmin();
    }

    private void initializeOldFeStatii() {
        for (CoiDisclProject project: getCoiDisclosure().getCoiDisclProjects()) {
            for (CoiDiscDetail detail: project.getCoiDiscDetails()) {
                detail.setOldEntityDispositionCode();
            }
        }
    }
    
 // check if the given dispCode is a member of a particular set of 'well-resolved' disposition codes
    private boolean isDispositionWellResolved(String dispCode) {
        boolean retVal = false; 
        if (CoiDispositionStatus.BEST_PRACTICES_MEMO.equals(dispCode) ||
            CoiDispositionStatus.NO_FURTHER_ACTION.equals(dispCode) ||
            CoiDispositionStatus.DISCLOSED_INTERESTS_ELIMINATED.equals(dispCode) ||
            CoiDispositionStatus.DISCLOSED_INTERESTS_REDUCED.equals(dispCode) ||
            CoiDispositionStatus.DISCLOSED_INTERESTS_MANAGED.equals(dispCode) ||
            CoiDispositionStatus.NO_CONFLICT_EXISTS.equals(dispCode) ||
            CoiDispositionStatus.EXEMPT.equals(dispCode)) {
            
            retVal = true;
        }
        return retVal;
    }
    
    
    private void checkForUnresolvedEvents() {
        unresolvedEventsPresent = false;
        if (getCoiDisclosure().isAnnualEvent() || getCoiDisclosure().isAnnualUpdate()) {
            // loop thru all disclosures for the reporter, and break on the first one we find is not resolved 'well'
            List<CoiDisclosure> disclosures = getDisclosureService().getAllDisclosuresForUser(getCoiDisclosure().getPersonId());
            for (CoiDisclosure disclosure: disclosures) {
                if (!disclosure.isAnnualEvent() && !disclosure.isAnnualUpdate() && !disclosure.isExcludedFromAnnual()) {
                    // ignoring disapproved disclosures during the check; annuals can get stuck otherwise 
                    if( !(CoiDisclosureStatus.DISAPPROVED.equals(disclosure.getDisclosureStatusCode())) && 
                        !(isDispositionWellResolved(disclosure.getDisclosureDispositionCode())) ) {
                        // we have a "bad" disposition status, so we set the display flag and an error message, and break
                        unresolvedEventsPresent = true;
                        if (annualCertApprovalErrorMsgForAdmin == null) {
                            annualCertApprovalErrorMsgForAdmin = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(ERROR_COI_ANNUAL_OPEN_DISCLOSURES_FOR_ADMIN);              
                            annualCertApprovalErrorMsgForAdmin = annualCertApprovalErrorMsgForAdmin.replace("{0}", getCoiDisclosure().getDisclosureReporter().getReporter().getFullName());
                        }
                        break;                        
                    }
                }
            }
        } 
        else {
            annualCertApprovalErrorMsgForAdmin = null;
        }
    }
    
    private void initializeModifyCoiDisclosurePermission(CoiDisclosure coiDisclosure) {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MODIFY_COI_DISCLOSURE, coiDisclosure);
        modifyReporter = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    public CoiDisclosure getCoiDisclosure() {
        CoiDisclosureDocument document = form.getCoiDisclosureDocument();
        if (document == null || document.getCoiDisclosure() == null) {
            throw new IllegalArgumentException("invalid (null) CoiDisclosureDocument in ProtocolForm");
        }
        return document.getCoiDisclosure();
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    public void setForm(CoiDisclosureForm form) {
        this.form = form;
    }

    public DisclosurePersonUnit getNewDisclosurePersonUnit() {
        return newDisclosurePersonUnit;
    }

    public void setNewDisclosurePersonUnit(DisclosurePersonUnit newDisclosurePersonUnit) {
        this.newDisclosurePersonUnit = newDisclosurePersonUnit;
        DisclosurePerson disclosurePerson = null;
        if (CollectionUtils.isEmpty(((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosurePersons())) {
            disclosurePerson = ((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosureReporter();
        } else {
            disclosurePerson = ((CoiDisclosureDocument)this.form.getDocument()).getCoiDisclosure().getDisclosurePersons().get(0);
        }
        this.newDisclosurePersonUnit.setDisclosurePersonId(disclosurePerson.getDisclosurePersonId());
        this.newDisclosurePersonUnit.setDisclosurePerson(disclosurePerson);
        this.newDisclosurePersonUnit.setPersonId(disclosurePerson.getPersonId());
    }

    public List<DisclosurePersonUnit> getDeletedUnits() {
        return deletedUnits;
    }

    public void setDeletedUnits(List<DisclosurePersonUnit> deletedUnits) {
        this.deletedUnits = deletedUnits;
    }

    public List<FinEntityDataMatrixBean> getEditRelationDetails() {
        return editRelationDetails;
    }

    public void setEditRelationDetails(List<FinEntityDataMatrixBean> editRelationDetails) {
        this.editRelationDetails = editRelationDetails;
    }

    public List<FinEntityDataMatrixBean> getNewRelationDetails() {
        return newRelationDetails;
    }

    public void setNewRelationDetails(List<FinEntityDataMatrixBean> newRelationDetails) {
        this.newRelationDetails = newRelationDetails;
    }

    private FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }

    public boolean isCanViewDisclosureFeHistory() {
        return canViewDisclosureFeHistory;
    }

    public void setCanViewDisclosureFeHistory(boolean canViewDisclosureFeHistory) {
        this.canViewDisclosureFeHistory = canViewDisclosureFeHistory;
    }

    public boolean isCanViewDisclosureCertification() {
        return canViewDisclosureCertification;
    }

    public boolean isCanCertifyDisclosure() {
        return canCertifyDisclosure;
    }

    public boolean isCanUpdateFEStatusAdmin() {
        return (getCoiDisclosure().isUnderReview() || getCoiDisclosure().isSubmittedForReview()) && canUpdateFEStatusAdmin;
    }

    private boolean hasCanViewDisclosureFeHistoryPermission(CoiDisclosure coiDisclosure) {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, coiDisclosure);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    private boolean hasCanViewDisclosureCertificationPermission() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    private boolean hasCanUpdateFEStatusAdmin() {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.UPDATE_FE_STATUS_ADMIN, getCoiDisclosure());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    private boolean hasCanCertifyDisclosurePermission() {
// for now        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.CERTIFY, getCoiDisclosure());
//        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        return (getCoiDisclosure().getPersonId() == null) || StringUtils.equals(getCoiDisclosure().getPersonId(), GlobalVariables.getUserSession().getPrincipalId());

    }

    public boolean isCanEditDisclosureFinancialEntity() {
        return canEditDisclosureFinancialEntity;
    }

    public void setCanEditDisclosureFinancialEntity(boolean canEditDisclosureFinancialEntity) {
        this.canEditDisclosureFinancialEntity = canEditDisclosureFinancialEntity;
    }
    private boolean hasCanEditDisclosureFinancialEntityPermission(CoiDisclosure coiDisclosure) {
        CoiDisclosureTask task = new CoiDisclosureTask(TaskName.MODIFY_COI_DISCLOSURE, coiDisclosure);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task); 
    }

    public String getConflictHeaderLabel() {
        if (StringUtils.isBlank(conflictHeaderLabel)) {
            initConflictHeaderLabel();
        }
        return conflictHeaderLabel;
    }

    public void setConflictHeaderLabel(String conflictHeaderLabel) {
        this.conflictHeaderLabel = conflictHeaderLabel;
    }
    
    private void initConflictHeaderLabel() {
        String param = DEFAULT_CONFLICT_HEADER_LABEL;
        try {
            param = getParameterService().getParameterValueAsString(NAMESPACE_CODE, PARAMETER_CODE, CONFLICT_HEADER_LABEL_PARAMETER);
        } catch (Exception e) {
            // param not set - use default    
        }
        setConflictHeaderLabel(param);
        
    }
    
    private ParameterService getParameterService() {
         return KraServiceLocator.getService(ParameterService.class);
    }

    public CoiDisclProject getNewCoiDisclProject() {
        return newCoiDisclProject;
    }

    public void setNewCoiDisclProject(CoiDisclProject newCoiDisclProject) {
        this.newCoiDisclProject = newCoiDisclProject;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public List<Protocol> getNewProtocols() {
        return newProtocols;
    }

    public void setNewProtocols(List<Protocol> newProtocols) {
        this.newProtocols = newProtocols;
    }

    public Long getNewProtocolId() {
        return newProtocolId;
    }

    public void setNewProtocolId(Long newProtocolId) {
        this.newProtocolId = newProtocolId;
    }

    public List<IacucProtocol> getNewIacucProtocols() {
        return newIacucProtocols;
    }

    public void setNewIacucProtocols(List<IacucProtocol> newIacucProtocols) {
        this.newIacucProtocols = newIacucProtocols;
    }

    public Long getNewIacucProtocolId() {
        return newIacucProtocolId;
    }

    public void setNewIacucProtocolId(Long newIacucProtocolId) {
        this.newIacucProtocolId = newIacucProtocolId;
    }

    public String getNewProposalNumber() {
        return newProposalNumber;
    }

    public void setNewProposalNumber(String newProposalNumber) {
        this.newProposalNumber = newProposalNumber;
    }

    public Long getNewAwardId() {
        return newAwardId;
    }

    public void setNewAwardId(Long newAwardId) {
        this.newAwardId = newAwardId;
    }

    public List<DevelopmentProposal> getNewProposals() {
        return newProposals;
    }

    public void setNewProposals(List<DevelopmentProposal> newProposals) {
        this.newProposals = newProposals;
    }

    public List<Award> getNewAwards() {
        return newAwards;
    }

    public void setNewAwards(List<Award> newAwards) {
        this.newAwards = newAwards;
    }

    public String getNewProjectId() {
        return newProjectId;
    }

    public void setNewProjectId(String newProjectId) {
        this.newProjectId = newProjectId;
    }

    public List<InstitutionalProposal> getNewInstitutionalProposals() {
        return newInstitutionalProposals;
    }

    public void setNewInstitutionalProposals(List<InstitutionalProposal> newInstitutionalProposals) {
        this.newInstitutionalProposals = newInstitutionalProposals;
    }

    public String getProposalType() {
        return proposalType;
    }

    public void setProposalType(String proposalType) {
        this.proposalType = proposalType;
    }

    public boolean isModifyReporter() {
        return modifyReporter;
    }

    public void setModifyReporter(boolean modifyReporter) {
        this.modifyReporter = modifyReporter;
    }

    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getNewModuleItemKey() {
        return newModuleItemKey;
    }

    public void setNewModuleItemKey(String newModuleItemKey) {
        this.newModuleItemKey = newModuleItemKey;
    }

    public MasterDisclosureBean getMasterDisclosureBean() {
        return masterDisclosureBean;
    }

    public void setMasterDisclosureBean(MasterDisclosureBean masterDisclosureBean) {
        this.masterDisclosureBean = masterDisclosureBean;
    }

    public boolean isIsMasterDisclosure() {
        return getMasterDisclosureBean() != null;
    }

    public CoiNotification getViewNotification() {
        return viewNotification;
    }

    public void setViewNotification(CoiNotification viewNotification) {
        this.viewNotification = viewNotification;
    }

    private CoiDisclosureService getDisclosureService() {
        if (disclosureService == null) {
            disclosureService = KraServiceLocator.getService(CoiDisclosureService.class);
        }
        return disclosureService;
    }

    public boolean isUnresolvedEventsPresent() {
        return unresolvedEventsPresent;
    }

    public void setUnresolvedEventsPresent(boolean unresolvedEventsPresent) {
        this.unresolvedEventsPresent = unresolvedEventsPresent;
    }

    public String getAnnualCertApprovalErrorMsgForAdmin() {
        return annualCertApprovalErrorMsgForAdmin;
    }
    
    public boolean isDisclosedProjectsPresent() {
        return getAllDisclosuresGroupedByProjects().size() > 0;
    }

    public boolean isFinancialEntitiesPresent() {
        boolean entitiesPresent = false;
        List<CoiDisclProject> disclosureProjects = getCoiDisclosure().getCoiDisclProjects();
        if(!disclosureProjects.isEmpty() && !disclosureProjects.get(0).getCoiDiscDetails().isEmpty()) {
            entitiesPresent = disclosureProjects.get(0).getCoiDiscDetails().get(0).getPersonFinIntDisclosure() != null;
        }
        return entitiesPresent;
    }
    
    public List<CoiGroupedMasterDisclosureBean> getAllDisclosuresGroupedByProjects() {
        if(allDisclosuresGroupedByProjects.isEmpty()) {
            setAllDisclosuresGroupedByProjects(getDisclosureService().getUndisclosedProjectsGroupedByEvent(getCoiDisclosure().getCoiDisclProjects()));
        }
        return allDisclosuresGroupedByProjects;
    }

    public boolean isDisclosureGroupedByEvent() {
        return disclosureGroupedByEvent;
    }

    public void setDisclosureGroupedByEvent(boolean disclosureGroupedByEvent) {
        this.disclosureGroupedByEvent = disclosureGroupedByEvent;
    }

    public void setAllDisclosuresGroupedByProjects(List<CoiGroupedMasterDisclosureBean> allDisclosuresGroupedByProjects) {
        this.allDisclosuresGroupedByProjects = allDisclosuresGroupedByProjects;
    }

}
