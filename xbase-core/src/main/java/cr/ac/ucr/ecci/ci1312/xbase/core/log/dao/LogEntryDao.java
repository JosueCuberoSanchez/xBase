package cr.ac.ucr.ecci.ci1312.xbase.core.log.dao;

import cr.ac.ucr.ecci.ci1312.xbase.model.AnswerLog;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseLog;
import cr.ac.ucr.ecci.ci1312.xbase.model.LogEntry;
import cr.ac.ucr.ecci.ci1312.xbase.model.ProcedureLog;
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
public interface LogEntryDao extends CrudDao<LogEntry, String> {
    List<ExerciseLog> getExerciseLogs();
    List<ProcedureLog> getProcedureLogs();
    List<AnswerLog> getAnswerLogs();
}
