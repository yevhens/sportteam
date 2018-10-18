package com.sport.team.entity;

public class Users_Skills {

    private int userId;
    private int skillId;

    public int getUserId() {
        return userId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public Users_Skills(int userId, int skillId) {
        this.userId = userId;
        this.skillId = skillId;
    }

    public Users_Skills() {
    }

    @Override
    public String toString() {
        return "Users_Skills{" +
                "userId=" + userId +
                ", skillId=" + skillId +
                '}';
    }
}
