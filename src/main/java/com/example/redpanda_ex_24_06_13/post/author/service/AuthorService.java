package com.example.redpanda_ex_24_06_13.post.author.service;

import com.example.redpanda_ex_24_06_13.post.author.repository.AuthorRepository;
import com.example.redpanda_ex_24_06_13.post.author.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Optional<Author> findAuthorById(long id) {
        return authorRepository.findById(id);
    }
}
