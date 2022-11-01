package com.dailymap.dailymap.domain.member;

import com.dailymap.dailymap.domain.member.model.Member;
import com.dailymap.dailymap.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Transactional
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
		Assertions.assertEquals("test01@kakao.com", savedMember.getEmail());
	}

	@Test
	@Transactional
	@DisplayName("사용자 조회 성공 검증")
	public void findMemberById() throws Exception {
		// given
		Member member = Member.builder()
			.email("test02@kakao.com")
			.username("TestUser02")
			.refreshToken("refreshToken Test Value")
			.tokenExpirationTime(LocalDateTime.now().plusDays(14))
			.build();

		memberRepository.save(member);

		// when
		Member findMember = memberRepository.findByUsername("TestUser02")
			.orElseThrow(Exception::new);

		// then
		Assertions.assertEquals("test02@kakao.com", findMember.getEmail());
	}

	@Test
	@Transactional
	@DisplayName("사용자 정보수정 성공 검증")
	public void updateMember() throws Exception {
		// given
		Member member = Member.builder()
			.email("test03@kakao.com")
			.username("TestUser03")
			.refreshToken("refreshToken Test Value")
			.tokenExpirationTime(LocalDateTime.now().plusDays(14))
			.build();

		memberRepository.save(member);

		Member findMember = memberRepository.findByUsername("TestUser03")
			.orElseThrow(Exception::new);

		findMember.updateUsername("User03");

		// when
		memberRepository.save(findMember);

		// then
		Optional<Member> updateMember = memberRepository.findByUsername("User03");
		Assertions.assertTrue(updateMember.isPresent());
	}

	@Test
	@Transactional
	@DisplayName("사용자 삭제 성공 검증")
	public void deleteMember() throws Exception {
		// given
		Member member = Member.builder()
			.email("test04@kakao.com")
			.username("TestUser04")
			.refreshToken("refreshToken Test Value")
			.tokenExpirationTime(LocalDateTime.now().plusDays(14))
			.build();

		memberRepository.save(member);

		Member findMember = memberRepository.findByUsername("TestUser04")
			.orElseThrow(Exception::new);

		// when
		memberRepository.delete(findMember);

		// then
		Optional<Member> deletedMember = memberRepository.findByUsername("TestUser04");
		Assertions.assertFalse(deletedMember.isPresent());
	}

}
