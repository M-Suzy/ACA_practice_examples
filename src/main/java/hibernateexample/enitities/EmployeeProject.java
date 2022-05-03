package hibernateexample.enitities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "employee_project")
public class EmployeeProject {

    @EmbeddedId
    private ProjectEmployeeId id;

    //extra column
    @Column(length = 20)
    private String teamBuilding;


    public ProjectEmployeeId getId() {
        return id;
    }

    public void setId(ProjectEmployeeId id) {
        this.id = id;
    }

    public String getTeamBuilding() {
        return teamBuilding;
    }

    public void setTeamBuilding(String teamBuilding) {
        this.teamBuilding = teamBuilding;
    }

    @Embeddable
    public static class ProjectEmployeeId implements Serializable {

        @Column
        private Long proj_id;
        @Column
        private  Long emp_id;


        public ProjectEmployeeId() {
        }

        public ProjectEmployeeId(long projId, long empId) {
            this.emp_id = empId;
            this.proj_id = projId;
        }

        public void setProj_id(Long proj_id) {
            this.proj_id = proj_id;
        }

        public void setEmp_id(Long emp_id) {
            this.emp_id = emp_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProjectEmployeeId that = (ProjectEmployeeId) o;

            if (!Objects.equals(proj_id, that.proj_id)) return false;
            return Objects.equals(emp_id, that.emp_id);
        }

        @Override
        public int hashCode() {
            int result = proj_id != null ? proj_id.hashCode() : 0;
            result = 31 * result + (emp_id != null ? emp_id.hashCode() : 0);
            return result;
        }
    }
}
