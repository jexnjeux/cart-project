package com.example.cart.member.model.dto;

import com.example.cart.member.model.entity.Member;
import com.example.cart.member.type.Gender;
import com.example.cart.member.type.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotBlank(message = "아이디는 필수 입력 사항입니다")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "비밀번호는 영문, 숫자를 포함하여 8자리 이상 16자리 이하입니다")
    private String password;
    private Role role;
    @NotBlank(message = "이름은 필수 입력 사항입니다")
    private String name;
    @NotBlank(message = "휴대폰번호는 필수 입력 사항입니다")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호가 올바르지 않습니다(올바른 휴대폰번호: 010-0000-0000)")
    private String phone;
    @Past
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String postalCode;

  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {

    private Long id;
    private String username;
    private Role role;
    private LocalDateTime createdDate;

    public static MemberDto.Response of(Member member) {
      return MemberDto.Response.builder()
          .id(member.getId())
          .username(member.getUsername())
          .role(member.getRole())
          .createdDate(member.getCreatedDate())
          .build();
    }
  }


}
