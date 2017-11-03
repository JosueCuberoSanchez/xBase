package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service;


import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService;

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

public interface ExerciseService extends CrudService<Exercise, String> {
    List<Exercise> getExercisesByTopic(String topic);
    List<Exercise> getExercisesByKeyword(String keyword);
    List<Exercise> getExercisesByRating(int rating);
    List<Exercise> getVisibleExercisesByTopic(String topic);
    List<Exercise> getVisibleExercisesByKeyword(String keyword);
    List<Exercise> getVisibleExercisesByRating(int rating);
    List<Exercise> getExercisesOrderedByRating();
    List<Exercise> getExercisesByDifficulty(Difficulty difficulty);
    List<Exercise> getVisibleExercises();
    String getExerciseSnippetFromReport(Answer answer);
    List<Exercise> getLastTenExercisesInProgress(Student student);
}
