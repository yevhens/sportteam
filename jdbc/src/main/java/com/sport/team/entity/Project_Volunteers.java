package com.sport.team.entity;

public class Project_Volunteers {


    private  int projectId;
    private int userId;

    public Project_Volunteers() {
    }

    public Project_Volunteers(int projectId, int userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
