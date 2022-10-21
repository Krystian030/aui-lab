package jandy.krystian.lab1.dto.student;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutStudentResponse {

    private String name;

    private String surname;

    int age;
}
