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
package org.kuali.kra.institutionalproposal.home;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import java.sql.Date;

public class InstitutionalProposalAdminDetails extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String devProposalNumber;

    private String instProposalNumber;

    private Integer instPropSequenceNumber;

    private Date dateSubmittedByDept;

    private Date dateReturnedToDept;

    private Date dateApprovedByOsp;

    private Date dateSubmittedToAgency;

    private Date instPropCreateDate;

    private String instPropCreateUser;

    private String signedBy;

    private boolean submissionType;

    private InstitutionalProposal institutionalProposal;

    public InstitutionalProposalAdminDetails() {
    }

    public String getDevProposalNumber() {
        return devProposalNumber;
    }

    public void setDevProposalNumber(String devProposalNumber) {
        this.devProposalNumber = devProposalNumber;
    }

    public String getInstProposalNumber() {
        return instProposalNumber;
    }

    public void setInstProposalNumber(String instProposalNumber) {
        this.instProposalNumber = instProposalNumber;
    }

    public Integer getInstPropSequenceNumber() {
        return instPropSequenceNumber;
    }

    public void setInstPropSequenceNumber(Integer instPropSequenceNumber) {
        this.instPropSequenceNumber = instPropSequenceNumber;
    }

    public Date getDateSubmittedByDept() {
        return dateSubmittedByDept;
    }

    public void setDateSubmittedByDept(Date dateSubmittedByDept) {
        this.dateSubmittedByDept = dateSubmittedByDept;
    }

    public Date getDateReturnedToDept() {
        return dateReturnedToDept;
    }

    public void setDateReturnedToDept(Date dateReturnedToDept) {
        this.dateReturnedToDept = dateReturnedToDept;
    }

    public Date getDateApprovedByOsp() {
        return dateApprovedByOsp;
    }

    public void setDateApprovedByOsp(Date dateApprovedByOsp) {
        this.dateApprovedByOsp = dateApprovedByOsp;
    }

    public Date getDateSubmittedToAgency() {
        return dateSubmittedToAgency;
    }

    public void setDateSubmittedToAgency(Date dateSubmittedToAgency) {
        this.dateSubmittedToAgency = dateSubmittedToAgency;
    }

    public Date getInstPropCreateDate() {
        return instPropCreateDate;
    }

    public void setInstPropCreateDate(Date instPropCreateDate) {
        this.instPropCreateDate = instPropCreateDate;
    }

    public String getInstPropCreateUser() {
        return instPropCreateUser;
    }

    public void setInstPropCreateUser(String instPropCreateUser) {
        this.instPropCreateUser = instPropCreateUser;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public boolean getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(boolean submissionType) {
        this.submissionType = submissionType;
    }

    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposal;
    }

    public void setProposal(InstitutionalProposal institutionalProposal) {
        this.institutionalProposal = institutionalProposal;
    }
}
