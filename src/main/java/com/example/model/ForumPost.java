package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "forum")
public class ForumPost {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public ForumPost(String title, String content, String authorName) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
    }

    public ForumPost() {
    }

    @NotBlank
    private String authorName;
}
