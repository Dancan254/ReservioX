package com.ian.reserviox.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private  Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;

}
