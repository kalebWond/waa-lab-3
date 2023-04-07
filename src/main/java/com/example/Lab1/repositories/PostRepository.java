package com.example.Lab1.repositories;

import com.example.Lab1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByAuthorIgnoreCase(String author);

    List<Post> findPostsByTitleIgnoreCase(String title);

}
