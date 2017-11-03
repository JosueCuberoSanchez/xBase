package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.*;
import java.util.LinkedList;
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
@Entity
@Table(name = "exercise_procedure")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "procedure_type", discriminatorType = DiscriminatorType.STRING)
public abstract class  ExerciseProcedure extends BasicEntity {

    @OneToOne
    private PracticalAnswer answer;

    @OneToMany(mappedBy = "procedure")
    private List<ProcedureLog> logs = new LinkedList<>();

    @Column (name = "is_deleted")
    private boolean isDeleted;

    public PracticalAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(PracticalAnswer answer) {
        this.answer = answer;
    }

    public List<ProcedureLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ProcedureLog> logs) {
        this.logs = logs;
    }

    public void addLogs(ProcedureLog logEntry){
        logs.add(logEntry);
    }

    @Override
    protected int onHashCode(int result) {
        return super.id.hashCode();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    protected boolean onEquals(Object o) {
        ExerciseProcedure procedure = (ExerciseProcedure) o;
        return answer.equals(procedure.answer);
    }
}
