package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.ExerciseState;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "state_changes")
public class ExerciseStateChange extends BasicEntity {

    @Column(name = "state")
    private ExerciseState state;

    @Column(name = "seen_date")
    private Date seenDate;

    @Column(name = "solved_date")
    private Date solvedDate;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exercise exercise;

    public ExerciseState getState() {
        return state;
    }

    public void setState(ExerciseState state) {
        this.state = state;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }

    public Date getSolvedDate() {
        return solvedDate;
    }

    public void setSolvedDate(Date solvedDate) {
        this.solvedDate = solvedDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEquals = false;
        if(o instanceof ExerciseStateChange){
            ExerciseStateChange stateChange = (ExerciseStateChange) o;
            if(student == stateChange.student && exercise == stateChange.exercise){
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
