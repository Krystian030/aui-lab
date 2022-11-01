package jandy.krystian.lab1.course.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostCourseRequest {

    private String title;

    private String language;

    private Double rating;
}
