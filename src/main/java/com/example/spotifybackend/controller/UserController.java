package com.example.spotifybackend.controller;

import com.example.spotifybackend.HttpResponse.ResponseBody;
import com.example.spotifybackend.HttpResponse.ResponseError;
import com.example.spotifybackend.model.User;
import com.example.spotifybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseBody> getUser(@RequestParam String userId){
        User user = userService.getUser(userId);
        System.out.println(user);
        if (user == null){
            ResponseBody body = new ResponseBody(null, new ResponseError("User doesn't exist", 404));
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        ResponseBody body = new ResponseBody(user);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        this.userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody String userId){
        this.userService.deleteUser(userId);
    }
}

