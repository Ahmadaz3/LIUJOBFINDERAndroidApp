package com.example.seniorprojecttest2;

public class BussData {

    private String id;
    private String name;
    private String location;
    private String Size;
    private String Type;
    private String website;

    private String Email;

    public BussData(String id, String name, String location, String size, String type, String website, String email) {
        this.id = id;
        this.name = name;
        this.location = location;
        Size = size;
        Type = type;
        this.website = website;
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
