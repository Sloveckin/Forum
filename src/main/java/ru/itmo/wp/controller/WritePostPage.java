package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.form.validator.PostCredentialsValidator;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.TagService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class WritePostPage extends Page {
    private final UserService userService;
    private final TagService tagService;
    private final PostCredentialsValidator postCredentialsValidator;

    public WritePostPage(UserService userService, TagService tagService, PostCredentialsValidator postCredentialsValidator) {
        this.userService = userService;
        this.tagService = tagService;
        this.postCredentialsValidator = postCredentialsValidator;
    }

    @AnyRole({Role.Name.ADMIN, Role.Name.WRITER})
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("post", new PostCredentials());
        return "WritePostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/writePost")
    public String writePostPost(@Valid @ModelAttribute("post") PostCredentials postForm,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "WritePostPage";
        }

        Post post = new Post(postForm.getTitle(), postForm.getText());
        if (!postForm.getTags().isEmpty()) {
            List<String> tagsName = Arrays.asList(postForm.getTags().split("[\\s+]"));
            for (String name : tagsName) {
                if (name.isEmpty()) {
                    continue;
                }
                tagService.save(post, name);
            }
        }

        userService.writePost(getUser(httpSession), post);
        putMessage(httpSession, "You published new post");

        return "redirect:/myPosts";
    }


    @InitBinder("post")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(postCredentialsValidator);
    }

}
