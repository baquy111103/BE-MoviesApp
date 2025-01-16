package com.example.demo.Controller.Short;

import com.example.demo.Model.Short.VideoShort;
import com.example.demo.Service.Short.VideoShortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video_shorts")
public class VideoShortController {
    @Autowired
    private VideoShortService videoShortService;

    @GetMapping("/{videoShortCode}")
    public VideoShort getVideoShortByCode(@PathVariable String videoShortCode) {
        return videoShortService.getVideoShortByCode(videoShortCode);
    }
}
