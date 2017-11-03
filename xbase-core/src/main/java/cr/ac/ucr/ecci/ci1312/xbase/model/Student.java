package cr.ac.ucr.ecci.ci1312.xbase.model;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
@Table(name = "students")
public class Student extends User{

    @Column(name = "student_id", unique = true)
    private String studentId;

    @ManyToMany(mappedBy = "markedFavoriteBy")
    private List<Exercise> favorites = new LinkedList<>();

    @OneToMany(mappedBy = "reportingStudent")
    private Map<Answer, AnswerReport> reported = new HashMap<>();

    @OneToMany(mappedBy = "exercise")
    private List<Doubt> doubts = new LinkedList<>();

    @ManyToMany
    private List<Exercise> solved = new LinkedList<>();

    @OneToMany(mappedBy = "student")
    private List<ExerciseStateChange> stateChanges = new LinkedList<>();

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return array of granted authorities.
     */
    @Transient
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>(super.getAuthorities());
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        return authorities;
    }

    public List<Exercise> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Exercise> favorites) {
        this.favorites = favorites;
    }

    public Map<Answer, AnswerReport> getReported() {
        return reported;
    }

    public void setReported(Map<Answer, AnswerReport> reported) {
        this.reported = reported;
    }

    public List<Doubt> getDoubts() {
        return doubts;
    }

    public void setDoubts(List<Doubt> doubts) {
        this.doubts = doubts;
    }

    public List<Exercise> getSolved() {
        return solved;
    }

    public void setSolved(List<Exercise> solved) {
        this.solved = solved;
    }

    public List<ExerciseStateChange> getStateChanges() {
        return stateChanges;
    }

    public void setStateChanges(List<ExerciseStateChange> stateChanges) {
        this.stateChanges = stateChanges;
    }

    public void addSolvedExercise(Exercise exercise){
        solved.add(exercise);
    }

    public void addExerciseStateChange(ExerciseStateChange stateChange){
        stateChanges.add(stateChange);
    }

    public void addDoubt(Doubt doubt){
        doubts.add(doubt);
    }
}
