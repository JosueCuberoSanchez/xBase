package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@DiscriminatorValue("text_procedure")
public class TextExerciseProcedure extends ExerciseProcedure {

    @Column(name = "text", columnDefinition ="TEXT")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEquals = false;
        if(o instanceof TextExerciseProcedure){
            TextExerciseProcedure procedure = (TextExerciseProcedure) o;
            if(text.equals(procedure.text) ){
                isEquals = true;
            }
        }
        return isEquals;
    }
}
