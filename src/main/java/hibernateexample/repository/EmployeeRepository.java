package hibernateexample.repository;

import hibernateexample.enitities.Employee;

import java.util.Set;

public interface EmployeeRepository {
    Employee findById(Long id);

    Employee findBySSN(Employee employee);
    Set<Employee> findAll();

    Employee save(Employee employee);

    Employee update(Employee employee);

    void deleteById(Long id);

    void addTeamBuildingInfo(String info, long projId, long empId);
}
