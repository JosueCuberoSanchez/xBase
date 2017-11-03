package cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.dao.AnswerReportDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.service.AnswerReportService;
import cr.ac.ucr.ecci.ci1312.xbase.model.AnswerReport;
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
@Service("answerReportService")
@Transactional
public class AnswerReportServiceImpl extends CrudServiceImpl<AnswerReport, String> implements AnswerReportService {

    @Autowired
    private AnswerReportDao answerReportDao;

    public void init(){
        super.setCrudDao(this.answerReportDao);
    }

    @Override
    public List<AnswerReport> getReports() {
        return this.answerReportDao.getReports();
    }

    @Override
    public List<AnswerReport> getUnresolvedReports() {
        return this.answerReportDao.getUnresolvedReports();
    }
}
