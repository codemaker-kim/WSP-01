package org.project.smumap.utils.config;

import org.project.smumap.domain.User;
import org.project.smumap.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username));
        //db에서 받아옴
        System.out.println("Loaded user: " + user.getUsername() + "  pw: " + user.getPassword());
        return user;
    }
}
