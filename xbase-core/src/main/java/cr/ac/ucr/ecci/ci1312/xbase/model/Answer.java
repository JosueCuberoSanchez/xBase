package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.AnswerState;
import cr.ac.ucr.ecci.ci1312.xbase.enums.AnswerType;

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
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("theoretical")

public class Answer extends BasicEntity {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AnswerType type;

    @Column(name = "state")
    private AnswerState state;

    @Column(name = "is_correct",nullable = false)
    private boolean isCorrect;

    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;

    @Column(name = "is_visible",nullable = false)
    private boolean isVisible;

    @Column(name = "numerical_answer")
    private Double numericalAnswer;

    @Column(name = "unit")
    private String unit;


    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "source_code", columnDefinition ="TEXT")
    private String sourceCode;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "fk_exercise")
    private Exercise exercise;

    @OneToMany(mappedBy = "answer")
    private List<AnswerLog> logs = new LinkedList<>();

    @OneToMany(mappedBy = "reportedAnswer")
    private List<AnswerReport> reports = new LinkedList<>();

    public boolean isPractical(){
        return false;
    }

    public AnswerType getType() {
        return type;
    }

    public AnswerState getState() {
        return state;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public List<AnswerLog> getLogs() {
        return logs;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public double getNumericalAnswer() {
        return numericalAnswer;
    }

    public String getUnit() {
        return unit;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public List<AnswerReport> getReports() {
        return reports;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }

    public void setState(AnswerState state) {
        this.state = state;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setNumericalAnswer(Double numericalAnswer) {
        this.numericalAnswer = numericalAnswer;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setLogs(List<AnswerLog> logs) {
        this.logs = logs;
    }

    public void setReports(List<AnswerReport> reports) {
        this.reports = reports;
    }

    public void addLogs(AnswerLog log){
        logs.add(log);
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEqual = false;
        if(o instanceof Answer){
            Answer answer = (Answer) o;
            switch (type){
                case CODE:
                    if (sourceCode.equals(answer.sourceCode)){
                        isEqual = true;
                    }

                case TEXT:
                    if (content.equals(answer.content)){
                        isEqual = true;
                    }

                case IMAGE:
                    if (imagePath.equals(answer.imagePath)){
                        isEqual = true;
                    }

                case NUMERIC:
                    if(numericalAnswer.equals(answer.numericalAnswer)){
                        isEqual = true;
                    }
            }
        }
        return isEqual;
    }

    @Override
    protected int onHashCode(int result) {
        return super.id.hashCode();
    }


}
