package com.example.demo.Repository.Movie;

import com.example.demo.Model.Movie.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, String> {
    @Query("SELECT e, m.movie_name FROM Episode e JOIN e.movie m WHERE m.movie_code = :movie_code")
    List<Episode> findEpisodesByMovieCode(@Param("movie_code") String movie_code);
}
