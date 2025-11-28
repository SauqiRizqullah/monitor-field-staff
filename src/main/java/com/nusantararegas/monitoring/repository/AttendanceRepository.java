package com.nusantararegas.monitoring.repository;

import com.nusantararegas.monitoring.entity.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, String> {
}
