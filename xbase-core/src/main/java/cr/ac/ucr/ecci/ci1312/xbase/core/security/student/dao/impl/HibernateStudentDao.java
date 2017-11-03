package cr.ac.ucr.ecci.ci1312.xbase.core.security.student.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.enums.ExerciseState;
import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseStateChange;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.dao.StudentDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Hibernate implementation of the {@link StudentDao}.
 *
 * @author Rodrigo A. Bartels
 */
@Repository("studentDao")
public class    HibernateStudentDao extends HibernateCrudDao<Student, String> implements StudentDao {

    @Autowired
    public HibernateStudentDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public Student findUserByUsername(String username){
        String sql = "SELECT * FROM students WHERE username = ?";
        Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Student.class);
        query.setParameter(0, username);
        Collection students = query.list();
        return (Student) DataAccessUtils.singleResult(students);
    }

    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents(){
        String sql = "SELECT * FROM students";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Student.class);
        return (List<Student>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getStudentsByName(String keyword) {
        String sql = "SELECT * FROM students WHERE name LIKE ? OR last_name LIKE ? OR second_last_name LIKE ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Student.class);
        query.setParameter(0, "%" + keyword + "%");
        query.setParameter(1, "%" + keyword + "%");
        query.setParameter(2, "%" + keyword + "%");
        return (List<Student>) query.list();
    }
}
