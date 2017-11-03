package cr.ac.ucr.ecci.ci1312.xbase.core.log.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.log.dao.LogEntryDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.AnswerLog;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseLog;
import cr.ac.ucr.ecci.ci1312.xbase.model.LogEntry;
import cr.ac.ucr.ecci.ci1312.xbase.model.ProcedureLog;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
@Repository("logEntryDao")
public class HibernateLogEntryDao extends HibernateCrudDao<LogEntry, String> implements LogEntryDao {

    public HibernateLogEntryDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProcedureLog> getProcedureLogs() {
        String sql = "SELECT * FROM log_entries WHERE log_type = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(ProcedureLog.class);
        query.setParameter(0, "procedure_log");
        return (List<ProcedureLog>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ExerciseLog> getExerciseLogs() {
        String sql = "SELECT * FROM log_entries WHERE log_type = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(ExerciseLog.class);
        query.setParameter(0, "exercise_log");
        return (List<ExerciseLog>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AnswerLog> getAnswerLogs() {
        String sql = "SELECT * FROM log_entries WHERE log_type = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(AnswerLog.class);
        query.setParameter(0, "answer_log");
        return (List<AnswerLog>) query.list();
    }
}
