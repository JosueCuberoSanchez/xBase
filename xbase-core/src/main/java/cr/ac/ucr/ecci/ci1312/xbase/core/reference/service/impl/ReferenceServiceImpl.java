package cr.ac.ucr.ecci.ci1312.xbase.core.reference.service.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.reference.dao.ReferenceDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.reference.service.ReferenceService;
import cr.ac.ucr.ecci.ci1312.xbase.model.BibliographicReference;
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
@Service("referenceService")
@Transactional
public class ReferenceServiceImpl extends CrudServiceImpl<BibliographicReference, String> implements ReferenceService {

    @Autowired
    private ReferenceDao referenceDao;

    public void init(){
        super.setCrudDao(this.referenceDao);
    }


}
