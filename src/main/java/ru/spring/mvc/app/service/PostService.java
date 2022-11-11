package ru.spring.mvc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.mvc.app.model.Post;
import ru.spring.mvc.app.repository.interfaces.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {

    private final PostRepository<Long, Post> postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        postRepository.add(post);
    }

    public Post getPostById(Long id) {
        return postRepository.getById(id);
    }

    public void updatePost(Long id, Post post) {
        postRepository.update(id, post);
    }

    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}
