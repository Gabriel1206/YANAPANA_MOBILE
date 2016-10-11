package pe.edu.upc.yanapan.model;

/**
 * Created by Juan on 10/10/2016.
 */

public class WorkingDate {

    private User user;
    private String longitude;
    private String latitude;
    private String type;


    public WorkingDate() {
        this.user = user;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
