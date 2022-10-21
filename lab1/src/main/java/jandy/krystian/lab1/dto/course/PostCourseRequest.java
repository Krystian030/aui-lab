package jandy.krystian.lab1.dto.course;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostCourseRequest {

    private String title;

    private String language;
}
