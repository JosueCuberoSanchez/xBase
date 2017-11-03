package cr.ac.ucr.ecci.ci1312.xbase.core.doubt.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.doubt.dao.DoubtDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Doubt;
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
@Repository("doubtDao")
public class HibernateDoubtDao extends HibernateCrudDao<Doubt, String> implements DoubtDao{

    @Autowired
    public HibernateDoubtDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Doubt> getOrderedDoubts() {
        String sql = "SELECT * FROM doubts ORDER BY entity_creation_timestamp DESC";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Doubt.class);
        return (List<Doubt>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Doubt> getUnresolvedDoubts() {
        String sql = "SELECT * FROM doubts WHERE is_solved = ? ORDER BY entity_creation_timestamp DESC";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Doubt.class);
        query.setParameter(0, false);
        return (List<Doubt>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Doubt> getUnresolvedOrderedDoubts() {
        String sql = "SELECT * FROM doubts WHERE is_solved = ? ORDER BY entity_creation_timestamp";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Doubt.class);
        query.setParameter(0, false);
        return (List<Doubt>) query.list();
    }
}
