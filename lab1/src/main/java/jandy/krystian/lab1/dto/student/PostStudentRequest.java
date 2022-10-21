package jandy.krystian.lab1.dto.student;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostStudentRequest {

    String name;

    String surname;

    int age;
}
