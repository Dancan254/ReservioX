package com.ian.reserviox.dto;

import com.ian.reserviox.enums.ReservationStatus;
import com.ian.reserviox.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;
    private Date bookDate;
    private String serviceName;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Long clientId;
    private String userName;
    private Long companyId;
    private Long adId;

}
