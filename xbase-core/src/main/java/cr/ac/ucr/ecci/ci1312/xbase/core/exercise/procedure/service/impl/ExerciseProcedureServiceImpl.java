package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.ExerciseProcedureService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.dao.ExerciseProcedureDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.log.service.LogEntryService;
import cr.ac.ucr.ecci.ci1312.xbase.enums.ActionType;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
@Service("exerciseProcedureService")
@Transactional
public class ExerciseProcedureServiceImpl extends CrudServiceImpl<ExerciseProcedure, String> implements ExerciseProcedureService {

    @Autowired
    private ExerciseProcedureDao exerciseProcedureDao;

    @Autowired
    private LogEntryService logEntryService;


    public void init(){
        setCrudDao(this.exerciseProcedureDao);
    }

    private void createLog(ExerciseProcedure procedure, ActionType type){
        ProcedureLog log = new ProcedureLog();
        log.setAction(type);
        log.setProcedure(procedure);
        log.setExercise( procedure.getAnswer().getExercise() );
        logEntryService.create(log);
    }

    @Override
    public void update(ExerciseProcedure entity) {
        this.createLog(entity, ActionType.UPDATE);
        super.update(entity);
    }
}
