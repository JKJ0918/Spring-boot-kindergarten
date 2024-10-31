package com.kinder.kindergarten.repository.Employee;

import com.kinder.kindergarten.entity.Certificate;
import com.kinder.kindergarten.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByEmployee(Employee employee);
}
