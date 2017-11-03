package cr.ac.ucr.ecci.ci1312.xbase.core.author.dao.impl;

import cr.ac.ucr.ecci.ci1312.xbase.core.author.dao.AuthorDao;
import cr.ac.ucr.ecci.ci1312.xbase.model.Author;
import org.hibernate.SessionFactory;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.impl.HibernateCrudDao;
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
@Repository("authorDao")
public class HibernateAuthorDao extends HibernateCrudDao<Author,String> implements AuthorDao{

    @Autowired
    public HibernateAuthorDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }


}
