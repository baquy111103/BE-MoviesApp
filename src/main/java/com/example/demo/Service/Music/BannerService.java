package com.example.demo.Service.Music;

import com.example.demo.Model.Music.Banner;
import com.example.demo.Repository.Music.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerService {
    @Autowired
    BannerRepository bannerRepository;

    public List<Banner> getActiveBannersByDate() {
        Date currentDate = new Date();
        return bannerRepository.findActiveBannersByDate(currentDate);
    }
}
