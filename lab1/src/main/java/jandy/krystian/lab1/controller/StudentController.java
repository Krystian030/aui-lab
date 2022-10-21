package jandy.krystian.lab1.controller;

import jandy.krystian.lab1.dto.student.*;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody PostStudentRequest postStudentRequest, UriComponentsBuilder builder) {
        Student student = Student.builder()
                .age(postStudentRequest.getAge())
                .name(postStudentRequest.getName())
                .surname(postStudentRequest.getSurname())
                .build();
        student = studentService.create(student);
        return ResponseEntity.created(builder.pathSegment("api", "student", "{id}")
                .buildAndExpand(student.getId()).toUri()).build();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentsResponse> readAll() {
        List<Student> students = studentService.findAll();
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
    public ResponseEntity<GetStudentResponse> read(@PathVariable(name = "id") Long id) {
        return studentService.find(id).map(
                (original) ->
                        ResponseEntity.ok(GetStudentResponse.builder()
                                .surname(original.getSurname())
                                .name(original.getName())
                                .id(original.getId())
                                .build())

        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id,
                                       @RequestBody PutStudentResponse studentResponse) {
        try {
            Student student = Student.builder()
                    .age(studentResponse.getAge())
                    .name(studentResponse.getName())
                    .surname(studentResponse.getSurname())
                    .build();
            studentService.update(id, student);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentService.delete(student.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addCourseToStudent(
            @RequestBody PatchStudentCourseAddResponse response,
            @PathVariable(name="id" ) Long id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentService.addCourseToStudent(response.getCourseId(), student.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentsResponse> readAllByCourseId(@PathVariable(name="courseId") Long courseId) {
        List<Student> 
    }
}
