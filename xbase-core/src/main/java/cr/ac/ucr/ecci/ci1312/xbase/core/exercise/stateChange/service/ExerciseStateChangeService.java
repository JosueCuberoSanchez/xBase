package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.service;

import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseStateChange;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService;

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
public interface ExerciseStateChangeService extends CrudService<ExerciseStateChange, String> {
    ExerciseStateChange getExerciseState(Student student, Exercise exercise);

    void solveExercise(ExerciseStateChange stateChange, int rating);
    void setExercisePending(ExerciseStateChange stateChange);
    void startExercise(Student student, Exercise exercise);
}
