package ru.spring.mvc.app.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class Post implements Serializable {

    private long id;

    private String name;

    private String company;

    private String description;

    private LocalDateTime created;
}
