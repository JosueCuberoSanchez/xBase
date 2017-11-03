package cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.topic.dao.TopicDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.TopicService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Topic;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service("topicService")
@Transactional
public class TopicServiceImpl extends CrudServiceImpl<Topic, String> implements TopicService {

    @Autowired
    private TopicDao topicDao;

    public void init(){
        setCrudDao(this.topicDao);
    }

    @Override
    public List<Topic> getRelatedTopics(String topicName) {
        return this.topicDao.getRelatedTopics(topicName.toLowerCase());
    }

    @Override
    public Topic getTopicByName(String name) {
        return this.topicDao.getTopicByName(name.toLowerCase());
    }

    @Override
    public List<Topic> getParentTopics(){
        return this.topicDao.getParentTopics();
    }

    @Override
    public String create(Topic entity) {
        if(this.topicDao.getTopicByName(entity.getName()) == null) {
            entity.setName(entity.getName().toLowerCase());
            return this.topicDao.create(entity);
        }
        return null;
    }

    @Override
    public void remove(Topic entity) {
        this.topicDao.remove(entity);
    }

    @Override
    public void update(Topic entity) {
        this.topicDao.update(entity);
    }

    @Override
    public Topic findById(String entityId) {
        return this.topicDao.findById(entityId);
    }

    public void changeTopicName(String newName, Topic topic){
        topic.setName(newName);
        this.update(topic);
    }
}
