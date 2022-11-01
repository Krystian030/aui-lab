package com.example.auistudent.course.service;

import com.example.auistudent.course.entity.Course;
import com.example.auistudent.course.repository.database.h2.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;


    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> find(String title) {
        return courseRepository.findById(title);
    }


    @Transactional
    public void delete(String title) {
//        setNull(id);
        courseRepository.deleteById(title);
    }

//    @Transactional
//    public void setNull(String id) {
//        Course course = courseRepository.findById(id).get();
//        List<Student> students = studentService.findAllByCourseId(id);
//        students.forEach( student -> student.setCourse(null));
//        course.setStudents(null);
//    }

    @Transactional
    public Course create(Course entity) {
        return courseRepository.save(entity);
    }
}
