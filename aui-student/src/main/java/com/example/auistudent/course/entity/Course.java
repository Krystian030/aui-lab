package com.example.auistudent.course.entity;

import com.example.auistudent.student.entity.Student;
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
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
}


