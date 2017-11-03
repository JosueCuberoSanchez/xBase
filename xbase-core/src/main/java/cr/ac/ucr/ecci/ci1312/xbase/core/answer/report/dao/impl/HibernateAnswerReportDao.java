package cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.dao.AnswerReportDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.AnswerReport;
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
@Repository("answerReportDao")
public class HibernateAnswerReportDao extends HibernateCrudDao<AnswerReport,String> implements AnswerReportDao{

    @Autowired
    public HibernateAnswerReportDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AnswerReport> getReports(){
        String sql = "SELECT * FROM answer_reports ORDER BY entity_creation_timestamp DESC";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(AnswerReport.class);
        return (List<AnswerReport>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AnswerReport> getUnresolvedReports(){
        String sql = "SELECT * FROM answer_reports WHERE state = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(AnswerReport.class);
        query.setParameter(0, "in_progress");
        return (List<AnswerReport>) query.list();
    }
}
