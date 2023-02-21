package ru.aston.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProjectWorker {
    private Integer id;

    private String projectName;

    private String workerName;

    private String workerLastName;

    private String workerRole;
}
