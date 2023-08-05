package com.example.todo;

import com.example.todo.exception.TaskNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("Sample Task");

        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask);
        assertFalse(createdTask.getCompleted());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteExistingTask() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    public void testDeleteNonExistingTask() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(taskId));

        verify(taskRepository, never()).deleteById(taskId);
    }

    @Test
    public void testToggleTaskCompletion() {
        Long taskId = 1L;
        Task task = new Task();
        task.setCompleted(false);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        Task toggledTask = taskService.toggleTaskCompletion(taskId);

        assertTrue(toggledTask.getCompleted());
        verify(taskRepository, times(1)).save(task);
    }
}
