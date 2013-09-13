package helpers;

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
        return searchResultName.equals(profileName);
    }

    public String getUrl() {
        return url;
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
        StringBuilder buf = new StringBuilder();
        buf.append(searchResultName).append(",").append(profileName).append(",").append(url);
//        buf.append(searchResultName).append(",").append(profileName).append(",").append(url).append(",")
//        .append(address).append(",").append(starRating).append(",").append(specialty);
        return buf.toString();
    }
}
