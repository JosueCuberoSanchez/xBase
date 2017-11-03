package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.dao.ExerciseDao;
import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
import cr.ac.ucr.ecci.ci1312.xbase.enums.ExerciseState;
import cr.ac.ucr.ecci.ci1312.xbase.model.Answer;
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

import java.util.ArrayList;
import java.util.List;

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

@Repository("exerciseDao")
public class HibernateExerciseDao extends HibernateCrudDao<Exercise, String> implements ExerciseDao {

    @Autowired
    public HibernateExerciseDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getExerciseSnippetFromReport(Answer answer){
        String sql = "SELECT * FROM exercises WHERE exercises.id = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, answer.getExercise().getId());
        List<Exercise> snippets = (List<Exercise>)query.list();
        Exercise currentExercise = DataAccessUtils.singleResult(snippets);
        return  currentExercise.getProblem().substring(0,200);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByTopic(String topic){
        String sql = "SELECT e.* FROM (SELECT * FROM topics WHERE name LIKE ?) AS t, topics_exercises AS te, exercises AS e WHERE t.id = te.topics_id AND te.associatedExercises_id = e.id";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, "%" + topic + "%");
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByKeyword(String keyword){
        String sql = "SELECT * FROM exercises WHERE problem LIKE ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, "%" + keyword + "%");
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByRating(int rating){
        String sql = "SELECT * FROM exercises WHERE avg_rating = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, rating);
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getVisibleExercisesByTopic(String topic) {
        String sql = "SELECT e.* FROM (SELECT * FROM topics WHERE name LIKE ?) AS t, topics_exercises AS te, exercises AS e WHERE t.id = te.topics_id AND te.associatedExercises_id = e.id AND e.is_published = 1";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, "%" + topic + "%");
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getVisibleExercisesByKeyword(String keyword) {
        String sql = "SELECT * FROM exercises WHERE problem LIKE ? AND is_published = 1";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, "%" + keyword + "%");
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getVisibleExercisesByRating(int rating) {
        String sql = "SELECT * FROM exercises WHERE avg_rating = ? AND is_published = 1";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, rating);
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesOrderedByRating(){
        String sql = "SELECT * FROM exercises WHERE is_published = 1 ORDER BY avg_rating DESC";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        return (List<Exercise>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Exercise> getExercisesByDifficulty(Difficulty difficulty){
        String sql = "SELECT * FROM exercises WHERE difficulty = ? AND is_published = 1";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, difficulty.toString());
        return (List<Exercise>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Exercise> getVisibleExercises() {
        String sql = "SELECT * FROM exercises WHERE is_published = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Exercise.class);
        query.setParameter(0, true);
        return (List<Exercise>) query.list();
    }

    @Override
    public List<Exercise> getLastTenExercisesInProgress(Student student) {
        String sql = "SELECT * FROM state_changes WHERE student_id = ? AND state = ? ORDER BY seen_date DESC";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(ExerciseStateChange.class);
        query.setParameter(0, student.getId());
        query.setParameter(1, ExerciseState.IN_PROGRESS);
        List<ExerciseStateChange> stateChanges = (List<ExerciseStateChange>) query.list();

        List<Exercise> exerciseList = new ArrayList<>();
        int numStateChanges = stateChanges.size();
        int max = (numStateChanges < 10) ? numStateChanges : 10;
        for (int i = 0; i < max; i++) {
            exerciseList.add( stateChanges.get(i).getExercise() );
        }
        return exerciseList;
    }
}
