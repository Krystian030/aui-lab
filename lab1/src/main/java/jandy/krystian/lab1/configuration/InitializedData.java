package jandy.krystian.lab1.configuration;

import jandy.krystian.lab1.entity.Course;
import jandy.krystian.lab1.entity.Student;
import jandy.krystian.lab1.service.CourseService;
import jandy.krystian.lab1.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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
    @Transactional
    public void init() {

        Course course1 = Course.builder()
                .title("PROGRAMMING COURSE")
                .language("pl")
                .build();
        Course course2 = Course.builder()
                .title("COOKING COURSE")
                .language("en")
                .build();

        courseService.create(course1);
        courseService.create(course2);

        Student student1 = Student.builder()
                .age(18)
                .name("Andrew")
                .surname("Smith")
                .course(course1)
                .build();

        Student student2 = Student.builder()
                .age(19)
                .name("Matthew")
                .surname("Brigs")
                .course(course1)
                .build();

        Student student3 = Student.builder()
                .age(16)
                .name("Anna")
                .surname("White")
                .course(course2)
                .build();

        studentService.create(student1);
        studentService.create(student2);
        studentService.create(student3);
    }
}
