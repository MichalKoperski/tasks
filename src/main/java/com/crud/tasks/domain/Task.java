package com.crud.tasks.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String title;
    @Column(name="description")
    private String content;

    public Task(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public Task() {}
}
