package com.example.demo.Controller.Short;

import com.example.demo.DTO.Short.FavoriteVideoShortDTO;
import com.example.demo.Service.Short.FavoriteVideoShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-video-short")
public class FavoriteVideoShortController {
    @Autowired
    private FavoriteVideoShortService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteVideoShortDTO>> getAllFavorites(@RequestParam String email) {
        List<FavoriteVideoShortDTO> favorites = favoriteService.getAllFavoritesByEmail(email);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/add")
    public ResponseEntity<FavoriteVideoShortDTO> addOrUpdateFavorite(@RequestParam String videoShortCode, @RequestParam String email) {
        FavoriteVideoShortDTO favorite = favoriteService.addOrUpdateFavorite(videoShortCode, email);
        return ResponseEntity.ok(favorite);
    }

    @PutMapping("/update")
    public ResponseEntity<FavoriteVideoShortDTO> updateFavoriteStatusToInactive(@RequestParam String videoShortCode, @RequestParam String email) {
        FavoriteVideoShortDTO favorite = favoriteService.updateFavoriteStatusToInactive(videoShortCode, email);
        return ResponseEntity.ok(favorite);
    }
}
