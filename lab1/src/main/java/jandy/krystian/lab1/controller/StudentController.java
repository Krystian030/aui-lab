package jandy.krystian.lab1.controller;

import jandy.krystian.lab1.dto.GetStudentResponse;
import jandy.krystian.lab1.dto.PostStudentRequest;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostStudentRequest postStudentRequest) {
        System.out.println(postStudentRequest);
        System.out.println("test");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@PathVariable(name = "id") Long id) {
        studentService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetStudentResponse> get(@PathVariable(name = "id") Long id) {
        return studentService.find(id).map(
                (original) ->
                        ResponseEntity.ok(GetStudentResponse.builder()
                                .surname(original.getSurname())
                                .name(original.getName())
                                .id(original.getId())
                                .build())

        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
