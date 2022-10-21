package jandy.krystian.lab1.service;

import jandy.krystian.lab1.command.CommandLine;
import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.repository.database.h2.CourseRepository;
import jandy.krystian.lab1.repository.database.h2.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService implements jandy.krystian.lab1.service.Service<Student, Long> {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Student> find(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> find() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            return studentRepository.findById(id);
        } catch (Exception e) {
            log.error("Unexpected error occurs: ", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            studentRepository.deleteById(id);
            log.info("Successful delete");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Student create(Student entity) {
        try {
            return studentRepository.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public void create() {
        try {
            System.out.println(">>> STUDENT CREATE");
            System.out.println("Enter age: ");
            Integer age = Integer.parseInt(CommandLine.scanner.nextLine());
            System.out.println("Enter name: ");
            String firstName = CommandLine.scanner.nextLine();
            System.out.println("Enter surname: ");
            String surname = CommandLine.scanner.nextLine();
            Long course = null;
            try {
                System.out.println("Enter course id: ");
                course = Long.parseLong(CommandLine.scanner.nextLine());
            } catch (Exception e) {
                log.error("Cannot parse to long value");
            }
            studentRepository.save(Student.builder()
                    .age(age)
                    .name(firstName)
                    .surname(surname)
                    .build());
            log.info("Successful create");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<Student> findAllByCourseId() {
        System.out.println("Enter course id: ");
        Long courseId = Long.parseLong(CommandLine.scanner.nextLine());
        return studentRepository.findAllByCourseId(courseId);
    }

    @Override
    @Transactional
    public void update() {
        try {
            System.out.println(">>> STUDENT UPDATE");
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            studentRepository.findById(id).ifPresentOrElse(
                    (student) -> {
                        System.out.println("Enter age: ");
                        Integer age = Integer.parseInt(CommandLine.scanner.nextLine());
                        System.out.println("Enter name: ");
                        String firstName = CommandLine.scanner.nextLine();
                        System.out.println("Enter surname: ");
                        String lastName = CommandLine.scanner.nextLine();
                        System.out.println("Enter course id: ");
                        Long courseId = Long.parseLong(CommandLine.scanner.nextLine());
                        student.setCourse(courseRepository.getById(courseId));
                        student.setSurname(lastName);
                        student.setName(firstName);
                        student.setAge(age);
                    },
                    () -> {
                        throw new IllegalArgumentException();
                    }
            );
            log.info("Successful update");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public void update(Long id, Student student) {
        find(id).ifPresentOrElse(
                (original) -> {
                    original.setSurname(student.getSurname());
                    original.setAge(student.getAge());
                    original.setName(student.getName());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update student");
                }
        );
    }

    @Transactional
    public void addCourseToStudent(Long courseId, Student student) {
        try {
            Optional<Course> course = courseRepository.findById(courseId);
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
}
