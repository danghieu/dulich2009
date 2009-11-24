package view;

public class Name {
	private String givenName;
	private String namePrefix;
	private String surName;
	private String isNameOf;
	
	public String getIsNameOf() {
		return isNameOf;
	}
	public void setIsNameOf(String isNameOf) {
		this.isNameOf = isNameOf;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
}
