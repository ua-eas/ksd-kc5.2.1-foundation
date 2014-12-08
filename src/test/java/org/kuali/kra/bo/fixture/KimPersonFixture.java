package org.kuali.kra.bo.fixture;



public enum KimPersonFixture {

    	QUICKSTART("quickstart", "1201", "10000000001"),
    	JTESTER("jtester", "1202", "10000000002"),
    	WOODS("woods", "1203", "10000000003"),
    	OPS_ADMIN("opsAdmin", "1204", "10000000004"),
    	NEGOTIATOR("negotiator", "1205", "10000000005"),
    	BORST("borst", "1206", "10000000006"),
    	IRB_ADMIN("irbAdmin", "1207", "10000000007"),
    	IACUC_ADMIN("iacucAdmin", "1208", "10000000008");
    	
    	private String principalName;
    	private String entityId;
    	private String principalId;


    	private KimPersonFixture(String principalName, String entityId, String principalId) {
    		this.principalName = principalName;
    		this.entityId = entityId;
    		this.principalId = principalId;
    	}

		public String getPrincipalName() {
			return principalName;
		}

		public String getEntityId() {
			return entityId;
		}

		public String getPrincipalId() {
			return principalId;
		}

}