package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
@DiscriminatorValue("answer_log")
public class AnswerLog extends LogEntry {

    @ManyToOne
    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean onEquals = false;
        if (o instanceof  AnswerLog){
            AnswerLog answerLog = (AnswerLog) o;
            onEquals = answer.equals(answerLog.answer);
        }
        return onEquals && super.onEquals(o);
    }


}
