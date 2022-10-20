package jandy.krystian.lab1.configuration;

import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.CourseService;
import jandy.krystian.lab1.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@Slf4j
public class InitializedData {

    private final CourseService courseService;

    private final StudentService studentService;

    @Autowired
    public InitializedData(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PostConstruct
    private void init() {
        Student student1 = Student.builder()
                .id(1L)
                .age(18)
                .firstName("Andrew")
                .lastName("Smith")
                .build();

        Student student2 = Student.builder()
                .id(2L)
                .age(19)
                .firstName("Matthew")
                .lastName("Brigs")
                .build();

        Student student3 = Student.builder()
                .id(3L)
                .age(16)
                .firstName("Anna")
                .lastName("White")
                .build();

        studentService.create(student1);
        studentService.create(student2);
        studentService.create(student3);

        Course course1 = Course.builder()
                .id(1L)
                .title("PROGRAMMING COURSE")
                .students(new ArrayList<>())
                .build();

        Course course2 = Course.builder()
                .id(2L)
                .title("COOKING COURSE")
                .students(new ArrayList<>())
                .build();

        courseService.create(course1);
        courseService.create(course2);
    }
}
