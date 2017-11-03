package cr.ac.ucr.ecci.ci1312.xbase.core.answer.service;

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
public interface AnswerService extends CrudService<Answer,String>{
    List<Answer> getAnswersOfExercise(Exercise exercise);

    void markAsDeleted(Answer answer);
    void markAsHidden(Answer answer);

    void changeAnswerProcedure(PracticalAnswer answer, ExerciseProcedure procedure);
}
