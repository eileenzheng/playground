package com.vitals.helpers;

public class Profile {
    String searchResultName;
    String profileName;
    String specialty;
    String url;
    String address;
    String starRating;

    public Profile(String searchResultName, String url) {
        this.searchResultName = searchResultName;
        this.url = url;
    }

    public boolean searchAndProfileMatches() {
        return searchResultName.split(",")[0].equals(profileName.split(",")[0]);
    }

    public String getUrl() {
        return url;
    }
    
    public String getName() {
    	return searchResultName;
    }

    public void setProfileName(String name) {
        this.profileName = name;
    }

    public void setSpecialty(String spec) {
        this.specialty = spec;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStarRating(String starRating) {
        this.starRating  = starRating;
    }

    public String toString() {
        return searchResultName + "," + profileName + "," + url;
    }
}
