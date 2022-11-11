package ru.spring.mvc.app.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Candidate implements Serializable {

    private long id;

    private String name;

    private String salary;

    private String description;

    private WorkingMode workingMode;

    private byte[] photo;

    private LocalDateTime created;
}
