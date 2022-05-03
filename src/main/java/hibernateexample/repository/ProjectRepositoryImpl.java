package hibernateexample.repository;

import hibernateexample.enitities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class ProjectRepositoryImpl implements ProjectRepository{

    private SessionFactory sessionFactory;

    public ProjectRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Project findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Project project= session.find(Project.class, id);

        transaction.commit();
        session.close();

        return project;
    }

    @Override
    public Project findByName(Project proj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = null;
        try {
            project = (Project) session.createQuery("select e FROM Project e WHERE name = :Name")
                    .setParameter("Name", proj.getName()).getSingleResult();
        }catch (Exception e){
            return null;
        }
        transaction.commit();
        session.close();

        return project;
    }

    @Override
    public Set<Project> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Project> query =
                session.createQuery(
                        "SELECT p FROM Project p",
                        Project.class);
        Set<Project> projects = new HashSet<>(query.getResultList());

        transaction.commit();
        session.close();

        return projects;
    }

    @Override
    public Project save(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(project);

        transaction.commit();
        session.close();

        return project;
    }

    @Override
    public Project update(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(project);

        transaction.commit();
        session.close();

        return project;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("DELETE FROM Project WHERE id = :ID")
                .setParameter("ID", id)
                .executeUpdate();

        transaction.commit();
        session.close();
    }
}
