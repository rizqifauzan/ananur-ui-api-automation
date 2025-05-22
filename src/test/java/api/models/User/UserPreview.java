package api.models.User;

public class UserPreview {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;

    public UserPreview(String id, String title, String firstName, String lastName, String picture) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
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
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getPicture() {
        return picture;
    }
}