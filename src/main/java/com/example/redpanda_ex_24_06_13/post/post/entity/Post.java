package com.example.redpanda_ex_24_06_13.post.post.entity;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeEntity {
    private Member author;
    private String title;
    private String content;
}
