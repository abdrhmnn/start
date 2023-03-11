package io.spring.start.services;

import java.util.List;

import io.spring.start.model.Employee;

public interface IEmployeeService {
  public List<Employee> get();

  public Employee get(Integer id);

  public Boolean save(Employee employee);

  public Boolean delete(Integer id);
}
