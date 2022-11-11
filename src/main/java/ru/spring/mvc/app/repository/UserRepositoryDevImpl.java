package ru.spring.mvc.app.repository;

import org.springframework.stereotype.Repository;
import ru.spring.mvc.app.dto.Pair;
import ru.spring.mvc.app.model.User;
import ru.spring.mvc.app.repository.interfaces.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.spring.mvc.app.repository.interfaces.Repository.isUnsupported;
import static ru.spring.mvc.app.util.DreamJobUtils.generateId;

@Repository
public class UserRepositoryDevImpl implements UserRepository<Long, User> {

    private final Map<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public void init() {
        users.put(1L, new User(1L, "nikita228", "helllloooo"));
    }

    @Override
    public User add(User user) {
        long id = generateId();
        user.setId(id);
        return users.putIfAbsent(id, user);
    }

    @Override
    public List<User> findAll() {
        isUnsupported();
        return null;
    }

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public void update(Long aLong, User user) {
        isUnsupported();
    }

    @Override
    public void delete(Long aLong) {
        isUnsupported();
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return users.values()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public Pair<Boolean, Long> existsUsersFullyAuth(String username, String password) {
        return users.values()
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .map(user -> new Pair<>(Boolean.TRUE, user.getId()))
                .findFirst()
                .orElseGet(() -> new Pair<>(Boolean.FALSE, -1L));
    }
}
