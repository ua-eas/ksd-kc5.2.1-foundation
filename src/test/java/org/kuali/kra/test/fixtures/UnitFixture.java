package org.kuali.kra.test.fixtures;


public enum UnitFixture {

	TEST_1("9999", "Test Unit 1", null),
	TEST_2("9998", "Test Unit 2", null),
	TEST_3("AGSC", "Test Unit 3", "1562");


	private String unitNumber;
	private String unitName;
	private String parentUnitNumber;

	private UnitFixture(String unitNumber, String unitName, String parentUnitNumber) {
		this.unitNumber = unitNumber;
		this.unitName = unitName;
		this.parentUnitNumber = parentUnitNumber;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public String getUnitName() {
		return unitName;
	}
	
	public String getParentUnitNumber() {
		return parentUnitNumber;
	}

}
