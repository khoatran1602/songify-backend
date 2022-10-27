package com.example.spotifybackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Table(name = "music")
public class Music implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private long id;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "cover_art", nullable = false)
    @JsonProperty("coverArt")
    private String coverArt;

    @Column(name = "subtitle", nullable = false)
    @JsonProperty("subtitle")
    private String subtitle;
    @Column(name = "user_id", nullable = false)
    @JsonProperty("userId")
    private String userId;

    public Music() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String songName) {
        this.title = songName;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoverart() {
        return coverArt;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
