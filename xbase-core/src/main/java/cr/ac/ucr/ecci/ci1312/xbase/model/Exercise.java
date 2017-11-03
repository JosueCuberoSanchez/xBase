package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;

import javax.persistence.*;
import java.util.*;
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
@Table(name = "exercises")
public class Exercise extends BasicEntity{

    @Column(name = "problem", nullable = false, columnDefinition ="TEXT")
    private String problem;

    @Column(name = "snippet")
    private String snippet;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

    @Column(name = "is_published")
    private boolean isPublished;

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "avg_rating")
    private int averageRating;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Student> markedFavoriteBy = new LinkedList<>();

    @OneToMany(mappedBy = "exercise")
    private List<Answer> answers = new LinkedList<>();

    @ManyToMany(mappedBy = "associatedExercises")
    private List<Topic> topics = new LinkedList<>();

    @ManyToMany
    private List<BibliographicReference> references = new LinkedList<>();

    @OneToMany(mappedBy = "doubtingStudent")
    private List<Doubt> doubtingStudents = new LinkedList<>();

    @ManyToMany(mappedBy = "solved")
    private List<Student> solvedBy = new LinkedList<>();

    @OneToMany(mappedBy = "exercise")
    private List<LogEntry> logEntries = new LinkedList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseStateChange> changes = new LinkedList<>();

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Student> getMarkedFavoriteBy() {
        return markedFavoriteBy;
    }

    public void setMarkedFavoriteBy(List<Student> markedFavoriteBy) {
        this.markedFavoriteBy = markedFavoriteBy;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<BibliographicReference> getReferences() {
        return references;
    }

    public void setReferences(List<BibliographicReference> references) {
        this.references = references;
    }

    public List<Doubt> getDoubtingStudents() {
        return doubtingStudents;
    }

    public void setDoubtingStudents(List<Doubt> doubtingStudents) {
        this.doubtingStudents = doubtingStudents;
    }

    public List<Student> getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(List<Student> solvedBy) {
        this.solvedBy = solvedBy;
    }

    public boolean isPublished() { return isPublished; }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public List<ExerciseStateChange> getChanges() {
        return changes;
    }

    public void setChanges(List<ExerciseStateChange> changes) {
        this.changes = changes;
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public void addLog(LogEntry logEntry){
        logEntries.add(logEntry);
    }

    public void addReference(BibliographicReference reference){
        references.add(reference);
    }

    public void addStudentWhoSolved(Student student){
        solvedBy.add(student);
    }

    public void addExerciseStateChange(ExerciseStateChange stateChange){
        changes.add(stateChange);
    }

    public void addDoubtingStudent(Doubt doubt){
        doubtingStudents.add(doubt);
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public void updateAverageRating(int rating){
        averageRating = (averageRating + rating) / solvedBy.size();
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEqual = false;
        Exercise exercise = (Exercise) o;
        if(problem.equals(exercise.problem)){
            isEqual = true;
        }
        return isEqual;
    }

    @Override
    protected int onHashCode(int result) {
        return super.id.hashCode();
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
}
