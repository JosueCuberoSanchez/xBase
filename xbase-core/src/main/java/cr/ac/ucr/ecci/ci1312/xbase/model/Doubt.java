package cr.ac.ucr.ecci.ci1312.xbase.model;

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
@Table(name = "doubts")
public class Doubt extends BasicEntity{

    @Column(name = "doubt")
    private String doubt;

    @Column(name = "is_solved")
    private boolean isSolved;

    @ManyToOne
    private Student doubtingStudent;

    @ManyToOne
    private Exercise exercise;

    public String getDoubt() {
        return doubt;
    }

    public void setDoubt(String doubt) {
        this.doubt = doubt;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public Student getDoubtingStudent() {
        return doubtingStudent;
    }

    public void setDoubtingStudent(Student doubtingStudent) {
        this.doubtingStudent = doubtingStudent;
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
        if(o instanceof Doubt){
            Doubt doubtCompare = (Doubt) o;
            if(doubt.equals(doubtCompare.doubt) && doubtingStudent.equals( doubtCompare.doubtingStudent) ){
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
