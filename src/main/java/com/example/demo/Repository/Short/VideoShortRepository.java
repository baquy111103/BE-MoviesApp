package com.example.demo.Repository.Short;

import com.example.demo.Model.Short.VideoShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoShortRepository extends JpaRepository<VideoShort, Long> {
    Optional<VideoShort> findByVideoShortCode(String videoShortCode);
}