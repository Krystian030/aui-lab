package com.example.auistudent.student.dto;

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
