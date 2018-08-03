package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        TaskDto taskDto = new TaskDto(1L, "title","b1");
        Task task1 = new Task(1L, "Title", "b1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task1);

        assertEquals("Title", task1.getTitle());
        assertEquals(task1.getId(),new Long(1));
        assertEquals(task1.getContent(),"b1");
    }

    @Test
    public void mapToTaskDtoTest() {
        TaskDto taskDto = new TaskDto(1L, "Title","b1");
        Task task1 = new Task(1L, "Title", "b1");

        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto);

        assertEquals("Title", taskDto.getTitle());
        assertEquals(taskDto.getId(),new Long(1));
        assertEquals(taskDto.getContent(),"b1");
    }

    @Test
    public void mapToTaskDtoList() {
        Task task = new Task(1L, "a1","b1");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        TaskDto taskDto = new TaskDto(1L, "a1","b1");
        List<TaskDto> mappedTaskList = new ArrayList<>();
        mappedTaskList.add(taskDto);

        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(mappedTaskList);

        assertEquals(mappedTaskList.get(0).getTitle(), "a1");
        assertEquals(mappedTaskList.get(0).getId(), new Long(1));
        assertEquals(mappedTaskList.get(0).getContent(), "b1");
    }
}