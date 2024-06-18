package com.example.redpanda_ex_24_06_13.post.post.entity;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import com.example.redpanda_ex_24_06_13.post.author.entity.Author;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeEntity {
    @ManyToOne
    private Author author;
    private String title;
    private String content;
}
