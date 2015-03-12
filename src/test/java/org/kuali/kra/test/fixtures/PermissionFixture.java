package org.kuali.kra.test.fixtures;

import org.kuali.kra.infrastructure.PermissionConstants;

public enum PermissionFixture {

	VIEW_QUESTIONNAIRE("1", "KC-QUESTIONNAIRE", PermissionConstants.VIEW_QUESTIONNAIRE, true),
	MODIFY_QUESTIONNAIRE("1", "KC-QUESTIONNAIRE", PermissionConstants.MODIFY_QUESTIONNAIRE, true),
	VIEW_QUESTION("TEST1", "KC-QUESTIONNAIRE", PermissionConstants.VIEW_QUESTION, true),
	MODIFY_QUESTION("TEST2", "KC-QUESTIONNAIRE", PermissionConstants.MODIFY_QUESTION, true);
	
	private String permId;
	private String namespaceCode;
	private String name;
	private boolean active;
	
	private PermissionFixture (String permId, String namespaceCode, String name, boolean active) {
		this.permId = permId;
		this.namespaceCode = namespaceCode;
		this.name = name;
		this.active = active;
	}

	public String getPermId() {
		return permId;
	}

	public String getNamespaceCode() {
		return namespaceCode;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}
			
}
