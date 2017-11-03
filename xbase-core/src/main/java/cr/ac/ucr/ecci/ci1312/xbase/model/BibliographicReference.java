package cr.ac.ucr.ecci.ci1312.xbase.model;

import cr.ac.ucr.ecci.ci1312.xbase.enums.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;

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
@Table(name = "bibliographic_reference")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "reference_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BibliographicReference extends BasicEntity{

    @Column(name = "publication_year")
    private Integer publicationYear;

    @ManyToMany(mappedBy = "references")
    private List<Exercise> exercises = new LinkedList<>();

    @ManyToMany(mappedBy = "references")
    private List<Author> authors = new LinkedList<>();

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean isEqual = false;
        if ( o instanceof BibliographicReference ){
            BibliographicReference reference = (BibliographicReference) o;
            if (exercises.equals( reference.exercises) && authors.equals( reference.authors ) ){
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    protected int onHashCode(int result) {
        return super.id.hashCode();
    }
}
