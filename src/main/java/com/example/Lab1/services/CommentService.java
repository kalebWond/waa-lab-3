package com.example.Lab1.services;

import com.example.Lab1.domain.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findAllByPostId(long postId);

    Comment getById(long id);

    void save(Comment p);


}
