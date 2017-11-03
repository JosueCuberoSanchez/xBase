package cr.ac.ucr.ecci.ci1312.xbase.core.mail;

import java.io.File;

import cr.ac.ucr.ecci.ci1312.xbase.model.EmailMessage;

/**
 * Interface for sending emails from the system.
 *
 * @author Rodrigo A. Bartels
 * @version 1.0
 * @since 8/5/11
 */
public interface EmailSenderService {

    /**
     * This method is used for sending a email.  A check will be made to see
     * if the given {@link EmailMessage emailMessage}
     * is valid.  If the <tt>emailMessage</tt> is not valid, then
     * an IllegalArgumentException will be thrown.
     *
     * @param emailMessage the message object to send.
     *
     * @throws IllegalArgumentException when emailMessage is not valid.
     * @see EmailMessage#isValid()
     */
    void sendEmail(EmailMessage emailMessage)
            throws IllegalArgumentException;

    /**
     * This method is used for sending a email.  A check will be made to see
     * if the given {@link EmailMessage emailMessage}
     * is valid.  If the <tt>emailMessage</tt> is not valid, then
     * an IllegalArgumentException will be thrown.
     *
     * @param emailMessage the message object to send.
     * @param attachments the files to attach to the email.
     *
     * @throws IllegalArgumentException when emailMessage is not valid.
     * @see EmailMessage#isValid()
     */
    void sendEmail(final EmailMessage emailMessage, final File... attachments);
}
