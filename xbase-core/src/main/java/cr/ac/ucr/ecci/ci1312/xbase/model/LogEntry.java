package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.ActionType;
import sun.rmi.runtime.Log;

import javax.persistence.*;

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
@Table(name = "log_entries")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "log_type", discriminatorType = DiscriminatorType.STRING)

public abstract class LogEntry extends BasicEntity {
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionType action;

    @ManyToOne
    private Administrator administrator;

    @ManyToOne
    private Exercise exercise;

    public ActionType getAction() {
        return action;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public LogEntry(){}

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEquals = false;
        LogEntry logEntry = (LogEntry) o;
        if(administrator.equals(logEntry.administrator) && getEntityCreationTimestamp().equals( logEntry.getEntityCreationTimestamp() )){
            isEquals = true;
        }
        return isEquals;
    }

    @Override
    protected int onHashCode(int result) {
        return super.id.hashCode();
    }
}
