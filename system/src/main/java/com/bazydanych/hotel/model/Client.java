package main.java.com.bazydanych.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Client {
    private int id;
    private String email;
    private String password;
    private boolean verified;
    private boolean disabled;
    private String privilege = "CLIENT";

    public Client(
        int id,
        String email,
        String password,
        boolean verified,
        boolean disabled
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.disabled = disabled;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
