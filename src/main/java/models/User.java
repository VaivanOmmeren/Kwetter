package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name= "users")
@NamedQueries({
        @NamedQuery(name = "users.getUserById", query = "SELECT s FROM User s WHERE s.id = :id"),
        @NamedQuery(name = "users.getUserByName", query = "SELECT s FROM User s WHERE s.name = :name")
})
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(unique = true)
    private String name;
    private String password;
    private Date DateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> following = new ArrayList<>();

    @ManyToOne
    private UserRole userRole;
    private String bio;
    private String website;
    private String token;

    @Transient
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;

    public User(){

    }

    public User(String name, String password, UserRole userRole, Date dateOfBirth, String bio, String website){
        this.name = name;
        this.password = password;
        this.DateOfBirth = dateOfBirth;
        this.bio = bio;
        this.website = website;
        this.userRole = userRole;
    }

    public User(String id, String password, String name, Date dateOfBirth, String bio, String website, UserRole userRole){
        this.id = id;
        this.password = password;
        this.name = name;
        this.DateOfBirth = dateOfBirth;
        this.bio = bio;
        this.website = website;
        this.userRole = userRole;
    }

    public User(String name){
        this.name =  name;
        this.following = new ArrayList<User>();
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

    public void unFollow(User unfollow){

        int index = -1;

        for(User u : following){
            if(u.getId().equals(unfollow.getId())){
                index = following.indexOf(u);
            }
        }
        if(index != -1){
            following.remove(index);
        }
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
