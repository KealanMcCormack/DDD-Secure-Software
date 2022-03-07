package com.example.repository;

import com.example.model.Book;
import com.example.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends
    JpaRepository<ForumPost, Long>{ }

