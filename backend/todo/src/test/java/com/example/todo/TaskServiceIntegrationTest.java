package com.example.todo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        // Mock the behavior of taskRepository.findAll()
        Task task1 = new Task(1L, "Task 1", false);
        Task task2 = new Task(2L, "Task 2", true);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> retrievedTasks = taskService.getAllTasks();

        assertEquals(2, retrievedTasks.size());
        assertEquals(task1.getTitle(), retrievedTasks.get(0).getTitle());
        assertEquals(task2.getTitle(), retrievedTasks.get(1).getTitle());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task(null, "New Task", false);

        // Mock the behavior of taskRepository.save()
        Task savedTask = new Task(1L, "New Task", false);
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);

        Task createdTask = taskService.createTask(newTask);

        assertNotNull(createdTask.getId());
        assertEquals(newTask.getTitle(), createdTask.getTitle());
        assertFalse(createdTask.getCompleted());
    }

    @Test
    public void testDeleteTask() {
        Long taskIdToDelete = 1L;

        // Mock the behavior of taskRepository.existsById() and taskRepository.deleteById()
        when(taskRepository.existsById(taskIdToDelete)).thenReturn(true);

        assertDoesNotThrow(() -> taskService.deleteTask(taskIdToDelete));
    }

    @Test
    public void testToggleTaskCompletion() {
        Long taskIdToToggle = 1L;
        Task existingTask = new Task(taskIdToToggle, "Toggle Task", false);

        // Mock the behavior of taskRepository.findById() and taskRepository.save()
        when(taskRepository.findById(taskIdToToggle)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(existingTask);

        Task toggledTask = taskService.toggleTaskCompletion(taskIdToToggle);

        assertEquals(existingTask.getCompleted(), toggledTask.getCompleted());
    }
}
