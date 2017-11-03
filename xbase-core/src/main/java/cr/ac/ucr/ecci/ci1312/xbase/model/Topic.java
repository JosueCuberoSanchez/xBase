package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.*;
import java.util.*;

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
@Entity
@Table(name = "topics")
public class Topic extends BasicEntity{

    @Column (name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "parentTopic")
    private List<Topic> topicChildren;

    @ManyToOne
    private Topic parentTopic;

    @ManyToMany
    private List<Exercise> associatedExercises = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Topic> getTopicChildren() {
        return topicChildren;
    }

    public void setTopicChildren(List<Topic> topicChildren) {
        this.topicChildren = topicChildren;
    }

    public Topic getParentTopic() {
        return parentTopic;
    }

    public void setParentTopic(Topic parentTopic) {
        this.parentTopic = parentTopic;
    }

    public List<Exercise> getAssociatedExercises() {
        return associatedExercises;
    }

    public void setAssociatedExercises(List<Exercise> associatedExercises) {
        this.associatedExercises = associatedExercises;
    }

    public void addChildren(Topic topic){
        topicChildren.add(topic);
    }

    public void addExercise(Exercise exercise){
        associatedExercises.add(exercise);
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEquals = false;
        if(o instanceof Topic){
            Topic topic = (Topic) o;
            if(name.equals(topic.getName())){
                isEquals = true;
            }
        }
        return isEquals;
    }

    @Override
    protected int onHashCode(int result) {

        return super.id.hashCode();
    }
}
