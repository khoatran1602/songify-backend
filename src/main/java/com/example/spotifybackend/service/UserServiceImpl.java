package com.example.spotifybackend.service;

import com.example.spotifybackend.model.User;
import com.example.spotifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(User user) {
        if (!StringUtils.hasText(user.getDisplayName())) {
            user.setDisplayName("Anonymous");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
