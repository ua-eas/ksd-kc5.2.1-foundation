package org.kuali.kra.test.fixtures;


public enum UnitFixture {

	TEST("9999", "Test Unit");


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
