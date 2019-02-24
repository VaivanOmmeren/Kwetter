package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private String name;
    private String password;
    private Date DateOfBirth;
    private List<User> following = new ArrayList<User>();
    private Role role;
    private String bio;
    private String website;

    public User(){

    }

    public User(String name, String password, Date dateOfBirth, Role role, String bio, String website){
        this.name = name;
        this.password = password;
        this.DateOfBirth = dateOfBirth;
        this.role = role;
        this.bio = bio;
        this.website = website;
    }

    public User(String name){
        this.name =  name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBio() {

        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void addFollowing(User userFollowing) { following.add(userFollowing);}

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}
