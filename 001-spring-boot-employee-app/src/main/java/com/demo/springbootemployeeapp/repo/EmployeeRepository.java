package com.demo.springbootemployeeapp.repo;

import com.demo.springbootemployeeapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //method to sort by last name:
    public List<Employee> findAllByOrderByLastName();
}
