package com.example.redpanda_ex_24_06_13.post.author.repository;

import com.example.redpanda_ex_24_06_13.post.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
