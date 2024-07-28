package com.ian.reserviox.service.company;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.entity.Ad;
import com.ian.reserviox.entity.User;
import com.ian.reserviox.repository.AdRepository;
import com.ian.reserviox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public CompanyServiceImpl(UserRepository userRepository, AdRepository adRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    public Boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad = new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setPrice(adDto.getPrice());
            ad.setImage(adDto.getImage().getBytes());
            ad.setUser(optionalUser.get());
            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDto> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::toDto).collect(Collectors.toList());
    }

    public AdDto getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        return optionalAd.map(Ad::toDto).orElse(null);
    }

    public String updateAd(Long id, AdDto updateDto) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()){
            Ad ad = optionalAd.get();
            ad.setServiceName(updateDto.getServiceName());
            ad.setDescription(updateDto.getDescription());
            ad.setPrice(updateDto.getPrice());
            ad.setImage(updateDto.getImage().getBytes());
            adRepository.save(ad);
            return "Ad updated successfully";
        }
        return "Ad not found";
    }

    @Override
    public String deleteAd(Long id) {
        return adRepository.findById(id)
                .map(ad -> {
                    adRepository.delete(ad);
                    return "Ad deleted successfully";
                })
                .orElse("Ad not found");

    }
}
