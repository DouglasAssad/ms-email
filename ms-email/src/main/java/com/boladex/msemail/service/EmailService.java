package com.boladex.msemail.service;

import com.boladex.msemail.enums.StatusEmail;
import com.boladex.msemail.models.EmailModel;
import com.boladex.msemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(emailModel);
        }
    }
}

