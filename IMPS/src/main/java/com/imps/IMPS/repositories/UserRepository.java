package com.imps.IMPS.repositories;

import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}