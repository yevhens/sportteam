package com.sport.team.service;

import com.sport.team.Util;
import com.sport.team.entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {

   /*     User user=new User();
        user.setId(20);//делаю новый айди для юзера
        user.setName("Cory");
        user.setPhone("78990101");
        user.setEmail("corry@gmail.ru");
        List<Tool> tools=new ArrayList<>();//создаю два аррэй листа для скиллзов и тулзов
        List<Skill> skills=new ArrayList<>();
        Tool tool1=new Tool();//создаю два тулза
        Tool tool2=new Tool();
        tool1.setName("corr");
        tool1.setId(20);
        tool2.setName("zorr");
        tool2.setId(21);
        Skill skill1=new Skill();//создаю два скилза
        Skill skill2=new Skill();
        skill1.setName("corrin");
        skill1.setId(20);
        skill2.setName("zorring");
        skill2.setId(21);
        tools.add(tool1);
        tools.add(tool2);
        skills.add(skill1);
        skills.add(skill2);
        user.setSkills(skills);
        user.setTools(tools);
        Skill_Service skillService=new Skill_Service();
       Tool_Service toolService=new Tool_Service();
       skillService.add(skill1);
       skillService.add(skill2);
       toolService.add(tool1);
       toolService.add(tool2);
       User_Service userService=new User_Service();
       userService.add(user);


       Project_Service project_service=new Project_Service();
       Project project=new Project(3,"Kiev","Vinnitsa","Kyiv",new java.sql.Date(2014-10-21),"ghgh@gmail.com","John","Sokyrko","Soku","3224","USA","78787",
               "dssds");
        project.setOrganizer(user);
        project.setSubmitter(user);
       project_service.add(project);
     Comment_Service comment_service=new Comment_Service();
     Comment comment=new Comment();
     Date date=new Date(2008-10-29);
     comment.setId(1);
     comment.setDateAdded(date);
     comment.setSubmitter(user);
     comment.setText("This is a test");
     comment.setProject(project);

     comment_service.add(comment);*/

        Util.initDB2();








    }
}


