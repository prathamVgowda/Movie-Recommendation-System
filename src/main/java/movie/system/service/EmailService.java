package movie.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationSuccessEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Registration Completed");
        message.setText("Hi " + username + ",\n\nYour registration is now complete and verified!\n\nWelcome aboard!");

        mailSender.send(message);
    }
    
    
    public void sendVerificationEmail(String toEmail, String username, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Email Verification Required");
        message.setText("Hi " + username + ",\n\nYour verification code is:\n" + verificationCode + 
            "\n\nPlease enter this code in the app to verify your email.\n\nThank you!");

        mailSender.send(message);
    }

}
