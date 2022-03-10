package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="comments")
public class Comment {
    public Comment(String comment, String poster, ForumPost forumPost) {
        super();
        this.comment = comment;
        this.poster = poster;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotBlank
    private String comment;
    @NotBlank
    private String poster;
    @ManyToOne
    @JoinColumn(name="forumPost_id", nullable=false)
    private ForumPost forumPost;

    public Comment() {

    }
}
