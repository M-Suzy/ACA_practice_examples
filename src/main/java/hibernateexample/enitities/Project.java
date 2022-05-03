package hibernateexample.enitities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name", length = 20, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable( name = "employee_project",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "emp_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "proj_id"))
    private Set<Employee> employees = new HashSet<>();

    public Project() {
    }

    public Project(String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", employees=").append(employees);
        sb.append('}');
        return sb.toString();
    }
}
