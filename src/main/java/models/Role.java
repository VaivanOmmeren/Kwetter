package models;

public class Role {

    public Role(){

    }

    public Role(String name, String ID){
        this.name = name;
        this.ID = ID;
    }

    private String name;
    private String ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
