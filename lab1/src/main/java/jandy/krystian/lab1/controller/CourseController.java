package jandy.krystian.lab1.controller;

import jandy.krystian.lab1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void create() {
        System.out.println("create");
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void readAll() {
        System.out.println("readAll");
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void read(@PathVariable(name = "id") Long id) {
//        return courseService.find(id).map(
//                (original) ->
//                        ResponseEntity.ok(GetStudentResponse.builder()
//                                .surname(original.getSurname())
//                                .name(original.getName())
//                                .id(original.getId())
//                                .build())
//
//        ).orElseGet(() -> ResponseEntity.notFound().build());
        System.out.println("read");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") Long id) {
        System.out.println("update");
        return ResponseEntity.ok("update");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "id") Long id) {
//        courseService.delete(id);
        System.out.println("delete");
    }
}
