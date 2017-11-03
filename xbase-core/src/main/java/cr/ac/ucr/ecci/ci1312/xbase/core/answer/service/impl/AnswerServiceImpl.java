package cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.dao.AnswerDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.AnswerService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.ExerciseProcedureService;
import cr.ac.ucr.ecci.ci1312.xbase.core.log.dao.LogEntryDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.log.service.LogEntryService;
import cr.ac.ucr.ecci.ci1312.xbase.enums.ActionType;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service("answerService")
@Transactional
public class AnswerServiceImpl extends CrudServiceImpl<Answer,String> implements AnswerService{

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private LogEntryDao logDao;

    @Autowired
    private ExerciseProcedureService exerciseProcedureService;

    @Autowired
    private LogEntryService logService;

    public void init(){
        super.setCrudDao(this.answerDao);
    }

    @Override
    public void markAsDeleted(Answer answer){
        answer.setDeleted(true);
        answer.setVisible(false);
        this.createLog(answer, ActionType.DELETION);
        super.update(answer);
    }

    @Override
    public void markAsHidden(Answer answer){
        answer.setVisible(false);
        this.createLog(answer, ActionType.CONCEALMENT);
        super.update(answer);
    }

    public void changeAnswerProcedure(PracticalAnswer answer, ExerciseProcedure procedure) {
        ExerciseProcedure oldProcedure = answer.getExerciseProcedure();
        oldProcedure.setDeleted(true);
        this.exerciseProcedureService.update(oldProcedure);

        ProcedureLog oldProcLog = new ProcedureLog();
        oldProcLog.setAction(ActionType.DELETION);
        oldProcLog.setExercise(answer.getExercise());
        oldProcLog.setProcedure(oldProcedure);
        logService.create(oldProcLog);

        this.exerciseProcedureService.create(procedure);

        ProcedureLog procedureLog = new ProcedureLog();
        procedureLog.setAction(ActionType.ADDITION);
        procedureLog.setExercise(answer.getExercise());
        procedureLog.setProcedure(procedure);
        logService.create(procedureLog);
        this.update(answer);
    }

    @Override
    public List<Answer> getAnswersOfExercise(Exercise exercise) {
        return answerDao.getAnswersOfExercise(exercise);
    }


    private void createLog(Answer answer, ActionType type){
        AnswerLog log = new AnswerLog();
        log.setAnswer(answer);
        log.setAction(type);
        logDao.create(log);
    }

    @Override
    public void update(Answer entity) {
        this.createLog(entity, ActionType.UPDATE);
        super.update(entity);
    }
}
