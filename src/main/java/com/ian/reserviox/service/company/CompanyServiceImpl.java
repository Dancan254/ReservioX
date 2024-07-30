package com.ian.reserviox.service.company;

import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.dto.ReservationDTO;
import com.ian.reserviox.entity.Ad;
import com.ian.reserviox.entity.Reservation;
import com.ian.reserviox.entity.User;
import com.ian.reserviox.enums.ReservationStatus;
import com.ian.reserviox.repository.AdRepository;
import com.ian.reserviox.repository.ReservationRepository;
import com.ian.reserviox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final ReservationRepository reservationRepository;

    public CompanyServiceImpl(UserRepository userRepository, AdRepository adRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.reservationRepository = reservationRepository;
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

    public List<ReservationDTO> getAllAddBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::toReservationDTO).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, ReservationStatus status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setReservationStatus(status);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}
