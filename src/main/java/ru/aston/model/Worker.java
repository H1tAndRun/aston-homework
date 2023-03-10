package ru.aston.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String role;
}
