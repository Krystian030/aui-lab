package jandy.krystian.lab1.dto.student;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudentsResponse {

    List<GetStudentResponse> students = new ArrayList<>();
}
