package com.example.redpanda_ex_24_06_13.post.post.entity;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class Author extends TimeEntity {
    @Column(name="nickname")
    private String writer; // 이렇게 새로운 entity를 생성하여 모듈간 종속성을 분리.
}
