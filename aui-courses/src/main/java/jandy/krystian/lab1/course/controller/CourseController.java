package jandy.krystian.lab1.course.controller;

import jandy.krystian.lab1.course.dto.GetCourseResponse;
import jandy.krystian.lab1.course.dto.GetCoursesResponse;
import jandy.krystian.lab1.course.dto.PostCourseRequest;
import jandy.krystian.lab1.course.dto.PutCourseResponse;
import jandy.krystian.lab1.course.entity.Course;
import jandy.krystian.lab1.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .language(postCourseRequest.getLanguage())
                .rating(postCourseRequest.getRating())
                .build();
        try {
            course = courseService.create(course);
            return ResponseEntity.created(builder.pathSegment("api", "course", "{title}")
                    .buildAndExpand(course.getTitle()).toUri()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
                                                .language(course.getLanguage())
                                                .rating(course.getRating())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }


    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetCourseResponse> read(@PathVariable(name = "title") String title) {
        Optional<Course> response = courseService.find(title);
        if (response.isPresent()) {
            Course course = response.get();
            return ResponseEntity.ok(GetCourseResponse.builder()
                    .language(course.getLanguage())
                    .title(course.getTitle())
                    .rating(course.getRating())
                    .build());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{title}")
    public ResponseEntity<Void> update(@PathVariable(name = "title") String title,
                                       @RequestBody PutCourseResponse courseResponse) {
        try {
            courseService.update(title, courseResponse);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "title") String title) {
        Optional<Course> course = courseService.find(title);
        if (course.isPresent()) {
            courseService.delete(course.get().getTitle());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
