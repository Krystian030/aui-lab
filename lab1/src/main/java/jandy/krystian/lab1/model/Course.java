package jandy.krystian.lab1.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Course {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private List<Student> students;
}
