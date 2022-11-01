package jandy.krystian.lab1.course.service;

import jandy.krystian.lab1.course.dto.PutCourseResponse;
import jandy.krystian.lab1.course.entity.Course;
import jandy.krystian.lab1.course.event.repository.CourseEventRepository;
import jandy.krystian.lab1.course.repository.database.h2.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService implements jandy.krystian.lab1.course.service.Service<Course, String> {

    private final CourseRepository courseRepository;
    private final CourseEventRepository courseEventRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseEventRepository courseEventRepository) {
        this.courseRepository = courseRepository;
        this.courseEventRepository = courseEventRepository;
    }

    @Override
    public Optional<Course> find(String title) {
        return courseRepository.findById(title);
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = null;
        try {
            courses = courseRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return courses;
    }

    @Override
    @Transactional
    public void delete(String title) {
        courseEventRepository.delete(title);
        courseRepository.deleteById(title);
    }


    @Override
    @Transactional
    public Course create(Course entity) {
        if (find(entity.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Course has already exists");
        }

        courseEventRepository.create(entity);
        return courseRepository.save(entity);
    }

    @Transactional
    public void update(String title, PutCourseResponse courseResponse) {
        find(title).ifPresentOrElse(
                (original) -> {
                    original.setRating(courseResponse.getRating());
                    original.setLanguage(courseResponse.getLanguage());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update student");
                }
        );
    }
}
