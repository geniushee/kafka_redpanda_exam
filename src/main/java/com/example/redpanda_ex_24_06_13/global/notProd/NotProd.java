package com.example.redpanda_ex_24_06_13.global.notProd;

import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.member.member.service.MemberService;
import com.example.redpanda_ex_24_06_13.post.author.entity.Author;
import com.example.redpanda_ex_24_06_13.post.author.service.AuthorService;
import com.example.redpanda_ex_24_06_13.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Profile("!prod")
@Slf4j
@Configuration
@RequiredArgsConstructor
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;
    private final MemberService memberService;
    private final PostService postService;
    private final AuthorService authorService;

    @Bean
    @Order(3)
    public ApplicationRunner initData(){
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if(memberService.count() > 0) return;

                self.work1();
                self.work2();
                self.work3();
            }
        };
    }

    @Transactional
    public void work1(){
        Member memberUser1 = memberService.join("user1", "1234", "user1");
        Member memberUser2 = memberService.join("user2", "1234", "user2");
        Member memberUser3 = memberService.join("user3", "1234", "user3");
    }

    @Transactional
    public void work2(){
        if(postService.count() > 0) return ;
        Author author1 = authorService.findAuthorById(1L).get();
        postService.creatPost(author1, "title1", "content1");
        Author author2 = authorService.findAuthorById(2L).get();
        postService.creatPost(author2, "title2", "content2");

    }

    @Transactional
    public void work3(){
        Author author3 = authorService.findAuthorById(3L).get();
        postService.creatPost(author3, "title3", "content3");
    }
}
