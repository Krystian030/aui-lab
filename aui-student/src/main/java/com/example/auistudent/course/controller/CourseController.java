package com.example.auistudent.course.controller;

import com.example.auistudent.course.dto.PostCourseRequest;
import com.example.auistudent.course.entity.Course;
import com.example.auistudent.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody PostCourseRequest postCourseRequest, UriComponentsBuilder builder) {
        Course course = Course.builder()
                .title(postCourseRequest.getTitle())
                .students(new ArrayList<>())
                .build();
        course = courseService.create(course);
        return ResponseEntity.created(builder.pathSegment("api", "course", "{id}")
                .buildAndExpand(course.getTitle()).toUri()).build();
    }

    @DeleteMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "title") String title) {
        Optional<Course> course = courseService.find(title);
        if (course.isPresent()) {
            courseService.delete(course.get().getTitle());
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.notFound().build();
    }
}
