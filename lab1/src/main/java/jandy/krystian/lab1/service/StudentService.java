package jandy.krystian.lab1.service;

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
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> find(Long id) {
        try {
            return studentRepository.find(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void delete(Long id) {
        try {
            studentRepository.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void create(Student entity) {
        try {
            studentRepository.create(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void update(Student entity) {
        try {
            studentRepository.update(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
