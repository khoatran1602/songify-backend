package com.example.spotifybackend.repository;

import com.example.spotifybackend.model.Music;

import java.util.List;
import java.util.Map;

public interface MusicRedisRepo {
    // Save a new employee.
    void save(Music music);

    void saveToCache(Music music);

    // Find employee by id.
    Music findById(long id);

    // Final all employees.
    Map<Long, Music> findAll();

    List<Music> getMusicByUserId(String userId);

    // Delete a employee.
    void delete(long id);
}
