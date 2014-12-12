package org.kuali.kra.test.fixtures;

public enum SubmissionQualifierTypeFixture {
	
	ANNUAL_SCHEDULED_BY_IRB("2", "Annual Scheduled By IRB");
	
	private String submissionQualifierTypeCode;
    private String description;
    
    
    private SubmissionQualifierTypeFixture(String submissionQualifierTypeCode, String description) {
    	this.submissionQualifierTypeCode = submissionQualifierTypeCode;
    	this.description = description;
    }

	public String getSubmissionQualifierTypeCode() {
		return submissionQualifierTypeCode;
	}

	public String getDescription() {
		return description;
	}

}