package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskDtoTest {

    @Test
    public void getterTest() {
        TaskDto taskDto = new TaskDto(2L, "t", "s");
        assertEquals(taskDto.getContent(), "s");
        assertEquals(taskDto.getTitle(), "t");
        assertEquals(taskDto.getId(), new Long(2));
    }
}