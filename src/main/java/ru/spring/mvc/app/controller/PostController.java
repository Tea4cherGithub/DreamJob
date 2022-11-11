package ru.spring.mvc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.mvc.app.model.Post;
import ru.spring.mvc.app.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostController {

    private final PostService postService;

    @GetMapping
    public ModelAndView getAllPosts() {
        return new ModelAndView("posts", Map.of("posts", postService.getAllPosts()));
    }

    @GetMapping("/addPost")
    public ModelAndView addPost() {
        return new ModelAndView("add-post", Map.of("post", new Post()));
    }

    @PostMapping("/addPost")
    public void addPostSubmit(@ModelAttribute("post") Post post,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        postService.addPost(post);
        response.sendRedirect(String.format("%s/posts", request.getContextPath()));
    }

    @GetMapping("/updatePost/{postId}")
    public ModelAndView updatePost(@PathVariable("postId") Long id) {
        var post = postService.getPostById(id);
        return new ModelAndView("update-post", Map.of("post", post));
    }

    @PostMapping("/updatePost/{postId}")
    public void updatePostSubmit(@PathVariable("postId") Long id,
                                 @ModelAttribute("post") Post post,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        postService.updatePost(id, post);
        response.sendRedirect(String.format("%s/posts", request.getContextPath()));
    }

    @GetMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable("postId") Long id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        postService.deletePost(id);
        response.sendRedirect(String.format("%s/posts", request.getContextPath()));
    }
}
