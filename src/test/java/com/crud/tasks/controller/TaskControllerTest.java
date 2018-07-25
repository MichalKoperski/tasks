package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private DbService service;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        Task task1 = new Task(1L, "Test task", "content1");
        List<Task> taskLists = new ArrayList<>();
        taskLists.add(task1);


        TaskDto taskDto1 = new TaskDto(1L, "Test task", "content1");
        List<TaskDto> taskListsDto = new ArrayList<>();
        taskListsDto.add(taskDto1);


        when(taskMapper.mapToTaskDtoList(taskLists)).thenReturn(taskListsDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1L)))
                .andExpect(jsonPath("$[0].title",is("Test task")))
                .andExpect(jsonPath("$[0].content",is("content1")));

    }
    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Test task", "content1");
        TaskDto taskDto = new TaskDto(1L, "Test task", "content1");

        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto);


        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$.id",is(1L)))
                .andExpect(jsonPath("$.title",is("Test task")))
                .andExpect(jsonPath("$.content",is("content1")));

    }

    @Test
    public void shouldDeleteTask() throws Exception {
        Task task1 = new Task(1L, "Test task", "content1");
        TaskDto taskDto = new TaskDto(1L, "Test task", "content1");

        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto);


        //When & Then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$.id",is(1L)))
                .andExpect(jsonPath("$.title",is("Test task")))
                .andExpect(jsonPath("$.content",is("content1")));

        service.deleteTask(1L);
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }



    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Test task", "content1");
        TaskDto taskDto = new TaskDto(1L, "Test task", "content1");

        when(service.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(task1);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);


        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("Test task")))
                .andExpect(jsonPath("$.content", is("content1")));
    }
    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Test task", "content1");
        TaskDto taskDto2 = new TaskDto(1L, "Test task2", "content2");

        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto1)))).thenReturn(taskDto2);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto2);


        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("Test task2")))
                .andExpect(jsonPath("$.content", is("content2")));
    }
}
