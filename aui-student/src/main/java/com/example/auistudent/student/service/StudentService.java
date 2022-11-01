package com.example.auistudent.student.service;

import com.example.auistudent.course.entity.Course;
import com.example.auistudent.course.repository.database.h2.CourseRepository;
import com.example.auistudent.student.entity.Student;
import com.example.auistudent.student.repository.database.h2.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Optional<Student> find(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public Student create(Student entity) {
        try {
            return studentRepository.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<Student> findAllByCourseTitle(String title) {
        return studentRepository.findAllByCourseTitle(title);
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

    public Optional<Student> find(String title, Long id) {
        Optional<Course> course = courseRepository.findById(title);
        if (course.isPresent()) {
            return studentRepository.findByIdAndCourse(id, course.get());
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void addCourseToStudent(String courseId, Student student) {
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
