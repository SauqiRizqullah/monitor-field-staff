package com.nusantararegas.monitoring.repository;

import com.nusantararegas.monitoring.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, String> {
}
