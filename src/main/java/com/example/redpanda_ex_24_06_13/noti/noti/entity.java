package com.example.redpanda_ex_24_06_13.noti.noti;

import com.example.redpanda_ex_24_06_13.global.entity.TimeEntity;
import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class entity extends TimeEntity {
    @ManyToOne
    private Member actor;
    @ManyToOne
    private Member receiver;
    private String relTypeCode; // 알림 종류
    private long relId; //알림 종류 아이디
    private String TypeCode;
    private String Type2Code;
    private boolean isRead;

}
