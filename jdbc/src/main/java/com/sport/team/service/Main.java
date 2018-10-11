package com.sport.team.service;

import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Main {
    public static void main(String[] args) {

        try{

            Tool tool=new Tool();
            tool.setId(1);
            tool.setName("Hammer");
            add(tool);
            List<Tool> tools=new ArrayList<>();
            tools.add(tool);

            Skill skill=new Skill();
            skill.setId(1);
            skill.setName("Hammering things");
            add(skill);
            List<Skill> skills=new ArrayList<>();
            skills.add(skill);

            User user=new User();
            user.setId(1);
            user.setName("Brett Meyer");
            user.setEmail("mayer@gmail.com");
            user.setPhone("044-516-19-99");
            user.setTools(tools);
            user.setSkills(skills);

            add(user);
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
