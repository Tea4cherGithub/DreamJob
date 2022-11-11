package ru.spring.mvc.app.repository.interfaces;

import ru.spring.mvc.app.dto.Pair;

public interface UserRepository<K,V> extends Repository<K, V> {

    boolean existsUserByUsername(String username);

    Pair<Boolean, Long> existsUsersFullyAuth(String username, String password);
}
