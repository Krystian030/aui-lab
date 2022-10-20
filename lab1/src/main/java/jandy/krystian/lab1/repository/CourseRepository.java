package jandy.krystian.lab1.repository;

import jandy.krystian.lab1.datastore.DataStore;
import jandy.krystian.lab1.model.Course;
import jandy.krystian.lab1.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CourseRepository implements Repository<Course, Long> {

    private final DataStore store;

    @Autowired
    public CourseRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Course> find(Long id) {
        return store.findCourse(id);
    }

    @Override
    public List<Course> findAll() {
        return store.findAllCourses();
    }

    @Override
    public void delete(Long id) {
        store.deleteCourse(id);
    }

    @Override
    public void create(Course entity) {
        store.createCourse(entity);
    }

    @Override
    public void update(Course entity) {
        store.updateCourse(entity);
    }
}
