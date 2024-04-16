package com.imps.imps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imps.imps.Entity.RegistrationEntityEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntityEntity, Integer> {
    UserEntity findByUsername(String username);
}
