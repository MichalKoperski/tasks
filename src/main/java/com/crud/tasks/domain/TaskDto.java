package com.crud.tasks.domain;

import lombok.Getter;

@Getter
public class TaskDto {

    private Long id;
    private String title;
    private String content;

    public TaskDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public TaskDto() {}
}
