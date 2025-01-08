package com.example.demo.DTO.Music;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicCategoryDTO {
    private String categoryCode;
    private String avatar;
    private String banner;
    private String cateNameVn;
    private String cateNameEn;
    private String description;
    private Boolean status;
}
