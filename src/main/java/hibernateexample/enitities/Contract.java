package hibernateexample.enitities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(nullable = false)
    private int salary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",
            foreignKey = @ForeignKey(name = "contract_employee_fk"))
    private Employee employee;

    public Contract(){}

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getSalary() {
        return salary;
    }

    public Employee getEmployee() {
        return employee;
    }
}
