package com.ian.reserviox.service.client;

import com.ian.reserviox.dto.AdDetailsDTO;
import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<AdDto> getAllAds();
    Optional<AdDto> getAdById(Long id);
    List<AdDto> searchAdByName(String name);
    boolean bookService(ReservationDTO reservationDTO);
    AdDetailsDTO getAdDetailsByAdId(Long adId);
}
