package cr.ac.ucr.ecci.ci1312.xbase.core.doubt.dao;

import cr.ac.ucr.ecci.ci1312.xbase.model.Doubt;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.CrudDao;

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
public interface DoubtDao extends CrudDao<Doubt, String> {
    List<Doubt> getOrderedDoubts();
    List<Doubt> getUnresolvedDoubts();
    List<Doubt> getUnresolvedOrderedDoubts();
}
