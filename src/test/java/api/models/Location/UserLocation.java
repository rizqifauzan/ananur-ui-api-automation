package api.models.Location;

public class UserLocation {
    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
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
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getTimezone() {
        return timezone;
    }
}