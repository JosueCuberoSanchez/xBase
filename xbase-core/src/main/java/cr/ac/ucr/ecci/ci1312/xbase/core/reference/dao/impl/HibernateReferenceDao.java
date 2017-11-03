package cr.ac.ucr.ecci.ci1312.xbase.core.reference.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.reference.dao.ReferenceDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.BibliographicReference;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

@Repository("referenceDao")
public class HibernateReferenceDao extends HibernateCrudDao<BibliographicReference, String> implements ReferenceDao {

    @Autowired
    public HibernateReferenceDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
}
