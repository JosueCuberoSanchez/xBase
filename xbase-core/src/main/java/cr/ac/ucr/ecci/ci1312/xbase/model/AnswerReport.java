package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.ReportState;

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
@Table(name = "answer_reports")
public class AnswerReport extends BasicEntity {
    @Column(name = "explanation", columnDefinition ="TEXT")
    private String explanation;

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student reportingStudent;

    @ManyToOne
    @JoinColumn(name = "fk_answer")
    private Answer reportedAnswer;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ReportState state;

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Student getReportingStudent() {
        return reportingStudent;
    }

    public void setReportingStudent(Student reportingStudent) {
        this.reportingStudent = reportingStudent;
    }

    public Answer getReportedAnswer() {
        return reportedAnswer;
    }

    public void setReportedAnswer(Answer reportedAnswer) {
        this.reportedAnswer = reportedAnswer;
    }

    public ReportState getState() {
        return state;
    }

    public void setState(ReportState state) {
        this.state = state;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEquals = false;
        if (o instanceof AnswerReport){
            AnswerReport report = (AnswerReport) o;
            if(explanation.equals(report.explanation) && reportingStudent == report.reportingStudent){
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
