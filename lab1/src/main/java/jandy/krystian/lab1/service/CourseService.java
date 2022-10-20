package jandy.krystian.lab1.service;

import jandy.krystian.lab1.command.CommandLine;
import jandy.krystian.lab1.model.Course;
import jandy.krystian.lab1.model.Student;
import jandy.krystian.lab1.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return courseRepository.find(id);
    }

    @Override
    public Optional<Course> find() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            return courseRepository.find(id);
        } catch (Exception e) {
            log.error("Unexpected error occurs: ", e);
            return Optional.empty();
        }
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
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            courseRepository.delete(id);
            log.info("Successful delete");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void create(Course entity) {
        courseRepository.create(entity);
    }

    @Override
    public void create() {
        try {
            System.out.println(">>> COURSE CREATE");
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            System.out.println("Enter title: ");
            String title = CommandLine.scanner.nextLine();
            courseRepository.create(Course.builder()
                    .id(id)
                    .title(title)
                    .build()
            );
            log.info("Successful create");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update(Course entity) {
        courseRepository.update(entity);
    }

    @Override
    public void update() {
        try {
            System.out.println(">>> COURSE UPDATE");
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            System.out.println("Enter title: ");
            String title = CommandLine.scanner.nextLine();
            courseRepository.update(Course.builder()
                    .id(id)
                    .title(title)
                    .students(new ArrayList<>())
                    .build()
            );
            log.info("Successful update");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void addToCourse() {
        try {
            System.out.println("Enter student id: ");
            Long studentId = Long.parseLong(CommandLine.scanner.nextLine());
            Student student = studentService.find(studentId).get();
            System.out.println("Enter course id: ");
            Long courseId = Long.parseLong(CommandLine.scanner.nextLine());
            Optional<Course> course = find(courseId);
            course.ifPresentOrElse(
                    tmp -> {
                        course.get().getStudents().add(student);
                    },
                    () -> {
                        throw new IllegalArgumentException("Cannot add student to course");
                    });
            log.info("Student is added to course");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
