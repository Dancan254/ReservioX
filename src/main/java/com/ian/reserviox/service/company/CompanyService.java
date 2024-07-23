package com.ian.reserviox.service.company;

import com.ian.reserviox.dto.AdDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    Boolean postAd(Long userId, AdDto adDto) throws IOException;
    List<AdDto> getAllAds(Long userId);
    AdDto getAdById(Long adId);
    String updateAd(Long id, AdDto updateDto) throws IOException;
    String deleteAd(Long id);
}
