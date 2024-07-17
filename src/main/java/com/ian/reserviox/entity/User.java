package com.ian.reserviox.entity;

import com.ian.reserviox.dto.UserDto;
import com.ian.reserviox.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private UserRole userRole;

    public UserDto convertUserToUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setEmail(this.email);
        userDto.setPassword(this.password);
        userDto.setFirstname(this.firstname);
        userDto.setLastname(this.lastname);
        userDto.setPhone(this.phone);
        userDto.setUserRole(this.userRole);
        return userDto;
    }
}
