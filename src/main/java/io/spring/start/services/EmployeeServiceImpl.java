package io.spring.start.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.spring.start.model.Employee;
import io.spring.start.repositories.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
  private IEmployeeRepository employeeRepository;

  public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> get() {
    return employeeRepository.getAllUser();
  }

  @Override
  public Employee get(Integer id) {
    return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("data tidak ditemukan!"));
  }

  @Override
  public Boolean save(Employee employee) {
    employeeRepository.save(employee);
    return employeeRepository.findById(employee.getId()).isPresent();
  }

  @Override
  public Boolean delete(Integer id) {
    employeeRepository.deleteById(id);
    return employeeRepository.findById(id).isPresent();
  }

}
