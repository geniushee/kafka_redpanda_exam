package com.example.redpanda_ex_24_06_13.noti.noti.eventListener;

import com.example.redpanda_ex_24_06_13.global.event.PostCreatedEvent;
import com.example.redpanda_ex_24_06_13.noti.noti.service.NotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotiEventListener {
    // 이벤트 리스너를 이용하여 post모듈과의 종속성을 제거
    // PostCreatedEvent를 채팅방이라고 생각한다면 listen이라는 메소드가 채팅방의 참가자이다.
    // PostCreatedEvent를 파라미터로 가지는 모든 메소스는 채팅방에 참가한다고 보면 된다.

    private final NotiService notiService;
    @EventListener
    public void listen(PostCreatedEvent event){
        notiService.postCreated(event.getPost());
    }
}
