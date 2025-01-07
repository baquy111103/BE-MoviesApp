package com.example.demo.Repository.Music;

import com.example.demo.Model.Music.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    @Query("SELECT b FROM Banner b WHERE b.startDate <= :currentDate " +
            "AND b.endDate >= :currentDate AND b.status = true " +
            "ORDER BY b.position ASC")
    List<Banner> findActiveBannersByDate(@Param("currentDate") Date currentDate);
}