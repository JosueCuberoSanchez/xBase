package cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service;

import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import org.springframework.security.core.userdetails.UserDetailsService;

import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link Student} entities.
 *
 * @author Rodrigo A. Bartels
 */
public interface StudentService extends CrudService<Student, String>, UserDetailsService{
    List<Student> getAllStudents();

    List<Student> getStudentsByName(String keyword);

    Student getStudentByUsername(String username);

    void sendPasswordReset(String inputMail);
}
