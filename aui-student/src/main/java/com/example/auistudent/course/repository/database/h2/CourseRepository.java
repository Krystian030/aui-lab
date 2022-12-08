package com.example.auistudent.course.repository.database.h2;

import com.example.auistudent.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, String> {

    Optional<Course> findByTitle(String s);
}
