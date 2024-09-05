package ru.itmo.wp.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private long id;


    @NonNull
    @NotEmpty
    @NotBlank
    @Lob
    @Size(min = 3, max = 100)
    private String text;


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
