package view;

public class Contact {
 private String email;
 private int phoneNumber;
 private int fax;
 private String  hasAddress;
 private String hasName;
 private Name name;
public Name getName() {
	return name;
}
public void setName(Name name) {
	this.name = name;
}
public String getHasAddress() {
	return hasAddress;
}
public void setHasAddress(String hasAddress) {
	this.hasAddress = hasAddress;
}
public String getHasName() {
	return hasName;
}
public void setHasName(String hasName) {
	this.hasName = hasName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(int phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getFax() {
	return fax;
}
public void setFax(int fax) {
	this.fax = fax;
}
public void setHasAddress(Object value) {
	// TODO Auto-generated method stub
	
}

  
}
