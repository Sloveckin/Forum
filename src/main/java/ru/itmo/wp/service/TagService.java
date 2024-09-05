package ru.itmo.wp.service;

import org.springframework.stereotype.Controller;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

@Controller
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public void save(Post post, String name) {
        Tag tag = tagRepository.findByName(name);
        if (tag == null) {
            tag = new Tag(name);
            tagRepository.save(tag);
        }
        post.addTag(tag);
    }



}
