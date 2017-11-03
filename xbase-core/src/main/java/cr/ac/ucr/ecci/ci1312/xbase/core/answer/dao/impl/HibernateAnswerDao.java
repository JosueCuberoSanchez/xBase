package cr.ac.ucr.ecci.ci1312.xbase.core.answer.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.dao.AnswerDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Answer;
import cr.ac.ucr.ecci.ci1312.xbase.model.AnswerLog;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

@Repository("answerDao")
public class HibernateAnswerDao extends HibernateCrudDao<Answer, String> implements AnswerDao{

    @Autowired
    public HibernateAnswerDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public List<Answer> getAnswersOfExercise(Exercise exercise){
        String sql = "SELECT * FROM answer where fk_exercise = ? AND is_correct = 1 AND is_visible = 1";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Answer.class);
        query.setParameter(0, exercise.getId());
        return (List<Answer>) query.list();
    }

}
