package com.ian.reserviox.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdDto {
    private Long id;
    @NotNull
    private String serviceName;

    @NotNull
    private String description;

    @NotNull
    private Double price;
    private MultipartFile image;
    private byte[] returnedImg;
    private Long userId;
    private String companyName;
}
