package com.rafantasy.userservice.common;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.rafantasy.userservice.common.Constants.POC_APP_NO_REPLY;


@Component
@RequiredArgsConstructor
public class Utils {

    private final JavaMailSender javaMailSender;

    /**
     * To send a mail when provided with the target email, the subject and the message.
     * @param userEmail The target email.
     * @param subject Subject of the mail.
     * @param message The mails content.
     */
    public void sendMail(String userEmail, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(userEmail);

        msg.setFrom(POC_APP_NO_REPLY);

        msg.setSubject(subject);

        msg.setText(message);

        javaMailSender.send(msg);

    }
}
