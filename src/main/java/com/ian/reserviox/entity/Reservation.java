package com.ian.reserviox.entity;

import com.ian.reserviox.dto.ReservationDTO;
import com.ian.reserviox.enums.ReservationStatus;
import com.ian.reserviox.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    private Long id;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Date bookDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    public ReservationDTO toReservationDTO(){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(this.id);
        reservationDTO.setBookDate(this.bookDate);
        reservationDTO.setReservationStatus(this.reservationStatus);
        reservationDTO.setReviewStatus(this.reviewStatus);
        reservationDTO.setClientId(this.user.getId());
        reservationDTO.setUserName(this.user.getFirstname());
        reservationDTO.setCompanyId(this.company.getId());
        reservationDTO.setAdId(this.ad.getId());
        reservationDTO.setServiceName(this.ad.getServiceName());
        return reservationDTO;
    }

}
