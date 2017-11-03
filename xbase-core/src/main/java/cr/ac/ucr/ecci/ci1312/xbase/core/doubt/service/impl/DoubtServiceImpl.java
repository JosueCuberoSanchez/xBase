package cr.ac.ucr.ecci.ci1312.xbase.core.doubt.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.doubt.dao.DoubtDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.doubt.service.DoubtService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Doubt;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
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

@Service("doubtService")
@Transactional
public class DoubtServiceImpl  extends CrudServiceImpl<Doubt,String> implements DoubtService {

    @Autowired
    private DoubtDao doubtDao;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExerciseService exerciseService;

    public void init(){
        super.setCrudDao(this.doubtDao);
    }

    public void doubtArises(Student student, Exercise exercise, String doubtText){
        Doubt doubt = new Doubt();
        doubt.setSolved(false);
        doubt.setDoubt(doubtText);
        doubt.setDoubtingStudent(student);
        doubt.setExercise(exercise);
        this.create(doubt);
    }

    @Override
    public List<Doubt> getUnresolvedOrderedDoubts() {
        return doubtDao.getUnresolvedOrderedDoubts();
    }

    @Override
    public List<Doubt> getOrderedDoubts() {
        return this.doubtDao.getOrderedDoubts();
    }

    @Override
    public List<Doubt> getUnresolvedDoubts() {
        return this.doubtDao.getUnresolvedDoubts();
    }
}
