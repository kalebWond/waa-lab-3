package com.example.Lab1.services;

import com.example.Lab1.domain.dto.PostDto;
import com.example.Lab1.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();

    UserDto getById(long id);

    void save(UserDto p);

    void delete(long id);

    void update(long id, UserDto p);

    List<UserDto> findAllWithPostCountGreaterThan(int count);
    List<UserDto> findAllByPostTitle(String title);

    List<PostDto> getPostsByUserId(long id);

}
