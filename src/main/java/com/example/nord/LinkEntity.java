package com.example.nord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "LINK")
@Data
public class LinkEntity {
    @Id
    UUID uuid = UUID.randomUUID();

    String url;

    LocalDateTime date = LocalDateTime.now();

    int time = -1;

    int views = 0;

    public LinkEntity(String url){
        this.url = url;
    }
    public LinkEntity(){
    }

    public void incrementViews(){
        this.views++;
    }
}
