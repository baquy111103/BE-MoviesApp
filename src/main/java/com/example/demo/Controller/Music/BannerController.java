package com.example.demo.Controller.Music;

import com.example.demo.Model.Movie.Mov_banner;
import com.example.demo.Model.Music.Banner;
import com.example.demo.Service.Music.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/banners/music")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/active")
    public ResponseEntity<List<Banner>> getActiveBanners() {
        List<Banner> banners = bannerService.getActiveBannersByDate();
        return ResponseEntity.ok(banners);
    }
}