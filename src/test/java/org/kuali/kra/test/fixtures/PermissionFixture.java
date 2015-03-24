package org.kuali.kra.test.fixtures;

import org.kuali.kra.infrastructure.PermissionConstants;

public enum PermissionFixture {

	VIEW_QUESTIONNAIRE("1", "KC-QUESTIONNAIRE", PermissionConstants.VIEW_QUESTIONNAIRE, true),
	CREATE_PROTOCOL_DOCUMENT("2", "KC-PROTOCOL", PermissionConstants.CREATE_PROTOCOL, true),
	MAINTAIN_PROTOCOL_SUBMISSIONS("3", "KC-PROTOCOL", PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS, true);
	
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
