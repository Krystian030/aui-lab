package jandy.krystian.lab1.dto.course;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetCourseResponse {

    private Long id;

    private String title;

    private String language;
}
