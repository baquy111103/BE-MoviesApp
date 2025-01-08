package com.example.demo.Service.Music;

import com.example.demo.DTO.Music.MusicCategoryDTO;
import com.example.demo.Model.Music.MusicCategory;
import com.example.demo.Repository.Music.MusicCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicCategoryService {

    @Autowired
    private MusicCategoryRepository musicCategoryRepository;

    public List<MusicCategoryDTO> getAllCategories() {
        List<MusicCategory> categories = musicCategoryRepository.findAll();

        // Chuyển đổi từ MusicCategory sang MusicCategoryDTO
        return categories.stream().map(category ->
                new MusicCategoryDTO(
                        category.getCategoryCode(),
                        category.getAvatar(),
                        category.getBanner(),
                        category.getCateNameVn(),
                        category.getCateNameEn(),
                        category.getDescription(),
                        category.getStatus()
                )
        ).collect(Collectors.toList());
    }
    }
