package models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.inject.Named;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "tweet.getTweetById", query = "SELECT s FROM Tweet s WHERE s.id = :id"),
        @NamedQuery(name = "tweet.getTweetByUser", query = "SELECT s FROM Tweet s WHERE s.authorID = :id")
})
public class Tweet {

    public Tweet(){

    }

    public Tweet(String text, String authorID, String authorname){
        this.text = text;
        this.authorID = authorID;
        this.authorname = authorname;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String text;
    private String authorID;
    private String authorname;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date postTimeStamp;

    public String getId() {
        return id;
    }


    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

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

    public void setId(String id) {
        this.id = id;
    }
}
