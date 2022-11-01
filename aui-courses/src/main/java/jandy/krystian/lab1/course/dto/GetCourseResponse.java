package jandy.krystian.lab1.course.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCourseResponse {

    private String title;

    private String language;

    private Double rating;
}
