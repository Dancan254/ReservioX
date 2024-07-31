package com.ian.reserviox.service.client;

import com.ian.reserviox.dto.AdDetailsDTO;
import com.ian.reserviox.dto.AdDto;
import com.ian.reserviox.dto.ReservationDTO;
import com.ian.reserviox.entity.Ad;
import com.ian.reserviox.entity.Reservation;
import com.ian.reserviox.entity.User;
import com.ian.reserviox.enums.ReservationStatus;
import com.ian.reserviox.enums.ReviewStatus;
import com.ian.reserviox.repository.AdRepository;
import com.ian.reserviox.repository.ReservationRepository;
import com.ian.reserviox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClientServiceImpl implements ClientService{
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    public ClientServiceImpl(AdRepository adRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
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

    public boolean bookService(ReservationDTO reservationDTO) {
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getClientId());
        if (optionalAd.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public AdDetailsDTO getAdDetailsByAdId(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsDTO adDetailsDTO = new AdDetailsDTO();
        optionalAd.ifPresent(ad -> adDetailsDTO.setAdDto(ad.toDto()));
        return adDetailsDTO;
    }

    @Override
    public List<ReservationDTO> getAllBookingByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId)
                .stream().map(Reservation::toReservationDTO).collect(Collectors.toList());
    }
}
