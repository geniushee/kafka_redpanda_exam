package com.example.redpanda_ex_24_06_13.noti.noti.entity;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Noti extends TimeEntity {
    @ManyToOne
    private Member actor;
    @ManyToOne
    private Member receiver;
    private String relTypeCode; // 알림 종류
    private long relId; //알림 종류 아이디
    private String TypeCode;
    private String Type2Code;
    private boolean isRead;

    public String getUri(){
        if(TypeCode.equals("POST") && Type2Code.equals("CREATED")){
            return "/p/" + relId;
        }
        return "";
    }

    public String getMessage(){
        if(TypeCode.equals("POST") && Type2Code.equals("CREATED")){
            return actor.getNickname() + "님이 " + relId + "번 글을 작성했습니다.";
        }
        return "";
    }
}
