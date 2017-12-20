package com.example.andriy.schedsport;

public class UserModel {
    String name, description, key;

    public String getImageId() {
        return imageId;
    }

    String imageId;
    public UserModel(){
    }

    public UserModel(String name, String description, String key, String imageId) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.imageId = imageId;
    }
}
