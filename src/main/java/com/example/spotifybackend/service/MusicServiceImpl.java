package com.example.spotifybackend.service;

import com.example.spotifybackend.model.Music;
import com.example.spotifybackend.repository.MusicDBRepository;
import com.example.spotifybackend.repository.MusicRedisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class MusicServiceImpl implements MusicRedisRepo {
    private final String MUSIC_CACHE = "MUSIC";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private final MusicDBRepository musicDBRepository;
    private HashOperations<String, Long, Music> hashOperations;

    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Autowired
    public MusicServiceImpl(MusicDBRepository musicDBRepository) {
        this.musicDBRepository = musicDBRepository;
    }

    @Override
    public void save(Music music) {
        musicDBRepository.save(music);
        saveToCache(music);
    }
    @Override
    public void saveToCache(final Music music) {
        hashOperations.put(MUSIC_CACHE, music.getId(), music);
    }

    @Override
    public Music findById(long id) {
        Music song = hashOperations.get(MUSIC_CACHE, id);
        if (song != null) return song;
        else {
            Music music = this.getMusic(id);
            saveToCache(music);
            return music;
        }
    }

    private Music getMusic(Long id) {
        Music music = null;
        try {
            music = this.musicDBRepository.findById(id)
                    .orElseThrow(() -> new Exception("Employee is not found for this id:: " + id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return music;
    }

    @Override
    public Map<Long, Music> findAll() {
        return hashOperations.entries(MUSIC_CACHE);
    }

    @Override
    public List<Music> getMusicByUserId(String userId) {
        return musicDBRepository.getMusicByUserId(userId);
    }

    @Override
    public void delete(long musicId) {
        hashOperations.delete(MUSIC_CACHE, musicId);
        musicDBRepository.deleteById(musicId);
    }
}
