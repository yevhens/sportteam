package com.sport.team.entity;

public class Users_Tools {


    private  int toolId;
    private int userId;

    public Users_Tools() {
    }

    public Users_Tools(int toolId, int userId) {
        this.toolId = toolId;
        this.userId = userId;
    }

    public int getToolId() {
        return toolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Users_Tools{" +
                "toolId=" + toolId +
                ", userId=" + userId +
                '}';
    }
}
