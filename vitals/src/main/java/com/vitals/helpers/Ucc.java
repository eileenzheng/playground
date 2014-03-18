package com.vitals.helpers;

public class Ucc {
	String searchResultName;
    String profileName;
    String url;
    String address;
    String city;
    String state;
    String zip;
    String starRating;

    public Ucc(String searchResultName, String url, String address, String city, String state, String zip) {
        this.searchResultName = searchResultName;
        this.url = url;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public boolean searchAndProfileMatches() {
        return searchResultName.equals(profileName);
    }
    
    public boolean isNameLongEnough() {
        return searchResultName.length() >= 5;
    }
    
    public boolean isAddressLongEnough() {
        return address.length() >= 5;
    }
    
    public boolean isCityLongEnough() {
        return city.length() >= 3;
    }
    
    public boolean isStateValid() {
        return state.matches("[A-Z]{2}");
    }
    
    public boolean isZipValid() {
        return zip.matches("[0-9]{5}");
    }

    public String getUrl() {
        return url;
    }
    
    public String getName() {
    	return searchResultName;
    }
    
    public String getAddress() {
    	return address;
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

    public void setProfileName(String name) {
        this.profileName = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStarRating(String starRating) {
        this.starRating  = starRating;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(searchResultName).append(",").append(profileName).append(",").append(url);
        return buf.toString();
    }
}
