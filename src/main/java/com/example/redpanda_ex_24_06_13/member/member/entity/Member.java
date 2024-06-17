package com.example.redpanda_ex_24_06_13.member.member.entity;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeEntity {
    private String username;
    private String password;
    private String nickname;
    @Setter(AccessLevel.PRIVATE)
    private long postCount;

    public void increasePostCount(){
        postCount++;
    }
}
