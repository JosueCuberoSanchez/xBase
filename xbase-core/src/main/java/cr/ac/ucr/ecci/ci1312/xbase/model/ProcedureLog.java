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
@DiscriminatorValue("procedure_log")
public class ProcedureLog extends LogEntry {

    @ManyToOne
    private ExerciseProcedure procedure;

    public ExerciseProcedure getProcedure() {
        return procedure;
    }

    public void setProcedure(ExerciseProcedure procedure) {
        this.procedure = procedure;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean onEquals = false;
        if (o instanceof ProcedureLog){
            ProcedureLog procedureLog = (ProcedureLog) o;
            onEquals = procedure.equals(procedureLog.procedure);
        }
        return onEquals && super.onEquals(o);
    }

}
