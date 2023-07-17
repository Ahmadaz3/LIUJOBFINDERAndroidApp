package com.example.seniorprojecttest2;

public class ResourcesData {
    private String resource_name;
    private String resource_contant;

    public ResourcesData(String resource_name, String resource_contant) {
        this.resource_name = resource_name;
        this.resource_contant = resource_contant;
    }
    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_contant() {
        return resource_contant;
    }

    public void setResource_contant(String resource_contant) {
        this.resource_contant = resource_contant;
    }
}
