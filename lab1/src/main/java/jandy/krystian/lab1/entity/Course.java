package jandy.krystian.lab1.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="language")
    private String language;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY,
    cascade = { CascadeType.ALL})
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
}


