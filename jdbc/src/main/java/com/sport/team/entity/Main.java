package com.sport.team.entity;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user=new User();
        user.setId(2);
        user.setName("Josh");
        user.setPhone("78789798789");
        user.setEmail("mail@mail.ru");
        List<Tool> tools=new ArrayList<>();
        List<Skill> skills=new ArrayList<>();
        Tool tool1=new Tool();
        Tool tool2=new Tool();
        tool1.setName("chisel");
        tool1.setId(1);
        tool2.setName("hammer");
        tool2.setId(2);
        Skill skill1=new Skill();
        Skill skill2=new Skill();
        skill1.setName("chiseling");
        skill1.setId(1);
        skill2.setName("hammering");
        skill2.setId(2);
        tools.add(tool1);
        tools.add(tool2);
        skills.add(skill1);
        skills.add(skill2);
        user.setSkills(skills);
        user.setTools(tools);
        System.out.println(user.toString());

    }
}
