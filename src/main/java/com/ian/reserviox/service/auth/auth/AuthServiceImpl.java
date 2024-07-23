package com.ian.reserviox.service.auth.auth;

import com.ian.reserviox.dto.AuthenticationRequest;
import com.ian.reserviox.dto.SignupRequestDto;
import com.ian.reserviox.dto.UserDto;
import com.ian.reserviox.entity.User;
import com.ian.reserviox.enums.UserRole;
import com.ian.reserviox.exception.InvalidCredentialsException;
import com.ian.reserviox.repository.UserRepository;
import com.ian.reserviox.service.auth.jwt.UserDetailsServiceImpl;
import com.ian.reserviox.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto signupClient(SignupRequestDto signupRequestDto) {
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setFirstname(signupRequestDto.getFirstname());
        user.setLastname(signupRequestDto.getLastname());
        user.setPhone(signupRequestDto.getPhone());
        user.setUserRole(UserRole.CLIENT);
        userRepository.save(user);
        return user.convertUserToUserDto();
    }

    @Override
    public UserDto signupCompany(SignupRequestDto signupRequestDto) {
        User user = new User();
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setFirstname(signupRequestDto.getFirstname());
        user.setPhone(signupRequestDto.getPhone());
        user.setUserRole(UserRole.COMPANY);
        userRepository.save(user);
        return user.convertUserToUserDto();
    }

    public Boolean isEmailAlreadyInUse(String email){
        return userRepository.findUserByEmail(email) != null;
    }

    public String authenticateUser(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Incorrect email or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
