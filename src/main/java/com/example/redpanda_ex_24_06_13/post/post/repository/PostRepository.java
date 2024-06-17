package com.example.redpanda_ex_24_06_13.post.post.repository;

import com.example.redpanda_ex_24_06_13.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
