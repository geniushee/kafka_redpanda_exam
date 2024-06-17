package com.example.redpanda_ex_24_06_13.member.member.service;

import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password, String nickname){
        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }

    public Long count(){
        return memberRepository.count();
    }

    @Transactional
    public void increasePostCount(long memberId) {
        Optional<Member> opMember = findById(memberId);
        opMember.ifPresent(Member::increasePostCount);
    }

    private Optional<Member> findById(long memberId) {
        return memberRepository.findById(memberId);
    }
}
