package com.example.demo.Repository.Movie;

import com.example.demo.Model.Movie.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface FavoritesRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.active = true AND f.email = :email")
    List<Favorite> findAllFavoritesByEmail(@Param("email") String email);

    @Query("SELECT f FROM Favorite f WHERE f.movie.movie_code = :movie_code AND f.email = :email")
    Optional<Favorite> findByMovieCodeAndEmail(@Param("movie_code") String movie_code, @Param("email") String email);
}
