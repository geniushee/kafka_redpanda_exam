package com.example.redpanda_ex_24_06_13.global.notProd;

import com.example.redpanda_ex_24_06_13.member.member.service.MemberService;
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

    @Bean
    public ApplicationRunner initData(){
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if(memberService.count() > 0) return;

                memberService.join("user1", "1234", "user1");
                memberService.join("user2", "1234", "user2");
                memberService.join("user3", "1234", "user3");
            }
        };
    }
}
