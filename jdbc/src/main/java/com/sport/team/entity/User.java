package com.sport.team.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    private int id;

    private String email;

    private String name;

    private String phone;

    private List<Skill> skills;

    private List<Tool> tools;

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("\nName: ").append( name );
        sb.append("\nEmail: ").append( email );
        sb.append("\nPhone: ").append( phone );
        return sb.toString();
    }
}
