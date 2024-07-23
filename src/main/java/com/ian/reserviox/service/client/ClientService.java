package com.ian.reserviox.service.client;

import com.ian.reserviox.dto.AdDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<AdDto> getAllAds();
    Optional<AdDto> getAdById(Long id);
    List<AdDto> searchAdByName(String name);
}
