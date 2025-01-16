package com.example.demo.Service.Short;

import com.example.demo.Model.Short.VideoShort;
import com.example.demo.Repository.Short.VideoShortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoShortService {
    @Autowired
    private VideoShortRepository videoShortRepository;

    public VideoShort getVideoShortByCode(String videoShortCode) {
        return videoShortRepository.findByVideoShortCode(videoShortCode)
                .orElseThrow(() -> new RuntimeException("VideoShort with code " + videoShortCode + " not found"));
    }
}
