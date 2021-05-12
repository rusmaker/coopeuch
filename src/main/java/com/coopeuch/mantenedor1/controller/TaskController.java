package com.coopeuch.mantenedor1.controller;

import com.coopeuch.mantenedor1.model.Task;
import com.coopeuch.mantenedor1.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTask (@Valid @RequestBody Task task) throws JsonProcessingException {

        taskRepository.save(task);

        return "Task guardada";
    }

    @PutMapping(path="/edit/{id}")
    public @ResponseBody String editTask (@Valid @RequestBody Task task,
                                          @PathVariable Long id) throws JsonProcessingException {

        Optional<Task> taskOpt;
        String respuesta = "Task actualizada";

        taskOpt = taskRepository.findById(id);

        if (!taskOpt.isPresent())
            respuesta = "Task inexistente";
        else {
            task.setId(id);
            taskRepository.save(task);
        }

        return respuesta;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @DeleteMapping(path= "/delete/{id}")
    public @ResponseBody String deleteTask(@PathVariable Long id) {

        Optional<Task> task;
        String respuesta = "Task borrada";

        task = taskRepository.findById(id);

        if (!task.isPresent())
            respuesta = "Task inexistente";
        else
            taskRepository.deleteById(id);

        return respuesta;
    }
}
