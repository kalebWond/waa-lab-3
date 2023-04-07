package com.example.Lab1.controllers;

import com.example.Lab1.domain.Comment;
import com.example.Lab1.domain.dto.PostDto;
import com.example.Lab1.services.CommentService;
import com.example.Lab1.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // get post by title or by author or simply get all
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDto> getAll(@RequestParam(value = "author", required = false) String author,
                                @RequestParam(value = "title", required = false) String title) {
        if(title != null) {
            return postService.findByTitle(title);
        }
        return author == null ? postService.findAll() : postService.findByAuthor(author);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto p) {
        postService.save(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable int id) {
        var post = postService.getById(id);
        return ResponseEntity.ok(post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int postId, @RequestBody PostDto p) {
        postService.update(postId, p);
    }

    // COMMENT SECTION

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{postId}/comments")
    public void addComment(@RequestBody Comment comment, @PathVariable String postId) {
        commentService.save(comment);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable int id) {
        var comments = commentService.findAllByPostId(id);
        return ResponseEntity.ok(comments);
    }

}
