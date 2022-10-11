package com.dailymap.dailymap.domain.member;

import com.dailymap.dailymap.DailymapApplicationTests;
import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class MemberRepositoryTests extends DailymapApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Order(1)
    @DisplayName("데이터베이스에 사용자정보 정상적으로 저장 성공 검증")
    public void createMember() {
        // given
        Member member = Member.builder()
            .email("test01@kakao.com")
            .username("TestUser01")
            .refreshToken("refreshToken Test Value")
            .tokenExpirationTime(LocalDateTime.now().plusDays(14))
            .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertEquals(1L, savedMember.getId());
    }

    @Test
    @Order(2)
    @DisplayName("사용자 조회 성공 검증")
    public void findMemberById() throws Exception {
        // given

        // when
        Member findMember = memberRepository.findById(1L)
            .orElseThrow(Exception::new);

        // then
        Assertions.assertEquals("test01@kakao.com", findMember.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("사용자 정보수정 성공 검증")
    public void updateMember() throws Exception {
        // given
        Member member = memberRepository.findById(1L)
            .orElseThrow(Exception::new);

        member.updateUsername("User01");

        // when
        memberRepository.save(member);

        // then
        Optional<Member> findMember = memberRepository.findByUsername("User01");
        Assertions.assertTrue(findMember.isPresent());
    }

    @Test
    @Order(4)
    @DisplayName("사용자 삭제 성공 검증")
    public void deleteMember() throws Exception {
        // given
        Member member = memberRepository.findById(1L)
            .orElseThrow(Exception::new);

        // when
        memberRepository.delete(member);

        // then
        Optional<Member> findMember = memberRepository.findById(1L);
        Assertions.assertFalse(findMember.isPresent());
    }

}
