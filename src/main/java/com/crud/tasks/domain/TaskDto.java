package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDto {

    public TaskDto() {}
    private Long id;
    private String title;
    private String content;
}
