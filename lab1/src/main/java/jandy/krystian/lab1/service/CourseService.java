package jandy.krystian.lab1.service;

import jandy.krystian.lab1.model.Course;
import jandy.krystian.lab1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> find(Long id) {
        return courseRepository.find(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }

    public void create(Course entity) {
        courseRepository.create(entity);
    }

    public void update(Course entity) {
        courseRepository.update(entity);
    }
}
