package models;

import java.util.Date;

public class Tweet {

    public Tweet(){

    }

    public Tweet(String ID, String text, String authorID){
        this.ID = ID;
        this.text = text;
        this.authorID = authorID;
    }

    private String ID;
    private String text;
    private String authorID;
    private Date postTimeStamp;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String id) {
        this.authorID = id;
    }

    public Date getPostTimeStamp() {
        return postTimeStamp;
    }

    public void setPostTimeStamp(Date postTimeStamp) {
        this.postTimeStamp = postTimeStamp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
