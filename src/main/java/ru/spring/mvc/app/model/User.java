package ru.spring.mvc.app.model;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    private long id;

    private String username;

    private String password;
}
