package cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.dao;

import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.CrudDao;

import java.util.List;

/**
 * Data access object for all the {@link} related operations.
 *
 * @author Rodrigo A. Bartels
 */

public interface AdministratorDao extends CrudDao<Administrator, String> {

    /**
     * Retrieves the user with the given username.
     *
     * @param username the user's unique identification.
     *
     * @return the User with the given id.
     */
    Administrator findUserByUsername(String username);

    List<Administrator> getAllAdministrators();

    List<Administrator> getAdminByName(String keyword);
}
