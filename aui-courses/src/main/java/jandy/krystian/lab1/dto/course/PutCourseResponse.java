package jandy.krystian.lab1.dto.course;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutCourseResponse {

    private String title;

    private String language;
}
