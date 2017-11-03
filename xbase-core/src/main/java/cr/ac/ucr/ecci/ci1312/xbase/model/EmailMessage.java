package cr.ac.ucr.ecci.ci1312.xbase.model;

import org.springframework.util.StringUtils;

/**
 * Class that represents an Email message within the application.
 *
 * @author Rodrigo A. Bartels
 * @version 1.0
 * @since 2/17/12
 */
public class EmailMessage {

    /**
     * The destination of the Message.
     */
    private String to;

    /**
     * The author of the Message.
     */
    private String from;

    /**
     * The subject of the Message.
     */
    private String subject;

    /**
     * The body of the Message.
     */
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns whether or not the email message represented by this object
     * instance is valid.
     * Validity for a Message is defined as having all fields populated.
     *
     * @return true if the Message represented by this instance is valid,
     * false otherwise.
     */
    public boolean isValid() {
        return isValidMessage(this);
    }

    /**
     * Utility method that checks to see if the specified Message is valid.
     * Validity for a Message is defined as having all fields populated.
     *
     * @param emailMessage the EmailMessage to test for validity.
     * @return true if the given EmailMessage is valid according to rules
     * noted above, false otherwise.
     */
    public static boolean isValidMessage(EmailMessage emailMessage) {
        return (emailMessage != null) &&
                StringUtils.hasText(emailMessage.getFrom()) &&
                StringUtils.hasText(emailMessage.getTo()) &&
                StringUtils.hasText(emailMessage.getSubject()) &&
                StringUtils.hasText(emailMessage.getMessage());
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


}
