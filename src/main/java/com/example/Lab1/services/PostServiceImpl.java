package com.example.Lab1.services;

import com.example.Lab1.domain.Post;
import com.example.Lab1.domain.dto.PostDto;
import com.example.Lab1.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getById(long id) {
        return modelMapper.map(postRepository.findById(id), PostDto.class);
    }

    @Override
    public void save(PostDto p) {
        postRepository.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void update(long id, PostDto p) {
        Post post = postRepository.findById(id).get();
        post.setAuthor(p.getAuthor());
        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        postRepository.save(post);
    }

    @Override
    public List<PostDto> findByAuthor(String author) {
        return postRepository.findPostsByAuthorIgnoreCase(author).stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        return postRepository.findPostsByTitleIgnoreCase(title).stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

}
