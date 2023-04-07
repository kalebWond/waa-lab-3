package com.example.Lab1.services;

import com.example.Lab1.domain.Comment;
import com.example.Lab1.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAllByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

}
