package hibernateexample.service;

import hibernateexample.enitities.Employee;
import hibernateexample.enitities.Project;
import hibernateexample.repository.EmployeeRepository;
import hibernateexample.repository.ProjectRepository;

public class EmployeeProjectService {
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    public EmployeeProjectService(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    //TODO Warning: Try to write a better code, this is just a quick example
    public void saveEmployeeProjects(Employee employee, Project project, String info){
        Employee emp = employeeRepository.findBySSN(employee);
        Project proj = projectRepository.findByName(project);
        if(emp==null) emp = employeeRepository.save(employee);
        if(proj==null) proj = projectRepository.save(project);

        emp.addProject(proj);
        proj.addEmployee(emp);

        employeeRepository.update(emp);
        employeeRepository.addTeamBuildingInfo(info, proj.getId(), emp.getId());
    }

    //TODO 
    public void addEmployee(Employee employee){
        employeeRepository.findBySSN(employee);
    }

}
