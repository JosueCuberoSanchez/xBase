package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.dao.ExerciseStateChangeDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseStateChange;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Proyecto de Bases de Datos 1
 * xBase
 * Autores:
 * Alemán Ramírez Esteban
 * Borchgrevink Leiva Alexia
 * Cubero Sánchez Josué
 * Durán Gregory Ian
 * Garita Centeno Alonso
 * Hidalgo Campos Jose
 * Mellado Xatruch Carlos
 * Muñoz Miranda Roy
 *
 * Primer ciclo 2017
 */

@Repository("exerciseStateChangeDao")
public class HibernateExerciseStateChangeDao extends HibernateCrudDao<ExerciseStateChange, String> implements ExerciseStateChangeDao {

    @Autowired
    public HibernateExerciseStateChangeDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ExerciseStateChange getExerciseState(Student student, Exercise exercise){
        /*String sql2 = "SELECT  state.* FROM state_changes AS state " +
                "INNER JOIN (SELECT * FROM exercises WHERE id = ?) AS ex " +
                "ON state.exercise_id = ex.id INNER JOIN (SELECT * FROM students WHERE id = ?) AS stu " +
                "ON state.student_id = students.id";*/
        String sql = "SELECT state_changes.* FROM state_changes, exercises, students WHERE exercises.id = ? AND students.id = ? AND state_changes.exercise_id = exercises.id AND state_changes.student_id = students.id";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(ExerciseStateChange.class);
        query.setParameter(0, exercise.getId());
        query.setParameter(1,student.getId());
        Collection state = query.list();
        return (ExerciseStateChange) DataAccessUtils.singleResult(state);
    }
}
