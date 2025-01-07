package com.example.demo.Repository.Music;

import com.example.demo.Model.Music.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value = "SELECT *" +
            " FROM song_country c WHERE c.status = 1", nativeQuery = true)
    List<Country> findCountry();
}
