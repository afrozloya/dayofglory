package superai.dayofglory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import superai.dayofglory.misc.TaskStatus;
import superai.dayofglory.models.Task;
import superai.dayofglory.repositories.TaskRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return taskRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        task.setStatus(TaskStatus.ACTIVE.name());
        return taskRepository.save(task);
    }

    @GetMapping(value="/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id,
                                           @Valid @RequestBody Task task) {
        return taskRepository.findById(id)
                .map(taskData -> {
                    taskData.setAnotations(task.getAnotations());
                    taskData.setStatus(TaskStatus.READY.name());
                    taskData.setVersion(taskData.getVersion()+1);
                    Task updatedTask = taskRepository.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}