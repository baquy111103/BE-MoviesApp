package com.example.demo.Service.Short;

import com.example.demo.DTO.Short.FavoriteVideoShortDTO;
import com.example.demo.Model.Short.FavoriteVideoShort;
import com.example.demo.Model.Short.VideoShort;
import com.example.demo.Repository.Short.FavoriteVideoShortRepository;
import com.example.demo.Repository.Short.VideoShortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteVideoShortService {
    @Autowired
    private FavoriteVideoShortRepository favoriteRepository;

    @Autowired
    private VideoShortRepository videoShortRepository;

    @Transactional
    public List<FavoriteVideoShortDTO> getAllFavoritesByEmail(String email) {
        List<FavoriteVideoShort> favorites = favoriteRepository.findAllFavoritesByEmail(email);
        return favorites.stream().map(FavoriteVideoShortDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public FavoriteVideoShortDTO addOrUpdateFavorite(String videoShortCode, String email) {
        Optional<FavoriteVideoShort> favoriteOpt = favoriteRepository.findByVideoShortCodeAndEmail(videoShortCode, email);
        FavoriteVideoShort favorite;

        if (favoriteOpt.isPresent()) {
            favorite = favoriteOpt.get();
            favorite.setActive(true);
            favorite.setFavoriteDay(new Date());
        } else {
            VideoShort videoShort = videoShortRepository.findByVideoShortCode(videoShortCode)
                    .orElseThrow(() -> new RuntimeException("VideoShort not found with code: " + videoShortCode));

            favorite = new FavoriteVideoShort();
            favorite.setVideoShort(videoShort);
            favorite.setVideoShortCode(videoShortCode);
            favorite.setEmail(email);
            favorite.setActive(true);
            favorite.setFavoriteDay(new Date());
        }

        favoriteRepository.save(favorite);
        return new FavoriteVideoShortDTO(favorite);
    }

    @Transactional
    public FavoriteVideoShortDTO updateFavoriteStatusToInactive(String videoShortCode, String email) {
        Optional<FavoriteVideoShort> favoriteOpt = favoriteRepository.findByVideoShortCodeAndEmail(videoShortCode, email);

        if (favoriteOpt.isPresent()) {
            FavoriteVideoShort favorite = favoriteOpt.get();
            favorite.setActive(false);
            favorite.setUnfavoriteDay(new Date());

            favoriteRepository.save(favorite);
            return new FavoriteVideoShortDTO(favorite);
        } else {
            throw new RuntimeException("Favorite not found for videoShortCode: " + videoShortCode);
        }
    }
}