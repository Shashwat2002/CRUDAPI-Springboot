package com.example.EmployeesAPICRUD.Repository;

import com.example.EmployeesAPICRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmpcity(String emp_city);

    Optional<List<Employee>> findByEmpageGreaterThan(int emp_age);

}
