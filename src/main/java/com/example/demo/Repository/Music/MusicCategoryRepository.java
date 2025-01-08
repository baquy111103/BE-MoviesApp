package com.example.demo.Repository.Music;

import com.example.demo.Model.Music.MusicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicCategoryRepository extends JpaRepository<MusicCategory, Long> {
}
