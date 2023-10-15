package com.example.cart.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.cart.common.exception.member.AlreadyJoinedUsernameException;
import com.example.cart.common.exception.member.MissingRequestException;
import com.example.cart.member.model.dto.MemberDto;
import com.example.cart.member.model.dto.MemberDto.Response;
import com.example.cart.member.repository.MemberRepository;
import com.example.cart.member.type.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@SpringBootTest
@TestPropertySource("classpath:application.yml")
@Sql("/sql/member-repository-test-data.sql")
class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;

  @Test
  void 회원_가입() {
    //given
    MemberDto.Request request = MemberDto.Request.builder().username("test2222")
        .password("test2222").role(
            Role.ROLE_USER).name("스프링").phone("010-3333-2222").build();

    BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "object");

    //when
    Response response = memberService.join(request, bindingResult);

    //then
    assertThat(response.getUsername()).isEqualTo("test2222");
  }

  @Test
  void password_가_숫자_영문_조합이_아닌_경우_에러() {
    //given
    MemberDto.Request request = MemberDto.Request.builder().username("test2222")
        .password("test").role(
            Role.ROLE_USER).name("스프링").phone("010-3333-2222").build();

    BindingResult bindingResult = Mockito.mock(BindingResult.class);
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getFieldError()).thenReturn(
        new FieldError("MemberDto.request", "password", "비밀번호는 영문, 숫자를 포함하여 8자리 이상 16자리 이하입니다"));

    //when
    //then
    assertThatThrownBy(() -> {
      memberService.join(request, bindingResult);
    }).isInstanceOf(MissingRequestException.class)
        .hasMessageContaining("비밀번호는 영문, 숫자를 포함하여 8자리 이상 16자리 이하입니다");

  }

  @Test
  void 휴대폰_번호_양식에_맞지_않으면_에러() {
    //given
    MemberDto.Request request = MemberDto.Request.builder().username("test2222")
        .password("test1234").role(
            Role.ROLE_USER).name("스프링").phone("0103333").build();

    BindingResult bindingResult = Mockito.mock(BindingResult.class);
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getFieldError()).thenReturn(
        new FieldError("MemberDto.request", "phone",
            "휴대폰 번호가 올바르지 않습니다(올바른 휴대폰번호: 010-0000-0000)"));

    //when
    //then

    assertThatThrownBy(() -> {
      memberService.join(request, bindingResult);
    }).isInstanceOf(MissingRequestException.class)
        .hasMessageContaining("휴대폰 번호가 올바르지 않습니다(올바른 휴대폰번호: 010-0000-0000)");

  }

  @Test
  void 이미_가입된_아이디로_가입시도하면_에러() {
    //given
    MemberDto.Request request = MemberDto.Request.builder().username("test1234")
        .password("test2222").role(
            Role.ROLE_USER).name("스프링").phone("010-3333-2222").build();

    BindingResult bindingResult = Mockito.mock(BindingResult.class);


    //when
    //then
    assertThatThrownBy(() -> {
      memberService.join(request, bindingResult);
    }).isInstanceOf(AlreadyJoinedUsernameException.class);
  }

}