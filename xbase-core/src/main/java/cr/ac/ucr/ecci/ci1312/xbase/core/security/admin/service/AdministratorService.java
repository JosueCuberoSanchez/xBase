package cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link Administrator} entities.
 *
 * @author Rodrigo A. Bartels
 */
public interface AdministratorService extends CrudService<Administrator, String>, UserDetailsService{
    Administrator getAdminByUsername(String username);

    void sendPasswordReset(String inputMail);
    List<Administrator> getAdminByName(String keyword);
}
