package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getterTest() {
        Task task = new Task(3L, "s", "k");

        assertEquals(task.getContent(), "k");
        assertEquals(task.getTitle(), "s");
        assertEquals(task.getId(), new Long(3));
    }

}