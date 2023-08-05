package com.example.todo.model;

import jakarta.persistence.*;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_id",
            sequenceName = "task_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id"
    )
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Boolean completed;

    public Task() {
    }

    public Task(String title, Boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public Task(Long id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
