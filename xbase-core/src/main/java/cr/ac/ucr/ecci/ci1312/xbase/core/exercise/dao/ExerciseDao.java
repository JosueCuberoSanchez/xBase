package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.dao;

import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
import cr.ac.ucr.ecci.ci1312.xbase.model.Answer;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.CrudDao;

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

public interface ExerciseDao extends CrudDao<Exercise,String>{
    List<Exercise> getExercisesByTopic(String topic);
    List<Exercise> getExercisesByKeyword(String keyword);
    List<Exercise> getExercisesByRating(int rating);
    List<Exercise> getVisibleExercisesByTopic(String topic);
    List<Exercise> getVisibleExercisesByKeyword(String keyword);
    List<Exercise> getVisibleExercisesByRating(int rating);
    List<Exercise> getExercisesByDifficulty(Difficulty difficulty);
    List<Exercise> getVisibleExercises();
    List<Exercise> getExercisesOrderedByRating();
    String getExerciseSnippetFromReport(Answer answer);
    List<Exercise> getLastTenExercisesInProgress(Student student);
}
