package com.imps.IMPS.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.PrintingRecord;

public interface PrintingRecordsRepository extends CrudRepository<PrintingRecord, Integer> {
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE STATUS = 'Pending'", nativeQuery = true)
	Iterable<PrintingRecord> findPending();
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE STATUS != 'Pending'", nativeQuery = true)
	Iterable<PrintingRecord> findCurrent();
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE userid = ?1", nativeQuery = true)
	Iterable<PrintingRecord> findByID(String userID);
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE requestid = ?1", nativeQuery = true)
	PrintingRecord findByRequestID(String requestID);
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE file_type = ?1", nativeQuery = true)
	ArrayList<PrintingRecord> findByFileType(String fileType);
}
