package hibernateexample.repository;


import hibernateexample.enitities.Project;

import java.util.Set;

public interface ProjectRepository{
    Project findById(Long id);
    Project findByName( Project project);
    Set<Project> findAll();

    Project save(Project project);

    Project update(Project Employee);

    void deleteById(Long id);


}
