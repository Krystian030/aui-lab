package jandy.krystian.lab1.service;

import jandy.krystian.lab1.command.CommandLine;
import jandy.krystian.lab1.model.Course;
import jandy.krystian.lab1.model.Student;
import jandy.krystian.lab1.repository.CourseRepository;
import jandy.krystian.lab1.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService implements jandy.krystian.lab1.service.Service<Student, Long> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> find(Long id) {
        try {
            return studentRepository.find(id);
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
            return studentRepository.find(id);
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
    public void delete(Long id) {
        try {
            studentRepository.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            studentRepository.delete(id);
            log.info("Successful delete");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void create(Student entity) {
        try {
            studentRepository.create(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void create() {
        try {
            System.out.println(">>> STUDENT CREATE");
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            System.out.println("Enter age: ");
            Integer age = Integer.parseInt(CommandLine.scanner.nextLine());
            System.out.println("Enter name: ");
            String firstName = CommandLine.scanner.nextLine();
            System.out.println("Enter surname: ");
            String lastName = CommandLine.scanner.nextLine();
            studentRepository.create(Student.builder()
                    .id(id)
                    .age(age)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build());
            log.info("Successful create");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {
        try {
            studentRepository.update(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            System.out.println(">>> STUDENT UPDATE");
            System.out.println("Enter id: ");
            Long id = Long.parseLong(CommandLine.scanner.nextLine());
            System.out.println("Enter age: ");
            Integer age = Integer.parseInt(CommandLine.scanner.nextLine());
            System.out.println("Enter name: ");
            String firstName = CommandLine.scanner.nextLine();
            System.out.println("Enter surname: ");
            String lastName = CommandLine.scanner.nextLine();
            studentRepository.update(Student.builder()
                    .id(id)
                    .age(age)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build());
            log.info("Successful update");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
