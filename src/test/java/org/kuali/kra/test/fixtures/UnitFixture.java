package org.kuali.kra.test.fixtures;


public enum UnitFixture {

	TEST_1("9999", "Test Unit 1"),
	TEST_2("9998", "Test Unit 2");


	private String unitNumber;
	private String unitName;

	private UnitFixture(String unitNumber, String unitName) {
		this.unitNumber = unitNumber;
		this.unitName = unitName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public String getUnitName() {
		return unitName;
	}

}
