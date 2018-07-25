package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        TaskDto taskDto = new TaskDto(1L, "a1","b1");
        Task mappedTask = taskMapper.mapToTask(taskDto);

        assertEquals("a1", mappedTask.getTitle());
        assertEquals(mappedTask.getId(),new Long(1));
        assertEquals(mappedTask.getContent(),"b1");
    }

    @Test
    public void mapToTaskDto() {
        Task task = new Task(1L, "a1","b1");
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        assertEquals(mappedTaskDto.getTitle(),"a1");
        assertEquals(mappedTaskDto.getId(),new Long(1));
        assertEquals(mappedTaskDto.getContent(),"b1");
    }

    @Test
    public void mapToTaskDtoList() {
        Task task = new Task(1L, "a1","b1");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        TaskDto taskDto = new TaskDto(1L, "a1","b1");
        List<TaskDto> mappedTaskList = new ArrayList<>();
        mappedTaskList.add(taskDto);

        assertEquals(mappedTaskList.get(0).getTitle(), taskMapper.mapToTaskDtoList(taskList));
    }
}