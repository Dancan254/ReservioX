package com.ian.reserviox.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {
    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private MultipartFile image;
    private byte[] returnedImg;
    private Long userId;
    private String companyName;
}
