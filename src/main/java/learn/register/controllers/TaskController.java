package learn.register.controllers;

import learn.register.data.TaskRepository;
import learn.register.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping
    public Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task Created Successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@PathVariable Long id, @ RequestBody Task task) {
        task.setId(id);
        taskRepository.update(task);
        return ResponseEntity.ok("Task Updated Successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskRepository.delete(id);
        return ResponseEntity.ok("Task Deleted Successfully");
    }
}
