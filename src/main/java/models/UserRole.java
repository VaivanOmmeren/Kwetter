package models;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "userRole.getRoleById", query = "SELECT s FROM UserRole s WHERE s.id = :id"),
        @NamedQuery(name = "userRole.getRoleByName", query = "SELECT s FROM UserRole s WHERE s.name = :name")
})
public class UserRole {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    public UserRole(){

    }

    public UserRole(String name){
        this.name = name;
    }
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
