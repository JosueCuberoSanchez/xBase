package cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.dao.ExerciseDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.dao.ExerciseStateChangeDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.service.ExerciseStateChangeService;
import cr.ac.ucr.ecci.ci1312.xbase.enums.ExerciseState;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.ExerciseStateChange;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
@Service("exerciseStateChangeService")
@Transactional
public class ExerciseStateChangeServiceImpl extends CrudServiceImpl<ExerciseStateChange, String> implements ExerciseStateChangeService {

    @Autowired
    private ExerciseStateChangeDao stateChangeDao;
    
    @Autowired
    private ExerciseDao exerciseDao;

    public void init(){
        setCrudDao(this.stateChangeDao);
    }
    
    @Override
    public void solveExercise(ExerciseStateChange stateChange, int rating) {
        Student student = stateChange.getStudent();

        Exercise exercise = stateChange.getExercise();
        exercise.addStudentWhoSolved(student);
        exercise.updateAverageRating(rating);
        exerciseDao.update(exercise);

        stateChange.setRating(rating);
        stateChange.setState(ExerciseState.SOLVED);
        stateChange.setSolvedDate(new Date());
        super.update(stateChange);
    }

    @Override
    public void startExercise(Student student, Exercise exercise) {
        ExerciseStateChange stateChange = new ExerciseStateChange();
        stateChange.setState(ExerciseState.IN_PROGRESS);
        stateChange.setExercise(exercise);
        stateChange.setStudent(student);
        stateChange.setSeenDate(new Date());
        super.create(stateChange);
    }

    @Override
    public void setExercisePending(ExerciseStateChange stateChange) {
        stateChange.setState(ExerciseState.PENDING);
        super.update(stateChange);
    }

    @Override
    public ExerciseStateChange getExerciseState(Student st, Exercise ex){ return stateChangeDao.getExerciseState(st,ex);}
}
