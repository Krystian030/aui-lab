package com.example.auistudent.student.controller;

import com.example.auistudent.course.entity.Course;
import com.example.auistudent.course.service.CourseService;
import com.example.auistudent.student.dto.*;
import com.example.auistudent.student.entity.Student;
import com.example.auistudent.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses/{title}/students")
public class CourseStudentController {

    private CourseService courseService;

    private StudentService studentService;

    @Autowired
    private CourseStudentController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PatchMapping("/{id}/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addCourseToStudent(
            @PathVariable(name = "title") String title,
            @PathVariable(name = "id") Long id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentService.addCourseToStudent(title, student.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentsResponse> getStudents(@PathVariable(name = "title") String title) {
        List<Student> students = studentService.findAllByCourseTitle(title);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                GetStudentsResponse.builder()
                        .students(
                                students.stream().map(
                                        (student) -> GetStudentResponse.builder()
                                                .surname(student.getSurname())
                                                .id(student.getId())
                                                .name(student.getName())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentResponse> getStudent(@PathVariable(name = "title") String title,
                                                         @PathVariable(name = "id") Long id
    ) {
        Optional<Student> student = studentService.find(title, id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return student.map(
                (original) ->
                        ResponseEntity.ok(GetStudentResponse.builder()
                                .surname(original.getSurname())
                                .name(original.getName())
                                .id(original.getId())
                                .build())
        ).get();
    }

    @PostMapping("/")
    public ResponseEntity<Void> createStudent(@PathVariable(name = "title") String title,
                                              @RequestBody PostStudentRequest postStudentRequest) {
        Optional<Course> course = courseService.find(title);
        if (course.isPresent()) {
            Student student = Student.builder()
                    .course(course.get())
                    .age(postStudentRequest.getAge())
                    .surname(postStudentRequest.getSurname())
                    .name(postStudentRequest.getName())
                    .build();
            studentService.create(student);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "title") String title,
                                              @PathVariable(name = "id") Long id) {
        Optional<Student> student = studentService.find(title, id);
        if (student.isPresent()) {
            studentService.delete(student.get().getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable(name = "title") String title,
                                              @PathVariable(name = "id") Long id,
                                              @RequestBody PutStudentResponse studentResponse) {
        Optional<Student> student = studentService.find(title, id);
        if (student.isPresent()) {
            Student updatedStudent = Student.builder()
                    .age(studentResponse.getAge())
                    .name(studentResponse.getName())
                    .surname(studentResponse.getSurname())
                    .build();
            studentService.update(student.get().getId(), updatedStudent);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
