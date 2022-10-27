package com.example.spotifybackend.engine;

import com.example.spotifybackend.model.User;
import com.example.spotifybackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));

        //save message to database
        User user = new User(message);

        this.userRepository.save(user);

        logger.info(String.format("#### -> ID message -> %s",user.getId()));

    }
}
