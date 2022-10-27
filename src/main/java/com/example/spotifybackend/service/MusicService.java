package com.example.spotifybackend.service;

import com.example.spotifybackend.model.Music;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MusicService {
    void save(Music music);

    void saveToCache(Music music);
    List<Music> getMusicByUserId(String userId);
    void deleteMusic(long id);
}
