package cr.ac.ucr.ecci.ci1312.xbase.core.mail;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

import cr.ac.ucr.ecci.ci1312.xbase.model.EmailMessage;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;

/**
 * Helper class for sending emails to {@code Tawker}s.
 *
 * @author Rodrigo Bartels
 * @version 1.0
 * @since 1/16/12
 */
@Component("userMailHelper")
public class UserMailHelperImpl implements UserMailHelper{

    private static final Logger logger = LoggerFactory.getLogger(UserMailHelperImpl.class);
    
    private static final String RESET_PASSWORD_SUBJECT_KEY = "xBase.security.password.reset.subject";
    private static final String RESET_PASSWORD_TEMPLATE = "reset-password.vm";

    
    private static final String MAP_PASSWORD_KEY = "password";
    private static final String MAP_USER_NAME_KEY = "username";

    @Value("${xBase.support.notificationEmail}")
    protected String notificationEmail;


    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private MessageSource messageSource;

    /**
     * Sends the reset password email to a user.
     *
     * @param user        the {@code user} whose password was reset
     * @param userName    the {@code user} first name
     * @param newPassword the new user password
     */
    @Override
    public void sendResetPasswordEmail(User user, String userName, String newPassword){
        EmailMessage message = new EmailMessage();
        message.setFrom(this.notificationEmail);
        message.setTo(user.getUsername());
        message.setSubject("Password Reset");
        //message.setSubject(this.messageSource.getMessage(RESET_PASSWORD_SUBJECT_KEY, null, null));

        Map<String, Object> model = new HashMap<>();
        model.put(MAP_USER_NAME_KEY, userName);
        model.put(MAP_PASSWORD_KEY, newPassword);
        
        try{
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, RESET_PASSWORD_TEMPLATE,
                    "UTF-8", model);

            if(StringUtils.isNotBlank(text)){
                message.setMessage(text);
                logger.debug("Password Reset email sent to {}", user.getUsername());
                emailSenderService.sendEmail(message);
            }
        }catch(VelocityException e){
            logger.error("Problem loading Velocity template {}", RESET_PASSWORD_TEMPLATE);
        }catch(Exception e){
            logger.error("Email notification wasn't send to {} \n{}", message.getTo(), e);
        }
    }
}

