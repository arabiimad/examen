package com.soprasteria.tse.atelierdevops.models.dto;

public class TaskResponse {
    private Long id;
    private String description;
    private boolean isCompleted;

    public TaskResponse() {}

    public TaskResponse(final Long id, final String description, final boolean isCompleted){
        this.setId(id);
        this.setDescription(description);
        this.setCompleted(isCompleted);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
