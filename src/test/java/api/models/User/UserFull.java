package api.models.User;

import api.models.Location.UserLocation;

public class UserFull {
    private String id;
    private String title = "mr";
    private String firstName;
    private String lastName;
    private String gender = "male";
    private String email;
    private String dateOfBirth;
    private String phone;
    private String registerDate;
    private String picture;
    private UserLocation location;

    public UserFull(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public UserLocation getLocation() {
        return location;
    }
    public void setLocation(UserLocation location) {
        this.location = location;
    }
}