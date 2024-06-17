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
    private final NotiService notiService;
    @EventListener
    public void listen(PostCreatedEvent event){
        notiService.postCreated(event.getPost());
    }
}
