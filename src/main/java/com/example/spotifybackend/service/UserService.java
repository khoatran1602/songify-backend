package com.example.spotifybackend.service;

import com.example.spotifybackend.model.User;

public interface UserService {
    User getUser(String userId);
    void updateUser(User user);
    void deleteUser(String userId);
}
