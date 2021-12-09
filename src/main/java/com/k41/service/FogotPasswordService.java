package com.k41.service;

import com.k41.entity.NguoiDung;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.UUID;

@Service
@Slf4j
public class FogotPasswordService {
    @Autowired
    Environment environment;
    @Autowired
    private NguoiDungService nguoiDungService;
    private String port;
    private String hostname;
    private final String fromEmail = "thithom.12082000@gmail.com";
    private final String password = "wujgqcawtqmmkctd";
    final String subject = "Quên Mật Khẩu";

    public void sendEmail(NguoiDung nguoiDung) throws MessagingException, UnknownHostException {
        nguoiDung.setActiveKey(UUID.randomUUID().toString());
        nguoiDung = nguoiDungService.save(nguoiDung);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(nguoiDung.getEmail(), false));
        message.setSubject(subject);
        String htmlContent = "<h1>" +
                "<a href=\"" + getServerUrlPrefi() +"quen-mat-khau/"+ nguoiDung.getActiveKey().toString() + "\">" +
                "Click vào link để đặt lại mật khẩu</a>" +
                "</h1>";
        message.setContent(htmlContent, "text/html;charset=UTF-8");
        log.info(htmlContent);
        Transport.send(message);
    }


    private String getPort() {
        if (port == null) port = environment.getProperty("local.server.port");
        return port;
    }

    private String getHostname() throws UnknownHostException {
        if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
        return hostname;
    }

    private String getServerUrlPrefi() throws UnknownHostException {
        return "http://" + getHostname() + ":" + getPort()+"/";
    }

}
