package superai.dayofglory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import superai.dayofglory.dto.TaskRequestDTO;
import superai.dayofglory.models.Task;
import superai.dayofglory.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestParam("file") MultipartFile file) {
        return taskService.createTask(file);
    }

    @GetMapping(value="/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id,
                                           @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task)
                .map(taskData -> {
                    return ResponseEntity.ok().body(taskData);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        boolean deleted = taskService.deleteTask(id);
        return  deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}