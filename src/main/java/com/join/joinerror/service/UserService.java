package com.join.joinerror.service;

import com.join.joinerror.domain.User;
import com.join.joinerror.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 성공 메세지를 던져주는 String
    public String join(String userName, String password) {

        // userName 중복 check
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName + "는 이미 있습니다.");
                });

        // 저장 (save)
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

}
