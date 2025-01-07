package com.example.demo.Service.Music;

import com.example.demo.Model.Music.Country;
import com.example.demo.Repository.Music.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findCountry();
    }
}
