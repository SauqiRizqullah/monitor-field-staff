package com.nusantararegas.monitoring.repository;

import com.nusantararegas.monitoring.entity.Attendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, String> {
    @Query("""
    SELECT a FROM Attendance a
    WHERE a.createdAt = (
        SELECT MAX(a2.createdAt)
        FROM Attendance a2
        WHERE a2.employee.employeeId = a.employee.employeeId
    )
    AND a.status.statusName = 'On Duty'
""")
    List<Attendance> findLatestOnDuty();
}
