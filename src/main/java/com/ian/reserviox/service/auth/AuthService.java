package com.ian.reserviox.service.auth;

import com.ian.reserviox.dto.AuthenticationRequest;
import com.ian.reserviox.dto.SignupRequestDto;
import com.ian.reserviox.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDto signupRequestDto);
    UserDto signupCompany(SignupRequestDto signupRequestDto);
    String authenticateUser(AuthenticationRequest authenticationRequest);
}
