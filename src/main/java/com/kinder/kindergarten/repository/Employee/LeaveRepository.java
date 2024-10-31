package com.kinder.kindergarten.repository.Employee;

import com.kinder.kindergarten.entity.Employee;
import com.kinder.kindergarten.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployee(Employee employee);
}
