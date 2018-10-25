package com.sport.team.entity;

public class Service_Events_Projects {


    private int service_eventId;
    private  int projectId;

    public Service_Events_Projects() {
    }

    public Service_Events_Projects(int service_eventId, int projectId) {
        this.service_eventId = service_eventId;
        this.projectId = projectId;
    }

    public int getService_eventId() {
        return service_eventId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setService_eventId(int service_eventId) {
        this.service_eventId = service_eventId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
