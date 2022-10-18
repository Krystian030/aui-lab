package jandy.krystian.lab1.service;

import jandy.krystian.lab1.model.Course;
import jandy.krystian.lab1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements jandy.krystian.lab1.service.Service<Course, Long> {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> find(Long id) {
        return courseRepository.find(id);
    }

    @Override
    public Optional<Course> find() {
        return Optional.empty();
    }

    @Override
    public List<Course> findAll() {
         return courseRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(id);
    }

    @Override
    public void delete() {

    }

    @Override
    public void create(Course entity) {
        courseRepository.create(entity);
    }

    @Override
    public void create() {

    }

    @Override
    public void update(Course entity) {
        courseRepository.update(entity);
    }

    @Override
    public void update() {

    }
}
