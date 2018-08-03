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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldFetchTasks() throws Exception {

        List<TaskDto> tasksDtoList = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L, "Title", "content");
        tasksDtoList.add(taskDto1);

        //List<Task> tasks = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(any(List.class))).thenReturn(tasksDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("Title")))
                .andExpect(jsonPath("$[0].content",is("content")));

    }
    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Title", "content");

        Task task1 = new Task(1L, "Title", "content");

        when(service.getTask(1L)).thenReturn(Optional.of(task1));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto1);



        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.title",is("Title")))
        .andExpect(jsonPath("$.content",is("content")));



    }

    @Test
    public void shouldDeleteTask() throws Exception {
        TaskDto taskDto1 = new TaskDto(1L, "Title", "content");

        Task task1 = new Task(1L, "Title", "content");

        when(service.getTask(1L)).thenReturn(Optional.of(task1));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto1);


        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Title")))
                .andExpect(jsonPath("$.content",is("content")));

        service.deleteTask(1L);
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Title", "content");

        Task task1 = new Task(1L, "Title", "content");

        when(service.saveTask(taskMapper.mapToTask(taskDto1))).thenReturn(task1);
       // when(service.saveTask(any(Task.class))).thenReturn(task1);
       // when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Title")))
                .andExpect(jsonPath("$.content",is("content")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "TestTask", "content1");

        TaskDto taskDto1 = new TaskDto(1L, "TestTask1", "content1");
        TaskDto taskDto2 = new TaskDto(1L, "TestTask2", "content2");

        //when(service.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(task1);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto2);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("TestTask2")))
                .andExpect(jsonPath("$.content", is("content2")));
    }

}
