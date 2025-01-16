package com.example.demo.DTO.Short;

import com.example.demo.Model.Short.FavoriteVideoShort;
import lombok.Data;

import java.util.Date;

@Data
public class FavoriteVideoShortDTO {
    private String videoShortCode;
    private String videoShortName;
    private String description;
    private Date favoriteDay;
    private Date unfavoriteDay;
    private boolean active;

    public FavoriteVideoShortDTO(FavoriteVideoShort favorite) {
        this.videoShortCode = favorite.getVideoShortCode();
        this.videoShortName = favorite.getVideoShort().getVideoShortName();
        this.description = favorite.getVideoShort().getDescription();
        this.favoriteDay = favorite.getFavoriteDay();
        this.unfavoriteDay = favorite.getUnfavoriteDay();
        this.active = favorite.isActive();
    }
}