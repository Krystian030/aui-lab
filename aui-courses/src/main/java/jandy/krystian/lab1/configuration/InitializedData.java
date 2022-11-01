package jandy.krystian.lab1.configuration;

import jandy.krystian.lab1.course.entity.Course;
import jandy.krystian.lab1.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class InitializedData {

    private final CourseService courseService;

    @Autowired
    public InitializedData(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostConstruct
    @Transactional
    public void init() {

        Course course1 = Course.builder()
                .title("PROGRAMMING-COURSE")
                .language("pl")
                .rating(4.5)
                .build();
        Course course2 = Course.builder()
                .title("COOKING-COURSE")
                .language("en")
                .rating(3.0)
                .build();

        courseService.create(course1);
        courseService.create(course2);
    }
}
