package learn.register.data;

import learn.register.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TaskJdbcTemplateRepository implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  TaskJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Task(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status")));
    }

    @Override
    public Task findById(Long id) {
        String sql = "SELECT * FROM tasks where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Task(rs.getLong("id"), rs.getString("title"), rs.getString("description"), rs.getString("status")));
    }

    @Override
    public int save(Task task) {
        String sql = "INSERT INTO tasks (title, description, status) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus());
    }

    @Override
    public int update(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ?";
        return jdbcTemplate.update(sql,task.getClass(), task.getDescription(), task.getStatus());
    }

    @Override
    public int delete(Long id){
        String sql = "DELETE FROM tasks WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
