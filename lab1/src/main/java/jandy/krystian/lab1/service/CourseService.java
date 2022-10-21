package jandy.krystian.lab1.service;

import jandy.krystian.lab1.command.CommandLine;
import jandy.krystian.lab1.dto.course.PutCourseResponse;
import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.repository.database.h2.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService implements jandy.krystian.lab1.service.Service<Course, Long> {

    private final CourseRepository courseRepository;

    private final StudentService studentService;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public Optional<Course> find(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<Course> find() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            return courseRepository.findById(id);
        } catch (Exception e) {
            log.error("Unexpected error occurs: ", e);
            return Optional.empty();
        }
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
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            courseRepository.deleteById(id);
            log.info("Successful delete");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Course create(Course entity) {
        return courseRepository.save(entity);
    }

    @Override
    @Transactional
    public void create() {
        try {
            System.out.println(">>> COURSE CREATE");
            System.out.println("Enter title: ");
            String title = CommandLine.scanner.nextLine();
            courseRepository.save(Course.builder()
                    .title(title)
                    .students(new ArrayList<>())
                    .build()
            );
            log.info("Successful create");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update() {

    }

    @Transactional
    public void addToCourse() {
        try {
            System.out.println("Enter student id: ");
            Long studentId = Long.parseLong(CommandLine.scanner.nextLine());
            Student student = studentService.find(studentId).get();
            System.out.println("Enter course id: ");
            Long courseId = Long.parseLong(CommandLine.scanner.nextLine());
            Optional<Course> course = find(courseId);
            course.ifPresentOrElse(
                    student::setCourse,
                    () -> {
                        throw new IllegalArgumentException("Cannot add student to course");
                    });
            log.info("Student is added to course");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public void update(Long id, PutCourseResponse courseResponse) {
        find(id).ifPresentOrElse(
                (original) -> {
                    original.setTitle(courseResponse.getTitle());
                    original.setLanguage(courseResponse.getLanguage());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update student");
                }
        );
    }
}
