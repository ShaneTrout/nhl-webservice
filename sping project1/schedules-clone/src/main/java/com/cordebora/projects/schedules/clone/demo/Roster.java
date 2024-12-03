package com.cordebora.projects.schedules.clone.demo;

import jakarta.validation.constraints.NotEmpty;

public class Roster {

    //Validate not empty
    @NotEmpty

    private String lastName;

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private String firstName;

    public Roster() {
    }

    public Roster(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
