package ru.spring.mvc.app.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.spring.mvc.app.model.Post;
import ru.spring.mvc.app.repository.interfaces.PostRepository;
import ru.spring.mvc.app.util.DreamJobUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.spring.mvc.app.util.DreamJobUtils.generateId;

@Repository
@Slf4j
public class PostRepositoryDevImpl implements PostRepository<Long, Post> {

    private final Map<Long, Post> posts = new ConcurrentHashMap<>();

    @Override
    @PostConstruct
    public void init() {
        long firstPostId = generateId();
        long secondPostId = generateId();
        long thirdPostId = generateId();
        posts.put(firstPostId, new Post(
                firstPostId, "Junior Java Developer",
                "OAO Turkish Airlanes", "description", LocalDateTime.now()));
        posts.put(secondPostId, new Post(
                secondPostId, "Middle PHP Developer",
                "PAO Sberbank", "description", LocalDateTime.now()));
        posts.put(thirdPostId, new Post(
                thirdPostId, "Senior DevOps",
                "LDA", "description", LocalDateTime.now()));
        log.info("Инициализировано хранилище в памяти. Количество данных в хранилище на момент инициализации равно {}", posts.size());
    }

    @Override
    public Post add(Post post) {
        long id = generateId();
        post.setId(id);
        post.setCreated(LocalDateTime.now());
        return posts.putIfAbsent(id, post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Post getById(Long id) {
        return posts.get(id);
    }

    @Override
    public void update(Long id, Post post) {
        posts.computeIfPresent(id, (idSaved, postUpdated) -> {
            postUpdated.setId(id);
            postUpdated.setName(post.getName());
            postUpdated.setDescription(post.getDescription());
            postUpdated.setCompany(post.getCompany());
            postUpdated.setCreated(postUpdated.getCreated());
            return postUpdated;
        });
    }

    @Override
    public void delete(Long id) {
        posts.remove(id);
    }
}
