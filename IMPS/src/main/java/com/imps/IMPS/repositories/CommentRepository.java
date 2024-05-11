package com.imps.IMPS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.Comment;
import com.imps.IMPS.models.PrintingRecord;

public interface CommentRepository extends CrudRepository<Comment,Integer>{

	@Query(value = "SELECT * FROM COMMENT WHERE requestid = ?1 ORDER BY id DESC", nativeQuery = true)
	Iterable<Comment> findByRequestID(String requestID);
}
