package jandy.krystian.lab1.command;

import jandy.krystian.lab1.service.CourseService;
import jandy.krystian.lab1.service.StudentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class InitializeServices {
    public static CourseService courseService;
    public static StudentService studentService;

    @Autowired
    public InitializeServices(CourseService serviceForCourse, StudentService serviceForStudent) {
        courseService = serviceForCourse;
        studentService = serviceForStudent;
    }
}
