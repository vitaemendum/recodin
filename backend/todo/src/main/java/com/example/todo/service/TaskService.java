package com.example.todo.service;

import com.example.todo.exception.InvalidTaskException;
import com.example.todo.exception.TaskNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        return taskRepository.findAll(sort);
        return taskRepository.findAll();
    }

    public Task createTask(Task task){
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new InvalidTaskException("Task title cannot be empty.");
        }
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public Task toggleTaskCompletion(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setCompleted(!task.getCompleted());
        return taskRepository.save(task);
    }
}
