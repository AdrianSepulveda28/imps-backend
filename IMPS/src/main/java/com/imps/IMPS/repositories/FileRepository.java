package com.imps.IMPS.repositories;

import org.springframework.data.repository.CrudRepository;

import com.imps.IMPS.models.File;

public interface FileRepository extends CrudRepository<File, String> {

}
