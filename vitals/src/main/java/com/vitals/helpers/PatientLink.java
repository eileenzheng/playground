package com.vitals.helpers;

public class PatientLink {
	String firstName;
	String lastName;
	String specialty;
	String address;
	String addressLine2;
	String city;
	String state;
	String zip;
	String vitalsPhone;
	String uchcPhone;
	boolean hasLogo;
	boolean hasBookOnline;
	int bookType;
	
	public PatientLink(String firstName, String lastName, String specialty, String address, String addressLine2, String city, String state, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.address = address;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public void setVitalsPhone(String vitalsPhone) {
		this.vitalsPhone = vitalsPhone;
	}

	public void setUchcPhone(String uchcPhone) {
		this.uchcPhone = uchcPhone;
	}

	public void setHasLogo(boolean hasLogo) {
		this.hasLogo = hasLogo;
	}

	public void setHasBookOnline(boolean hasBookOnline) {
		this.hasBookOnline = hasBookOnline;
	}
	
	public void setBookType(int bookType) {
		this.bookType = bookType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getVitalsPhone() {
		return vitalsPhone;
	}

	public String getUchcPhone() {
		return uchcPhone;
	}

	public boolean hasLogo() {
		return hasLogo;
	}

	public boolean hasBookOnline() {
		return hasBookOnline;
	}
	
	public int getBookType() {
		return bookType;
	}
	
}
