package com.example.todo.config;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    CommandLineRunner commandLineRunner (TaskRepository taskRepository) {
        return args -> {
            Task garbage = new Task (
                1L,
                "Take out garbage",
                true
            );
            Task shopping = new Task (
                    2L,
                    "Go shopping",
                    false
            );
            Task guitar = new Task (

                    3L,
                    "Play guitar",
                    false
            );

            taskRepository.saveAll(List.of(garbage, shopping, guitar));
        };
    }
}
