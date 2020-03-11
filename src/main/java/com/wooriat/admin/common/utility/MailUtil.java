package com.wooriat.admin.common.utility;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
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

            Properties p = new Properties();
            p.setProperty("resource.loader", "class");
            p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            Velocity.init( p );

            Template template = Velocity.getTemplate("templates/"+templateFileName+".vm", "UTF-8");

            //Template template = velocityEngine.getTemplate("templates/"+templateFileName+".vm", "UTF-8");

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
