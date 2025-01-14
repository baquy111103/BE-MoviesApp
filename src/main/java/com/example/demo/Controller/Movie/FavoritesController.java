package com.example.demo.Controller.Movie;

import com.example.demo.DTO.Movie.FavoriteDTO;
import com.example.demo.Service.Movie.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoriteService;

    // Endpoint để lấy danh sách yêu thích của người dùng
    @GetMapping
    public ResponseEntity<List<FavoriteDTO>> getAllFavorites(@RequestParam String email) {
        List<FavoriteDTO> favoriteDTOs = favoriteService.getAllFavoritesByEmail(email);
        return ResponseEntity.ok(favoriteDTOs);
    }

    // Endpoint để thêm hoặc cập nhật yêu thích cho người dùng
    @PostMapping("/add")
    public ResponseEntity<FavoriteDTO> addOrUpdateFavorite(@RequestParam String movie_code, @RequestParam String email) {
        try {
            FavoriteDTO favoriteDTO = favoriteService.addOrUpdateFavorite(movie_code, email);
            return ResponseEntity.ok(favoriteDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint để cập nhật trạng thái yêu thích thành không yêu thích
    @PutMapping("/update")
    public ResponseEntity<FavoriteDTO> updateFavoriteStatusToInactive(@RequestParam String movie_code, @RequestParam String email) {
        try {
            FavoriteDTO favoriteDTO = favoriteService.updateFavoriteStatusToInactive(movie_code, email);
            return ResponseEntity.ok(favoriteDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}