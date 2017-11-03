package cr.ac.ucr.ecci.ci1312.xbase.core.topic.dao.impl;


import cr.ac.ucr.ecci.ci1312.xbase.core.topic.dao.TopicDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Topic;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

@Repository("topicDao")
public class HibernateTopicDao extends HibernateCrudDao<Topic, String> implements TopicDao{
    @Autowired
    public HibernateTopicDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getRelatedTopics(String topicName){
        String sql = "SELECT t1.* FROM topics AS t1, (SELECT id FROM topics WHERE topics.name = ?) AS t2 WHERE t1.parentTopic_id = t2.id";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Topic.class);
        query.setParameter(0, topicName);
        return (List<Topic>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Topic> getParentTopics(){
        String sql = "SELECT * FROM topics WHERE parentTopic_id IS NULL";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Topic.class);
        return (List<Topic>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Topic getTopicByName(String name) {
        String sql = "SELECT * FROM topics WHERE name = ?";
        Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Topic.class);
        query.setParameter(0, name);
        Collection topics = query.list();
        return (Topic) DataAccessUtils.singleResult(topics);
    }
}
