package com.energywise.imps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energywise.imps.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
