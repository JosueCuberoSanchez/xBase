package cr.ac.ucr.ecci.ci1312.xbase.core.log.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.log.dao.LogEntryDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.log.service.LogEntryService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import cr.ac.ucr.ecci.ci1312.xbase.support.SecurityUtils;
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
@Service("logService")
@Transactional
public class LogEntryServiceImpl extends CrudServiceImpl<LogEntry, String> implements LogEntryService {

    @Autowired
    private LogEntryDao logEntryDao;

    @Autowired
    private AdministratorService administratorService;

    public void init(){
        super.setCrudDao(this.logEntryDao);
    }

    @Override
    public List<ExerciseLog> getExerciseLogs() {
        return logEntryDao.getExerciseLogs();
    }

    @Override
    public List<ProcedureLog> getProcedureLogs() {
        return logEntryDao.getProcedureLogs();
    }

    @Override
    public List<AnswerLog> getAnswerLogs() {
        return logEntryDao.getAnswerLogs();
    }

    @Override
    public String create(LogEntry entity) {
        Administrator administrator = administratorService.findById( SecurityUtils.getCurrentLoggedAdmin().getId() );
        entity.setAdministrator(administrator);
        return super.create(entity);
    }
}
