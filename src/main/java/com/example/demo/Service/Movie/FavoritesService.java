package com.example.demo.Service.Movie;

import com.example.demo.DTO.Movie.FavoriteDTO;
import com.example.demo.Model.Movie.Favorite;
import com.example.demo.Model.Movie.Movie;
import com.example.demo.Repository.Movie.FavoritesRepository;
import com.example.demo.Repository.Movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritesService {
    @Autowired
    private FavoritesRepository favoriteRepository;

    @Autowired
    public MovieRepository movieRepository;

    @Transactional
    public List<FavoriteDTO> getAllFavoritesByEmail(String email) {
        List<Favorite> favorites = favoriteRepository.findAllFavoritesByEmail(email);
        return favorites.stream().map(favorite -> {
            FavoriteDTO dto = new FavoriteDTO();
            dto.setActive(favorite.getActive());
            dto.setFavorite_day(favorite.getFavorite_day().toString());
            dto.setUnfavorite_day(favorite.getUnfavorite_day() != null ? favorite.getUnfavorite_day().toString() : "Chưa có ngày hủy yêu thích");
            dto.setMovie_code(favorite.getMovie().getMovie_code());
            dto.setMovie_name(favorite.getMovie().getMovie_name());
            dto.setRelease_date(favorite.getMovie().getRelease_date());
            dto.setImage_url(favorite.getMovie().getImage_url());
            dto.setMovie_genre(favorite.getMovie().getMovie_genre());
            dto.setType(favorite.getMovie().getType());
            dto.setDuration(favorite.getMovie().getDuration());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public FavoriteDTO addOrUpdateFavorite(String movie_code, String email) {
        Optional<Favorite> favoriteOpt = favoriteRepository.findByMovieCodeAndEmail(movie_code, email);
        Favorite favorite;

        if (favoriteOpt.isPresent()) {
            favorite = favoriteOpt.get();
            favorite.setActive(true);
            favorite.setFavorite_day(new Date());
        } else {
            Movie movie = movieRepository.findByMovieCode(movie_code)
                    .orElseThrow(() -> new RuntimeException("Movie not found with movie_code: " + movie_code));

            favorite = new Favorite();
            favorite.setMovie(movie);
            favorite.setActive(true);
            favorite.setFavorite_day(new Date());
            favorite.setEmail(email); // Gán email người dùng
        }

        // Lưu vào database
        favoriteRepository.save(favorite);

        // Trả về DTO
        return new FavoriteDTO(favorite);
    }

    @Transactional
    public FavoriteDTO updateFavoriteStatusToInactive(String movie_code, String email) {
        Optional<Favorite> favoriteOpt = favoriteRepository.findByMovieCodeAndEmail(movie_code, email);

        if (favoriteOpt.isPresent()) {
            Favorite favorite = favoriteOpt.get();
            favorite.setActive(false);
            favorite.setUnfavorite_day(new Date());

            favoriteRepository.save(favorite);

            return new FavoriteDTO(favorite);
        } else {
            throw new RuntimeException("Movie not found in favorites with movie_code: " + movie_code);
        }
    }

}
