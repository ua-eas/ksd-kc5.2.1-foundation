package org.kuali.kra.test.fixtures;


public enum CustomAttributeDocumentFixture {
	
	CUSTOM_ATTRIBUTE_DOCUMENT_1("PRDV", true, CustomAttributeFixture.CUSTOM_ATTRIBUTE_1),
	CUSTOM_ATTRIBUTE_DOCUMENT_2("PRDV", false, CustomAttributeFixture.CUSTOM_ATTRIBUTE_2);
	
	private String documentTypeCode;
	private boolean required;
	private CustomAttributeFixture customAttribute;
	
	private CustomAttributeDocumentFixture (String documentTypeCode, boolean required, CustomAttributeFixture customAttribute) {
		this.documentTypeCode = documentTypeCode;
		this.required = required;
		this.customAttribute = customAttribute;
	}

	public String getDocumentTypeCode() {
		return documentTypeCode;
	}

	public void setDocumentTypeCode( String documentTypeCode ) {
		this.documentTypeCode = documentTypeCode;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired( boolean required ) {
		this.required = required;
	}

	public CustomAttributeFixture getCustomAttribute() {
		return customAttribute;
	}

	public void setCustomAttribute( CustomAttributeFixture customAttribute ) {
		this.customAttribute = customAttribute;
	}
}
