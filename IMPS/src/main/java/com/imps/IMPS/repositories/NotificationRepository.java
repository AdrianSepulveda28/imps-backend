package com.imps.IMPS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.Comment;
import com.imps.IMPS.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	
	@Query(value = "SELECT * FROM NOTIFICATION WHERE userid = ?1 ORDER BY id DESC", nativeQuery = true)
	Iterable<Notification> findByUserID(String userID);
}
