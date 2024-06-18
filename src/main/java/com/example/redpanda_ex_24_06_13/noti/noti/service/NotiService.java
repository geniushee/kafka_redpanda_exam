package com.example.redpanda_ex_24_06_13.noti.noti.service;

import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.member.member.service.MemberService;
import com.example.redpanda_ex_24_06_13.noti.noti.entity.Noti;
import com.example.redpanda_ex_24_06_13.noti.noti.repository.NotiRepository;
import com.example.redpanda_ex_24_06_13.post.post.entity.Post;
import com.example.redpanda_ex_24_06_13.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotiService {
    private final NotiRepository notiRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void postCreated(Post post) {
        Member actor = postService.of(post.getAuthor());
        List<Member> receivers = memberService.findAll()
                .stream().filter(member -> !member.equals(actor))
                .toList();

        receivers.forEach(receiver -> {
            Noti noti = Noti.builder()
                    .actor(actor)
                    .receiver(receiver)
                    .relTypeCode(post.getModuleName())
                    .relId(post.getId())
                    .TypeCode("POST")
                    .Type2Code("CREATED")
                    .readStatus(false)
                    .build();

            notiRepository.save(noti);
        });
    }
}
