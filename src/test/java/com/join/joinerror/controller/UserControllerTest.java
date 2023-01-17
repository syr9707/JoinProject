package com.join.joinerror.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.join.joinerror.domain.dto.UserJoinRequest;
import com.join.joinerror.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    // Java Object를 Json으로 만들어주는
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {

        String userName = "Kim";
        String password = "1qwefa32";

        // HTTP Request에다가 어떤 값을 보낼 때는 byte로 보냄
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void join_fail() throws Exception {
        String userName = "Kim";
        String password = "1qwefa32";

        // HTTP Request에다가 어떤 값을 보낼 때는 byte로 보냄
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }


}