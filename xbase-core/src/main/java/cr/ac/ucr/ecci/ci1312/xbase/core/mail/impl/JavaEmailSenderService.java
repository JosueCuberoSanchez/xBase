package cr.ac.ucr.ecci.ci1312.xbase.core.mail.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;

import cr.ac.ucr.ecci.ci1312.xbase.core.mail.EmailSenderService;
import cr.ac.ucr.ecci.ci1312.xbase.model.EmailMessage;

/**
 * Implementation of the EmailSender Service.
 * It sends an {@link EmailMessage} through the Java Mail API.
 *
 * @author Rodrigo A. Bartels
 */
@Transactional
@Service("emailSenderService")
public class JavaEmailSenderService implements EmailSenderService {

    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${xbase.support.notificationName}")
    private String notificationName;

    @Autowired
    private JavaMailSender javaMailSender;

    public JavaMailSender getJavaMailSender(){
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(final EmailMessage emailMessage){
        if(!emailMessage.isValid()){
            logger.error("Trying to send email to user without username");
            return;
        }

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setFrom(emailMessage.getFrom(), notificationName);
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getMessage(), true);
        };
        try{
            this.javaMailSender.send(preparator);
        }catch(Exception e){
            logger.error("Error sending email to {}", emailMessage.getTo(), e);
        }
    }

    public void sendEmail(final EmailMessage emailMessage, final File... attachments){
        Assert.isTrue(emailMessage.isValid(), "emailMessage object was not properly created before trying to send " +
                "email.");

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setFrom(emailMessage.getFrom(), notificationName);
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getMessage(), true);
            for(File attachment : attachments){
                mimeMessageHelper.addAttachment(attachment.getName(), attachment);
            }
        };
        try{
            this.javaMailSender.send(preparator);
        }catch(Exception e){
            logger.error("Error sending email to {}", emailMessage.getTo(), e);
        }
    }
}