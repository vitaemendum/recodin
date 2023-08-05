package com.example.todo;

import com.example.todo.controller.TaskController;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        Task newTask = new Task(1L, "New Task", false);
        when(taskService.createTask(any(Task.class))).thenReturn(newTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Task\",\"completed\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task1 = new Task(1L, "Task 1", false);
        Task task2 = new Task(2L, "Task 2", true);
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Task 2"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    public void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testToggleTaskCompletion() throws Exception {
        Task task = new Task(1L, "Toggle Task", false);
        when(taskService.toggleTaskCompletion(1L)).thenReturn(task);

        mockMvc.perform(patch("/api/tasks/1/toggle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Toggle Task"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}