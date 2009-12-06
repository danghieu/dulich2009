package view;

public class Address {

	private String country;
	private String street;
	private String zipcode;
	private String isAddressOf;
	
	public String getIsAddressOf() {
		return isAddressOf;
	}
	public void setIsAddressOf(String isAddressOf) {
		this.isAddressOf = isAddressOf;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Address(String country, String street, String zipcode){
		this.country = country;
		this.street = street;
		this.zipcode = zipcode;
		
	}
	public Address() {
		// TODO Auto-generated constructor stub
	}
}
