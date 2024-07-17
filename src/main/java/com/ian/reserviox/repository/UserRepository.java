package com.ian.reserviox.repository;

import com.ian.reserviox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByEmail(String email);
}
