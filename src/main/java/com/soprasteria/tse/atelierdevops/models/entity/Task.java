package com.soprasteria.tse.atelierdevops.models.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    @Column(name = "is_completed", nullable = false)
    @Type(type = "yes_no")
    @NotNull
    private boolean isCompleted;

    public Task() {}

    public Task(final Long id, final String description, final boolean isCompleted){
        this.setId(id);
        this.setDescription(description);
        this.setCompleted(isCompleted);
    }

    public Long getId() { return id; }
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
