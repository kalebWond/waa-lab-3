package com.example.Lab1.domain.dto;

import com.example.Lab1.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    long id;
    String title;
    String content;
    String author;
    List<Comment> comments;
}