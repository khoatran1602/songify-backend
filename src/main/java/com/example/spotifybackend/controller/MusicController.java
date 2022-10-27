package com.example.spotifybackend.controller;

import com.example.spotifybackend.HttpResponse.ResponseBody;
import com.example.spotifybackend.HttpResponse.ResponseError;
import com.example.spotifybackend.model.Music;
import com.example.spotifybackend.service.MusicServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.List;

@RequestMapping("/api/v1/music")
@RestController
@CrossOrigin(origins = "*")
public class MusicController {
    private static final Logger LOG = LoggerFactory.getLogger(MusicController.class);
    @Autowired
    private MusicServiceImpl musicService;

    @PostMapping
    public String addSong(@RequestBody Music music) {
        LOG.info("Saving the new song to the redis.");
        musicService.save(music);
        return "Successfully added. Music with id = " + music.getId();
    }

    @GetMapping
    public List<Music> getSongs(@RequestParam(required = false) String userId) {
        if (!StringUtils.hasText(userId)){
            LOG.info("User ID is missing");
            return null;
        }
        return musicService.getMusicByUserId(userId);
    }

    @DeleteMapping
    public ResponseEntity<ResponseBody> deleteMusic(@RequestBody Music music, @RequestParam String userId) {
        if (!userId.equals(music.getUserId())) {
            System.out.println("3");
            ResponseError error = new ResponseError("Something wrong", 403);
            ResponseBody body = new ResponseBody(null, error);
            return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        }
        LOG.info("Deleting song with user's id = " + userId);
        musicService.delete(music.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
