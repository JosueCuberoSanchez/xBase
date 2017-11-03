package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.impl;


import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.dao.ExerciseDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.dao.ExerciseProcedureDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.log.dao.LogEntryDao;
import cr.ac.ucr.ecci.ci1312.xbase.enums.ActionType;
import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
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

@Service("exerciseService")
@Transactional
public class ExerciseServiceImpl extends CrudServiceImpl<Exercise, String> implements ExerciseService {

    @Autowired
    private ExerciseDao exerciseDao;

    @Autowired
    private LogEntryDao logDao;

    @Autowired
    private ExerciseProcedureDao exerciseProcedureDao;

    public void init(){
        setCrudDao(this.exerciseDao);
    }

    @Override
    public List<Exercise> getExercisesByTopic(String topic) {
        return this.exerciseDao.getExercisesByTopic(topic.toLowerCase());
    }

    @Override
    public List<Exercise> getExercisesByKeyword(String keyword) {
        return this.exerciseDao.getExercisesByKeyword(keyword);
    }

    @Override
    public List<Exercise> getExercisesByRating(int rating) {
        return this.exerciseDao.getExercisesByRating(rating);
    }

    @Override
    public List<Exercise> getVisibleExercisesByTopic(String topic) {
        return this.exerciseDao.getVisibleExercisesByTopic(topic);
    }

    @Override
    public List<Exercise> getVisibleExercisesByKeyword(String keyword) {
        return this.exerciseDao.getVisibleExercisesByKeyword(keyword);
    }

    @Override
    public List<Exercise> getVisibleExercisesByRating(int rating) {
        return this.exerciseDao.getExercisesByRating(rating);
    }

    @Override
    public List<Exercise> getLastTenExercisesInProgress(Student student) {
        return exerciseDao.getLastTenExercisesInProgress(student);
    }

    @Override
    public List<Exercise> getExercisesOrderedByRating(){
        return this.exerciseDao.getExercisesOrderedByRating();
    }

    @Override
    public List<Exercise> getExercisesByDifficulty(Difficulty difficulty) {
        return this.exerciseDao.getExercisesByDifficulty(difficulty);
    }

    @Override
    public String getExerciseSnippetFromReport(Answer answer){
        return this.exerciseDao.getExerciseSnippetFromReport(answer);
    }

    @Override
    public List<Exercise> getVisibleExercises() {
        return this.exerciseDao.getVisibleExercises();
    }

    @Override
    public void update(Exercise entity) {
        this.createAssociatedLog(ActionType.UPDATE, entity);
        super.update(entity);
    }

    @Override
    public void remove(Exercise entity) {
        this.createAssociatedLog(ActionType.DELETION, entity);
        super.remove(entity);
    }

    @Override
    public String create(Exercise entity) {
        this.createAssociatedLog(ActionType.ADDITION, entity);
        entity.setVisible(true);
        entity.setPublished(true);
        entity.setDeleted(false);
        if(entity.getProblem().length() < 25)
            entity.setSnippet(entity.getProblem().substring(0, entity.getProblem().length()));
        else
            entity.setSnippet(entity.getProblem().substring(0, 25));

        entity.setAverageRating(0);
        return super.create(entity);
    }

    private void createAssociatedLog(ActionType type, Exercise exercise){
        ExerciseLog log = new ExerciseLog();
        log.setAction(type);
        log.setExercise(exercise);
        logDao.create(log);
    }
}
