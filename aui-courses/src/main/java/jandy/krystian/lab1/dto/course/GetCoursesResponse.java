package jandy.krystian.lab1.dto.course;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class GetCoursesResponse {

    List<GetCourseResponse> courses = new ArrayList<>();
}
