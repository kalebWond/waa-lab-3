package com.example.Lab1.services;

import com.example.Lab1.domain.Post;
import com.example.Lab1.domain.User;
import com.example.Lab1.domain.dto.PostDto;
import com.example.Lab1.domain.dto.UserDto;
import com.example.Lab1.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(post -> modelMapper.map(post, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void save(UserDto p) {
        userRepository.save(modelMapper.map(p, User.class));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(long id, UserDto u) {
        userRepository.findById(id).map(user -> {
            user.setName(u.getName());
            return userRepository.save(user);
        });
    }

    @Override
    public List<UserDto> findAllWithPostCountGreaterThan(int count) {
        return userRepository.getUsersWithMultiplePosts(count).stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllByPostTitle(String title) {
        return userRepository.getUsersByPostTitle(title).stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUserId(long id) {
        User user = userRepository.findById(id).get();
        return user.getPosts().stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

}
