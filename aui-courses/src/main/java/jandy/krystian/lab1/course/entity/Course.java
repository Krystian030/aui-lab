package jandy.krystian.lab1.course.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "rating")
    private Double rating;
}


