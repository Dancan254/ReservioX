package com.ian.reserviox.service.client;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.entity.Ad;
import com.ian.reserviox.repository.AdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClientServiceImpl implements ClientService{
    private final AdRepository adRepository;

    public ClientServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream().map(Ad::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AdDto> getAdById(Long id) {
        return adRepository.findById(id)
                .map(Ad::toDto);
    }

    @Override
    public List<AdDto> searchAdByName(String name) {
        return adRepository.findAllByServiceNameContaining(name)
                .stream().map(Ad::toDto).collect(Collectors.toList());
    }

}
