package dao;

import model.Employee;

import java.util.Set;

public interface EmployeeDao {


    Employee findEmployeeById(int id);

    void deleteById(int id);

    void update(int id, Employee employee);

    void createEmployee(Employee employee);

    Set<Employee> findAll();
}
