package cr.ac.ucr.ecci.ci1312.xbase.core.mail;

import cr.ac.ucr.ecci.ci1312.xbase.model.User;

/**
 * Interface for all the User Mail Helper implementations.
 *
 * @author Rodrigo Bartels
 * @version 1.0
 * @since 4/6/12
 */
public interface UserMailHelper {

    /**
     * Sends an email to a user that reset his password.
     *
     * @param user        the user
     * @param newPassword the new password
     */
    void sendResetPasswordEmail(User user, String userName, String newPassword);

    
}
