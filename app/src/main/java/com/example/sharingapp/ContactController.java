package com.example.sharingapp;

public class ContactController {
    private Contact contact;

    public ContactController( Contact contact){
        this.contact = contact;

    }

    private String email;
    private String id;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public static void setText(String username) {
    }

    public void addObserver(Observer observer) {
        contact.addObserver(observer);
    }


    public void removeObserver(Observer observer) {
        contact.removeObserver(observer);
    }
}
