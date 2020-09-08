package com.linlinyang.restservices.springbootbuildingblocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linlinyang.restservices.springbootbuildingblocks.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
