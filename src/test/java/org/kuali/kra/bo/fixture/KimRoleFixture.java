package org.kuali.kra.bo.fixture;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.RoleConstants;


public enum KimRoleFixture {
	
	// Can do everything
	NEGOTIATION_ADMIN(RoleConstants.NEGOTIATION_ROLE_TYPE, "Negotiation Administrator", new HashMap<String, String>()),
	
	// Can do everything, but is derived
	NEGOTIATION_NEGOTIATOR(RoleConstants.NEGOTIATION_ROLE_TYPE, "Negotiator", new HashMap<String, String>()),
	
	// Can do everything but modify activities
	NEGOTIATION_CREATOR(RoleConstants.NEGOTIATION_ROLE_TYPE, "Negotiation Creator", new HashMap<String, String>()),
	
	// Can only view, different roles for tracking
	NEGOTIATION_INVESTIGATORS(RoleConstants.NEGOTIATION_ROLE_TYPE, "Investigators", new HashMap<String, String>()), // Principle Investigator
	NEGOTIATION_PI(RoleConstants.NEGOTIATION_ROLE_TYPE, "PI", new HashMap<String, String>()), // Principle Investigator
	NEGOTIATION_COI(RoleConstants.NEGOTIATION_ROLE_TYPE, "COI", new HashMap<String, String>()), // Co-Investigator
	NEGOTIATION_KP(RoleConstants.NEGOTIATION_ROLE_TYPE, "KP", new HashMap<String, String>()); // Key Personnel
	

	private String namespaceCode;
	private String roleName;
	private Map<String, String> qualifications;


	private KimRoleFixture(String namespaceCode, String roleName, Map<String, String> qualifications) {
		this.namespaceCode = namespaceCode;
		this.roleName = roleName;
		this.qualifications = qualifications;
	}


	public String getNamespaceCode() {
		return namespaceCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public Map<String, String> getQualifications() {
		return qualifications;
	}

}
