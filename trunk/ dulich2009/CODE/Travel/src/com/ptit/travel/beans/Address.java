package com.ptit.travel.beans;

import java.io.Serializable;
import java.util.ArrayList;

import com.ptit.travel.agent.communication.Message;



public class Address implements Serializable {

	private String city;
	private String country;
	public Address(){
		
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toMsg(){
		return (this.city + Message.SEPARATE 
				+ this.country);
	}
	public Address(String msg){
		
		ArrayList<String> arr = Message.split(msg);
		setCity((String)arr.get(0));
		setCountry(arr.get(1));
	
		
		
	}
}
