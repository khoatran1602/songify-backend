package com.example.spotifybackend.repository;

import com.example.spotifybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface UserRepository extends JpaRepository<User, String> {
}
