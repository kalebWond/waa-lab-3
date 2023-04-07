package com.example.Lab1.controllers;

import com.example.Lab1.domain.dto.PostDto;
import com.example.Lab1.domain.dto.UserDto;
import com.example.Lab1.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAll(@RequestParam(value = "title", required = false) String title) {
        return title == null ? userService.findAll() : userService.findAllByPostTitle(title);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserDto p) {
        userService.save(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable int id) {
        var user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    // Make a query that will retrieve all the users that have more than (n) posts by requestParam
    @GetMapping("/multiple-posters")
    public ResponseEntity<List<UserDto>> getUsersWithMultiplePosts(@RequestParam(value = "count", required = false) int count) {
        var users = userService.findAllWithPostCountGreaterThan(count != 0 ? count : 1);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable int id) {
        List<PostDto> posts = userService.getPostsByUserId(id);
        return ResponseEntity.ok(posts);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int userId, @RequestBody UserDto p) {
        userService.update(userId, p);
    }
}
