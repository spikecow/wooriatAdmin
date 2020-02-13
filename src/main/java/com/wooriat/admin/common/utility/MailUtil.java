package com.wooriat.admin.common.utility;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender javaMailSender;

    private final VelocityEngine velocityEngine;

    public void sendMail(String toEmail, String fromEmail, String subject, String content, String templateYn, VelocityContext velocityContext, String templateFileName) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        if(templateYn != null && templateYn.equals("Y")){
            Template template = velocityEngine.getTemplate("/src/main/webapp/templates/"+templateFileName+".vm");

            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);

            content = stringWriter.toString();
        }

        message.setSubject(subject);
        message.setText(content, "UTF-8", "html");
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress( toEmail )); //TO

        javaMailSender.send(message);

    }

}
