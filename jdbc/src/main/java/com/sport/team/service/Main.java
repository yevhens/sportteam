package com.sport.team.service;

import com.sport.team.entity.Skill;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {


   /*     User user=new User();
        user.setId(5);//делаю новый айди для юзера
        user.setName("Nikita");
        user.setPhone("78789778978998789");
        user.setEmail("mailpoitu@gmail.ru");
        List<Tool> tools=new ArrayList<>();//создаю два аррэй листа для скиллзов и тулзов
        List<Skill> skills=new ArrayList<>();
        Tool tool1=new Tool();//создаю два тулза
        Tool tool2=new Tool();
        tool1.setName("basel");
        tool1.setId(5);
        tool2.setName("hencel");
        tool2.setId(6);
        Skill skill1=new Skill();//создаю два скилза
        Skill skill2=new Skill();
        skill1.setName("baselling");
        skill1.setId(5);
        skill2.setName("hencellin");
        skill2.setId(6);
        tools.add(tool1);
        tools.add(tool2);
        skills.add(skill1);
        skills.add(skill2);
        user.setSkills(skills);
        user.setTools(tools);
        System.out.println(user.toString());
        UserService userService=new UserService();
        userService.add(user);*/

SkillService skillService=new SkillService();
        Skill sool1=new Skill(19,"upd1");
        Skill sool2=new Skill(20,"upd2");
        Skill sool4=new Skill(22,"upd3");
        Skill sool3=new Skill(21,"upd14");
        System.out.println(skillService.getAll().toString());
        skillService.delete(sool1);
        skillService.delete(sool2);
        skillService.delete(sool3);
        skillService.delete(sool4);

    }
}


