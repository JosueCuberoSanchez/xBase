package cr.ac.ucr.ecci.ci1312.xbase.core.security.student.dao;

import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.CrudDao;

import java.util.List;

/**
 * Data access object for all the {@link Student} related operations.
 *
 * @author Rodrigo A. Bartels
 */

public interface StudentDao extends CrudDao<Student, String> {

    /**
     * Retrieves the user with the given username.
     *
     * @param username the user's unique identification.
     *
     * @return the User with the given id.
     */
    Student findUserByUsername(String username);

    List<Student> getAllStudents();

    List<Student> getStudentsByName(String keyword);




}
