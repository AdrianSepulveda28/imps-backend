package com.imps.imps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imps.imps.Entity.RegistrationEntityEntity;
import com.imps.imps.Entity.UserRegistrationEntity;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationEntity, Integer> {
    UserRegistrationEntity findByUsername(String username);
}
