package cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.impl;


import cr.ac.ucr.ecci.ci1312.xbase.core.mail.UserMailHelper;
import cr.ac.ucr.ecci.ci1312.xbase.core.mail.UserMailHelperImpl;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.dao.StudentDao;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import cr.ac.ucr.ecci.ci1312.xbase.support.SecurityUtils;
import cr.ac.ucr.ecci.ci1312.xbase.support.security.PasswordGenerator;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.impl.CrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link StudentService}.
 *
 * @author Rodrigo A. Bartels
 */

@Service
@Transactional
public class StudentServiceImpl extends CrudServiceImpl<Student, String> implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    /**
     * Method that initializes the necessary fields during bean instantiation.
     */
    public void init(){
        setCrudDao(this.studentDao);
    }


    @Override
    public List<Student> getAllStudents() {
        return this.studentDao.getAllStudents();
    }

    @Override
    public List<Student> getStudentsByName(String keyword) {
        return this.studentDao.getStudentsByName(keyword);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException{
        UserDetails user = this.studentDao.findUserByUsername(username.toLowerCase());
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Override
    public String create(Student student) {
        Student retrievedStudent = this.studentDao.findUserByUsername(student.getUsername().toLowerCase());
        if(retrievedStudent != null)
            throw new IllegalArgumentException("User with the given username already exists");

        student.setEnabled(true);
        student.setStatus(User.Status.ACTIVE);
        student.setUsername(student.getUsername().toLowerCase());
        SecurityUtils.validatePassword( student.getPassword() );
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return super.create(student);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return this.studentDao.findUserByUsername(username);
    }

    @Override
    public void sendPasswordReset(String inputMail) {
        Student currentStudent = this.studentDao.findUserByUsername(inputMail);
        if (currentStudent != null) {
            UserMailHelper mailHelper = new UserMailHelperImpl();
            String randomPassword = PasswordGenerator.generatePassword();
            mailHelper.sendResetPasswordEmail(currentStudent, inputMail, randomPassword);
        }
    }


}
