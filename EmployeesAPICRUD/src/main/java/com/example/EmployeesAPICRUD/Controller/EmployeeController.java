package com.example.EmployeesAPICRUD.Controller;


import com.example.EmployeesAPICRUD.Repository.EmployeeRepository;
import com.example.EmployeesAPICRUD.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public String createNewEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "Employee Created in database";
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getALLEmployees() {
        List<Employee> empList = new ArrayList<>();
        employeeRepository.findAll().forEach(empList::add);
        return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
    }

    @GetMapping("/employees/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
        Optional<Employee> emp = employeeRepository.findById(empid);
        if (emp.isPresent()) {
            return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    //@PutMapping("/employees/{empid}")
    // public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
    //    Optional<Employee> emp = employeeRepository.findById(empid);
    //   if (emp.isPresent()) {
    //       Employee existEmp = emp.get();
    //       existEmp.setEmp_age(employee.getEmp_age());
    //      existEmp.setEmp_city(employee.getEmpcity());
    //      existEmp.setEmp_name(employee.getEmpname());
    //    existEmp.setEmp_salary(employee.getEmpsalary());
    //      employeeRepository.save(existEmp);
    //     return "Employee Details against Id " + empid + " updated";
    //  } else {
    //      return "Employee Details does not exist for empid " + empid;
    //  }
    //  }


    @DeleteMapping("/employees/{empid}")
    public String deleteEmployeeByEmpId(@PathVariable Long empid) {
        employeeRepository.deleteById(empid);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/employees")
    public String deleteAllEmployee() {
        employeeRepository.deleteAll();
        return "Employee deleted Successfully..";
    }
    @GetMapping("/employees/empcity")
    public ResponseEntity<Employee> getEmployeeByempcity(@RequestParam("emp_city") String emp_city) {
        Employee emp = employeeRepository.findByEmpcity(emp_city);
        return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
    }

    @GetMapping("/employee/employeeGreaterThan")
    public ResponseEntity<List<Employee>> getEmployeeGreaterThan(@RequestParam("emp_age") int emp_age){
        Optional<List<Employee>> empList = employeeRepository.findByEmpageGreaterThan(emp_age);
        return new ResponseEntity<List<Employee>>(empList.get(), HttpStatus.FOUND);
    }
}

