package jandy.krystian.lab1.course.event.repository;

import jandy.krystian.lab1.course.entity.Course;
import jandy.krystian.lab1.course.event.dto.PostCourseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class CourseEventRepository {

    private RestTemplate restTemplate;

    static private final String BASE_URL = "http://localhost:8082/api/courses/";

    @Autowired
    public CourseEventRepository() {
        restTemplate = new RestTemplateBuilder().rootUri(BASE_URL).build();
    }

    public boolean delete(String title) {
        try {
            restTemplate.delete("/{title}", title);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean create(Course course) {
        try {
            restTemplate.postForLocation("/", PostCourseRequest.builder().title(course.getTitle()).build());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
