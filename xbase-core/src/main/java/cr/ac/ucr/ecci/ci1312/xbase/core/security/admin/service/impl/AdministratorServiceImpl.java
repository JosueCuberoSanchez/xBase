package cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.impl;


import cr.ac.ucr.ecci.ci1312.xbase.core.mail.UserMailHelper;
import cr.ac.ucr.ecci.ci1312.xbase.core.mail.UserMailHelperImpl;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import cr.ac.ucr.ecci.ci1312.xbase.support.SecurityUtils;
import cr.ac.ucr.ecci.ci1312.xbase.support.security.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.dao.AdministratorDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;

import java.util.List;

/**
 * Default implementation of the {@link AdministratorService}.
 *
 * @author Rodrigo A. Bartels
 */

@Service("administratorService")
@Transactional
public class AdministratorServiceImpl extends CrudServiceImpl<Administrator, String> implements AdministratorService {

    private static final Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    /**
     * Method that initializes the necessary fields during bean instantiation.
     */
    public void init(){
        setCrudDao(this.administratorDao);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException{
        UserDetails user = this.administratorDao.findUserByUsername(username.toLowerCase());
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public String create(Administrator admin) {
        Administrator foundAdmin = this.administratorDao.findUserByUsername(admin.getUsername().toLowerCase());
        if(foundAdmin != null)
            throw new IllegalArgumentException("User with given name already exists");

        admin.setEnabled(true);
        admin.setStatus(User.Status.ACTIVE);
        admin.setAdminID(admin.getAdminID().replaceAll("-", ""));
        admin.setUsername(admin.getUsername().toLowerCase());
        SecurityUtils.validatePassword( admin.getPassword() );
        admin.setPassword(this.passwordEncoder.encode(admin.getPassword()));
        return super.create(admin);
    }

    @Override
    public void update(Administrator entity) {
        this.administratorDao.update(entity);
    }

    @Override
    public void remove(Administrator entity) {
        this.administratorDao.remove(entity);
    }

    @Override
    public Administrator getAdminByUsername(String username) {
        return this.administratorDao.findUserByUsername(username);
    }

    @Override
    public void sendPasswordReset(String inputMail) {
        Administrator currentAdmin = this.administratorDao.findUserByUsername(inputMail);
        if (currentAdmin != null) {
            UserMailHelper mailHelper = new UserMailHelperImpl();
            String randomPassword = PasswordGenerator.generatePassword();
            mailHelper.sendResetPasswordEmail(currentAdmin, inputMail, randomPassword);
        }
    }

    @Override
    public List<Administrator> getAdminByName(String keyword) {
        return this.administratorDao.getAdminByName(keyword);
    }
}
