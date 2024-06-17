package com.example.redpanda_ex_24_06_13.global.event;

import com.example.redpanda_ex_24_06_13.post.post.entity.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PostCreatedEvent extends ApplicationEvent {
    private Post post;

    public PostCreatedEvent(Object source, Post post){
        super(source);
        this.post = post;
    }
}
