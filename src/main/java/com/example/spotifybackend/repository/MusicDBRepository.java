package com.example.spotifybackend.repository;

import com.example.spotifybackend.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MusicDBRepository extends JpaRepository<Music, Long> {
    @Query(value = "SELECT * FROM music WHERE user_id = :userId", nativeQuery = true)
    List<Music> getMusicByUserId(@Param("userId") String userId);
}
