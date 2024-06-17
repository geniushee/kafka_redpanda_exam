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
    // 중복 엔티티 클래스 역할에 따른 클래스 정의
    // 같은 member테이블을 맵핑하여 하나의 테이블로 2개의 엔티티 사용

    @Column(name="nickname")
    private String writer; // 이렇게 새로운 entity를 생성하여 모듈간 종속성을 분리.
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "Bigint default 0") //bigint 타입 기본값 0으로 설정 객체 생성시 not null 오류 해결
    private long postCount;

    public void increasePostCount(){
        postCount++;
    }
}
