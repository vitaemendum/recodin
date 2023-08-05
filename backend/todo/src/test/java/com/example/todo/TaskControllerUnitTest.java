package com.example.todo;

import com.example.todo.controller.TaskController;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TaskControllerUnitTest {

    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        taskController = new TaskController(taskService);
    }

    @Test
    public void testCreateTask() {
        Task inputTask = new Task(1L, "New Task", false);
        Task createdTask = new Task(1L, "New Task", false);
        ResponseEntity<Task> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

        when(taskService.createTask(inputTask)).thenReturn(createdTask);

        ResponseEntity<Task> actualResponse = taskController.createTask(inputTask);

        verify(taskService, times(1)).createTask(inputTask);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", false));
        tasks.add(new Task(2L, "Task 2", true));

        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> expectedResponse = ResponseEntity.ok(tasks);

        ResponseEntity<List<Task>> actualResponse = taskController.getAllTasks();

        verify(taskService, times(1)).getAllTasks();
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteTask() {
        Long taskId = 1L;
        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();

        doNothing().when(taskService).deleteTask(taskId);

        ResponseEntity<Void> actualResponse = taskController.deleteTask(taskId);

        verify(taskService, times(1)).deleteTask(taskId);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    public void testToggleTaskCompletion() {
        Long taskId = 1L;
        Task task = new Task(taskId, "Toggle Task", false);
        when(taskService.toggleTaskCompletion(taskId)).thenReturn(task);

        ResponseEntity<Task> expectedResponse = ResponseEntity.ok(task);

        task = taskController.toggleTaskCompletion(taskId);

        ResponseEntity<Task> actualResponse = ResponseEntity.ok(task);

        verify(taskService, times(1)).toggleTaskCompletion(taskId);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

