package hibernateexample.enitities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SSN",
                             name = "SSN_UK")})
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ssn", length = 15,
            nullable = false, unique = true)
    private String SSN;

    @Column(name = "first_name", length = 10, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private int age;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",
            foreignKey = @ForeignKey(name = "department_employee_fk"))
    private Department department;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.MERGE)
    private Set<Project> projects = new HashSet<>();

    public Employee(){}


    public Long getId() {
        return id;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Contract getContract() {
        return contract;
    }

    public Department getDepartment() {
        return department;
    }
}
