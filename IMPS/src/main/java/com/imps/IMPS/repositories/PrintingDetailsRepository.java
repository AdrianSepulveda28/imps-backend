package com.imps.IMPS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.PrintingDetails;

public interface PrintingDetailsRepository extends CrudRepository<PrintingDetails, Integer> {

	@Query(value = "SELECT * FROM PRINTING_DETAILS WHERE requestid = ?1 AND file_name = ?2", nativeQuery = true)
	PrintingDetails findByID(String requestID, String fileName);
}
