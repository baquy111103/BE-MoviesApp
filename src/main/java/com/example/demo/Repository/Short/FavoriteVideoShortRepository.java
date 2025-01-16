package com.example.demo.Repository.Short;

import com.example.demo.Model.Short.FavoriteVideoShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteVideoShortRepository extends JpaRepository<FavoriteVideoShort, Long> {
    @Query("SELECT f FROM FavoriteVideoShort f WHERE f.active = true AND f.email = :email")
    List<FavoriteVideoShort> findAllFavoritesByEmail(@Param("email") String email);

    @Query("SELECT f FROM FavoriteVideoShort f WHERE f.videoShortCode = :videoShortCode AND f.email = :email")
    Optional<FavoriteVideoShort> findByVideoShortCodeAndEmail(@Param("videoShortCode") String videoShortCode, @Param("email") String email);
}
