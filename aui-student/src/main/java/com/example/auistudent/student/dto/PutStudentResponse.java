package com.example.auistudent.student.dto;

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
