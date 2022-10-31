package com.dailymap.dailymap.domain.member.service;

import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.repository.MemberRepository;
import com.dailymap.dailymap.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(Member member) throws BusinessException{
        memberRepository.save(member);
    }

    public boolean isRegistered(String email) {
        return findMemberByEmail(email).isPresent();
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
