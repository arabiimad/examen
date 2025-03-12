package com.soprasteria.tse.atelierdevops.models.dto;

public class TaskRequest {
    private String description;
    private Boolean isCompleted = false;

    public TaskRequest(){}

    public TaskRequest(final String description, final Boolean isCompleted){
        this.setDescription(description);
        this.setCompleted(isCompleted);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
