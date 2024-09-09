package com.example.firststepsjava;

public class UserEntity {

    private byte id;
    private byte age;
    private String firstname;
    private String lasstname;

    public UserEntity() {
    }

    public UserEntity(byte id, byte age, String firstname, String lasstname) {
        this.id = id;
        this.age = age;
        this.firstname = firstname;
        this.lasstname = lasstname;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLasstname() {
        return lasstname;
    }

    public void setLasstname(String lasstname) {
        this.lasstname = lasstname;
    }
}
