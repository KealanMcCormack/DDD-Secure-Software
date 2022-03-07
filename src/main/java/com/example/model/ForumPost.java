package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
    @NotBlank
    private String authorName;
    @NotBlank
    @OneToMany(targetEntity = Comment.class)
    private List comments;
}
