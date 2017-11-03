package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
@Table(name = "author")
public class Author extends BasicEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_last_name")
    private String secondLastName;

    @ManyToMany
    private List<BibliographicReference> references = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public List<BibliographicReference> getReferences() {
        return references;
    }

    public void setReferences(List<BibliographicReference> references) {
        this.references = references;
    }

    @Override
    protected boolean onEquals(Object o) {
        Author author = (Author) o;
        boolean equals = false;
        if(name.equals(author.name) && lastName.equals(author.lastName) && secondLastName.equals(author.secondLastName)){
            equals = true;
        }
        return equals;
    }

    @Override
    public int onHashCode(int result) {
        return super.id.hashCode();
    }
}
