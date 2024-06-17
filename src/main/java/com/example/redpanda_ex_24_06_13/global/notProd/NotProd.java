package com.example.redpanda_ex_24_06_13.global.notProd;

import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.member.member.service.MemberService;
import com.example.redpanda_ex_24_06_13.post.post.entity.Author;
import com.example.redpanda_ex_24_06_13.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Profile("!prod")
@Slf4j
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;
    private final PostService postService;

    @Bean
    public ApplicationRunner initData(){
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if(memberService.count() > 0) return;

                Member memberUser1 = memberService.join("user1", "1234", "user1");
                Member memberUser2 = memberService.join("user2", "1234", "user2");
                Member memberUser3 = memberService.join("user3", "1234", "user3");

                if(postService.count() > 0) return ;
                Author author1 = postService.of(memberUser1);
                postService.creatPost(author1, "title1", "content1");
                Author author2 = postService.of(memberUser2);
                postService.creatPost(author2, "title1", "content1");
                Author author3 = postService.of(memberUser3);
                postService.creatPost(author3, "title1", "content1");

            }
        };
    }
}
