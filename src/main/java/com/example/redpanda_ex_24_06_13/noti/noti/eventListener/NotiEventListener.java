package com.example.redpanda_ex_24_06_13.noti.noti.eventListener;

import com.example.redpanda_ex_24_06_13.global.event.PostCreatedEvent;
import com.example.redpanda_ex_24_06_13.noti.noti.service.NotiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class NotiEventListener {
    // 이벤트 리스너를 이용하여 post모듈과의 종속성을 제거
    // PostCreatedEvent를 채팅방이라고 생각한다면 listen이라는 메소드가 채팅방의 참가자이다.
    // PostCreatedEvent를 파라미터로 가지는 모든 메소스는 채팅방에 참가한다고 보면 된다.

    private final NotiService notiService;
    private final KafkaTemplate<Object, Object> template;

    @EventListener
    @Async
    public void listen(PostCreatedEvent event){
        notiService.postCreated(event.getPost());
    }

    @KafkaListener(topics = "chat-room-1", groupId = "1") // groupId는 파티션과 관계가 있으나 나중에 공부해봐라
    public void consume(String message){System.out.println("consume message : " + message);}

    // groupId가 동일하면 같은 컨슈머로 인지하여 여기는 메시지를 받지 못함
    // 메세지를 받고자 하면 그룹아이디(유사. 사용자 아이디)가 달라야 한다.
    @KafkaListener(topics = "chat-room-1", groupId = "2")
    public void consume2(String message){System.out.println("consume2 message : " + message);}
}
