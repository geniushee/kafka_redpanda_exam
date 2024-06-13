package com.example.redpanda_ex_24_06_13.member.member.repository;


import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
