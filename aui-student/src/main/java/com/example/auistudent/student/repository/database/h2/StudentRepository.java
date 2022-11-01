package com.example.auistudent.student.repository.database.h2;

import com.example.auistudent.course.entity.Course;
import com.example.auistudent.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCourse(Course course);

    List<Student> findAllByCourseTitle(String title);

    Optional<Student> findByIdAndCourse(Long id, Course course);
}
