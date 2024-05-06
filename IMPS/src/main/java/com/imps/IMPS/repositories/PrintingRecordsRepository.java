package com.imps.IMPS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.PrintingRecord;
import com.imps.IMPS.models.User;

public interface PrintingRecordsRepository extends CrudRepository<PrintingRecord, Integer> {
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE STATUS = 'Pending'", nativeQuery = true)
	Iterable<PrintingRecord> findPending();
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE STATUS != 'Pending'", nativeQuery = true)
	Iterable<PrintingRecord> findCurrent();
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE userid = ?1", nativeQuery = true)
	Iterable<PrintingRecord> findByID(String userID);
}
