package jandy.krystian.lab1.controller;

import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @PostMapping("/")
    public void create(@RequestBody StudentDto studentDto) {
        System.out.println(studentDto);
    }

    @DeleteMapping("/{id}")
    public void create(@PathVariable(name = "id") Long id) {
        studentService.delete(id);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable(name = "id") Long id) {
        return studentService.find(id).get();
    }
}
