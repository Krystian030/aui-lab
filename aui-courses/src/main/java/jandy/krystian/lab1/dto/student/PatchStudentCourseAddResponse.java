package jandy.krystian.lab1.dto.student;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PatchStudentCourseAddResponse {

    private Long courseId;
}
