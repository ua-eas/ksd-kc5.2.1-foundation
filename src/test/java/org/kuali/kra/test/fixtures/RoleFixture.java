package org.kuali.kra.test.fixtures;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.infrastructure.RoleConstants;


public enum RoleFixture {

	// ************************************************************************
	// NEGOTIATION
	//*************************************************************************
	
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
	NEGOTIATION_KP(RoleConstants.NEGOTIATION_ROLE_TYPE, "KP", new HashMap<String, String>()), // Key Personnel


	// ************************************************************************
	// TIME AND MONEY
	//*************************************************************************
	PROTOCOL_PI(RoleConstants.PROTOCOL_ROLE_TYPE, "PI", new HashMap<String, String>()), // Principle Investigator
	
	
	// ************************************************************************
	// TIME AND MONEY
	//*************************************************************************

	TIME_AND_MONEY_VIEWER("KC-T", "Time And Money Viewer", new HashMap<String, String>()),
	TIME_AND_MONEY_MODIFIER("KC-T", "Time And Money Modifier", new HashMap<String, String>()),

	
	// ************************************************************************
	// TIME AND MONEY
	//*************************************************************************
	
	COMMITTEE_ADMIN("KC-COMMITTEE", "Committee Administrator", new HashMap<String, String>()),


	// ************************************************************************
	// MISC
	//*************************************************************************
	
	//Subaward
	SUBAWARD_MODIFIER("KC-SUBAWARD", "Modify Subaward", new HashMap<String, String>()),
	
	// Protocol Unit Hierarchy requires these qualifications
	@SuppressWarnings("serial")
	SUPER_USER("KC-SYS", "KC Superuser", new HashMap<String, String>(){{put("unitNumber","*"); put("subunits","*");}});


	private String namespaceCode;
	private String roleName;
	private Map<String, String> qualifications;


	private RoleFixture(String namespaceCode, String roleName, Map<String, String> qualifications) {
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
