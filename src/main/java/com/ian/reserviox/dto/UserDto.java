package com.ian.reserviox.dto;

import com.ian.reserviox.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private UserRole userRole;
}
