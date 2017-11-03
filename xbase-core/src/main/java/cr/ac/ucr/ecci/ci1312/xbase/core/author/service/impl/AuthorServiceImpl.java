package cr.ac.ucr.ecci.ci1312.xbase.core.author.service.impl;


import cr.ac.ucr.ecci.ci1312.xbase.core.author.dao.AuthorDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.author.service.AuthorService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Author;
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
@Service("authorService")
@Transactional
public class AuthorServiceImpl extends CrudServiceImpl<Author, String> implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    public void init(){
        setCrudDao(this.authorDao);
    }
}
