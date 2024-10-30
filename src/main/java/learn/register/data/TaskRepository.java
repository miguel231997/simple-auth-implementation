package learn.register.data;

import learn.register.models.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();
    Task findById(Long id);
    int save(Task task);
    int update(Task task);
    int delete(Long id);
}
