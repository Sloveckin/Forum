package ru.itmo.wp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {

    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post/{id}")
    @Guest
    private String postGet(@PathVariable("id") String id, Model model, HttpSession session) {
        try {
            session.setAttribute("currentPost", postService.findById(Long.parseLong(id)));
            model.addAttribute("comment", new Comment());
        } catch (NumberFormatException ignored) {
            ///Nothing
        }
        return "PostPage";
    }

    @GetMapping("/post/")
    private String emptyPostGet() {
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    private String post(@PathVariable("id") String id, @Valid @ModelAttribute("comment") Comment comment,
                        BindingResult bindingResult,
                        HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        Post post = (Post) session.getAttribute("currentPost");
        postService.writeComment(post, comment, getUser(session));
        return "PostPage";
    }


}
