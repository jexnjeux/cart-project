package com.example.cart.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.cart.member.model.entity.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application.yml")
@Sql("/sql/member-repository-test-data.sql")
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  void username_으로_가입_여부_확인() {
    // given
    String username = "test1234";

    // when
    boolean exists = memberRepository.existsByUsername(username);

    // then
    assertThat(exists).isTrue();
  }

  @Test
  void username_으로_회원_정보_조회 () {
    //given
    String username = "test1234";

    //when
    Optional<Member> result = memberRepository.findByUsername(username);

    //then
    assertThat(result.get().getName()).isEqualTo("김코딩");
  }


}