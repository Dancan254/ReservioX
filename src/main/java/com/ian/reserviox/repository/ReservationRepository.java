package com.ian.reserviox.repository;

import com.ian.reserviox.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByCompanyId(Long companyId);
}
