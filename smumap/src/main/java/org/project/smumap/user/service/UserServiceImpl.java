package org.project.smumap.user.service;

import lombok.RequiredArgsConstructor;
import org.project.smumap.domain.User;
import org.project.smumap.user.dto.UserInfoRequest;
import org.project.smumap.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long UserSave(UserInfoRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("already user exists");
        }

        return userRepository.save(user).getId();
    }

}
