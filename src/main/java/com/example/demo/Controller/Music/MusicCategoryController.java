package com.example.demo.Controller.Music;

import com.example.demo.DTO.Music.MusicCategoryDTO;
import com.example.demo.Service.Music.MusicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music-categories")
public class MusicCategoryController {

    @Autowired
    private MusicCategoryService musicCategoryService;

    @GetMapping
    public ResponseEntity<List<MusicCategoryDTO>> getAllCategories() {
        List<MusicCategoryDTO> categories = musicCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
