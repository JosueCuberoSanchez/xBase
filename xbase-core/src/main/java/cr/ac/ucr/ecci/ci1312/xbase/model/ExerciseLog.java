package cr.ac.ucr.ecci.ci1312.xbase.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
@DiscriminatorValue("exercise_log")
public class ExerciseLog extends LogEntry {
}
