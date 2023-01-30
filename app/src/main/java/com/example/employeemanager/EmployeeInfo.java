package com.example.employeemanager;

public class EmployeeInfo {
    private String foreName, surName,
            email, phone, address,
            manager, project,
            username, pwd;
    private int id;

    public EmployeeInfo(int id, String foreName, String surName){
        this.foreName = foreName;
        this.surName = surName;
        this.id = id;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.manager = manager;
//        this.project = project;
//        this.username = username;
//        this.pwd = pwd;
    }

    // getters
    public String getForeName() {
        return foreName;
    }
    public String getSureName() {
        return surName;
    }
    public int getId(){
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getManager() {
        return manager;
    }
    public String getProject() {
        return project;
    }
    public String getUsername() {
        return username;
    }
    public String getPwd() {
        return pwd;
    }

}

