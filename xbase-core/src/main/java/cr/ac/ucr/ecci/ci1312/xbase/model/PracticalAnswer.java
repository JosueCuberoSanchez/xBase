package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
@DiscriminatorValue("practical")
public class PracticalAnswer extends Answer {

    @OneToOne(mappedBy = "answer")
    private ExerciseProcedure exerciseProcedure;

    @Override
    public boolean isPractical() {
        return true;
    }

    public ExerciseProcedure getExerciseProcedure() {
        return exerciseProcedure;
    }

    @Override
    protected boolean onEquals(Object o) {
        return o instanceof PracticalAnswer && super.id.equals( ((PracticalAnswer) o).id);
    }

    public void setExerciseProcedure(ExerciseProcedure exerciseProcedure) {
        this.exerciseProcedure = exerciseProcedure;
    }
}
