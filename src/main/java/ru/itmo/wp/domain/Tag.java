package ru.itmo.wp.domain;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    public Tag() {

    }
    public Tag(String tagName) {
        this.name = tagName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

