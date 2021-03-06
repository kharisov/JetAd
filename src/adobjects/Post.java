package adobjects;

import java.util.ArrayList;

public class Post {
    private String header;
    private byte[] image;
    private ArrayList<String> tags;
    private int id;
    private long date;
    private int ownerId;
    private String content;

    public Post(String header, String content, int owner) {
        this.header = header;
        this.ownerId = owner;
    }

    public Post(String header, String content, int id, int owner){
        this.header = header;
        this.id = id;
        this.ownerId = owner;
    }

    public String getHeader() {
        return header;
    }

    public String getContent(){ return content; }

    public byte[] getImage() {
        return image;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }
}
