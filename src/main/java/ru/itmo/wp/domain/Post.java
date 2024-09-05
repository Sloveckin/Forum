package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/** @noinspection unused*/
@Entity
@Table(indexes = @Index(columnList = "creationTime"))
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 60)
    private String title;

    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @OrderBy("name")
    private Set<Tag> tags;


    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    @Lob
    @NotBlank
    private String text;

    @CreationTimestamp
    private Date creationTime;

    public Post() {
    }

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    public void addComment(Comment comment) {
        if (commentList == null) {
            commentList = new ArrayList<>();
        }
        comment.setPost(this);
        commentList.add(comment);
    }

    public void addTag(Tag tag) {
        if (tags == null) {
            tags = new HashSet<>();
        }
        tags.add(tag);
    }

}
