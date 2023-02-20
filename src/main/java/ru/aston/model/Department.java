package ru.aston.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Integer id;

    private String departmentName;

    private String departmentLocationName;
}
