package jandy.krystian.lab1.controller;

import jandy.krystian.lab1.dto.course.GetCourseResponse;
import jandy.krystian.lab1.dto.course.GetCoursesResponse;
import jandy.krystian.lab1.dto.course.PostCourseRequest;
import jandy.krystian.lab1.dto.course.PutCourseResponse;
import jandy.krystian.lab1.dto.student.GetStudentResponse;
import jandy.krystian.lab1.dto.student.GetStudentsResponse;
import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
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
                .language(postCourseRequest.getLanguage())
                .students(new ArrayList<>())
                .build();
        course = courseService.create(course);
        return ResponseEntity.created(builder.pathSegment("api", "course", "{id}")
                .buildAndExpand(course.getId()).toUri()).build();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetCoursesResponse> readAll() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(
                GetCoursesResponse.builder()
                        .courses(
                                courses.stream().map(
                                        (course) -> GetCourseResponse.builder()
                                                .title(course.getTitle())
                                                .id(course.getId())
                                                .language(course.getLanguage())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetCourseResponse> read(@PathVariable(name = "id") Long id) {
        Optional<Course> response = courseService.find(id);
        if (response.isPresent()) {
            Course course = response.get();
            return ResponseEntity.ok(GetCourseResponse.builder()
                    .id(course.getId())
                    .language(course.getLanguage())
                    .title(course.getTitle())
                    .build());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id,
                                       @RequestBody PutCourseResponse courseResponse) {
        try {
            Course student = Course.builder()
                    .title(courseResponse.getTitle())
                    .language(courseResponse.getLanguage())
                    .build();
            courseService.update(id, courseResponse);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        Optional<Course> course = courseService.find(id);
        if (course.isPresent()) {
            courseService.delete(course.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentsResponse> readAllByCourseId(@PathVariable(name="courseId") Long courseId) {
        List<Student> students = courseService.findAllByCourseId(courseId);
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
}
