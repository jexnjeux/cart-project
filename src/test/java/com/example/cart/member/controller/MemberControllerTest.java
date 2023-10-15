package com.example.cart.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.cart.member.model.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/sql/member-controller-test-data.sql")
class MemberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void 정상적인_회원가입() throws Exception {
    //given
    MemberDto.Request request = MemberDto.Request.builder()
        .username("test2222")
        .password("test2222")
        .name("김애플")
        .phone("010-3333-3333")
        .build();

    //when
    //then
    mockMvc.perform(post("/join")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").isNumber())
        .andExpect(jsonPath("$.data.username").value("test2222"))
        .andExpect(jsonPath("$.data.role").value("ROLE_USER"));

  }

  @Test
  void 이미_가입된_아이디로_회원가입_에러() throws Exception {
    //given
    MemberDto.Request request = MemberDto.Request.builder()
        .username("test1234")
        .password("test1234")
        .name("김애플")
        .phone("010-3333-3333")
        .build();

    //when
    //then
    mockMvc.perform(post("/join").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict());
  }

}