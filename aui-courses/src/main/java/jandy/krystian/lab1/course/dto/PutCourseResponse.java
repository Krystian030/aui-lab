package jandy.krystian.lab1.course.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutCourseResponse {

    private String language;

    private Double rating;
}
