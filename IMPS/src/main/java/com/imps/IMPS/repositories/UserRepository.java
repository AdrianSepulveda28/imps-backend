package com.imps.IMPS.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.imps.IMPS.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query(value = "SELECT * FROM USER WHERE NAME = ?1 AND PASSWORD = ?2", nativeQuery = true)
	User findByUsernameAndPassword(String username, String password);
	
	@Query(value = "SELECT * FROM USER WHERE NAME = ?1 AND PASSWORD = ?2 AND is_admin = 1", nativeQuery = true)
	User checkAdmin(String username, String password);
	
	@Query(value = "SELECT * FROM USER WHERE EMAIL = ?1", nativeQuery = true)
	User findByEmail(String email);
	
	@Query(value = "SELECT * FROM USER WHERE NAME = ?1", nativeQuery = true)
	User findByName(String name);
	
	@Query(value = "SELECT * FROM USER", nativeQuery = true)
	ArrayList<User> getAll();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USER u SET u.Token = ?2 WHERE EMAIL = ?1", nativeQuery = true)
	int setNewToken(String email, String token);
	
	@Query(value = "SELECT * FROM USER WHERE EMAIL = ?1 and Token = ?2", nativeQuery = true)
	User findByEmailAndToken(String email, String token);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USER u SET u.password = ?1 WHERE EMAIL = ?2 and Token = ?3", nativeQuery = true)
	int setNewPassword(String password, String email, String token);
	
}