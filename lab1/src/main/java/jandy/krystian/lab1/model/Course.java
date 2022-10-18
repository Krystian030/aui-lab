package jandy.krystian.lab1.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
