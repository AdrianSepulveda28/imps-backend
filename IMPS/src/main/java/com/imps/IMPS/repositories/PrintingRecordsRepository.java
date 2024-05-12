package com.imps.IMPS.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE file_type = 'Module' AND request_date > ?1", nativeQuery = true)
	ArrayList<PrintingRecord> getModules(String requestDate);
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE file_type = 'Office Form' AND request_date > ?1", nativeQuery = true)
	ArrayList<PrintingRecord> getOfficeForms(String requestDate);
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE file_type = 'Manual' AND request_date > ?1", nativeQuery = true)
	ArrayList<PrintingRecord> getManuals(String requestDate);
	
	@Query(value = "SELECT * FROM PRINTING_RECORD WHERE file_type = 'Exam' AND request_date > ?1", nativeQuery = true)
	ArrayList<PrintingRecord> getExams(String requestDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE PRINTING_RECORD r SET r.status = ?2 WHERE requestid = ?1", nativeQuery = true)
	int setNewStatus(String requestid, String status);
}
