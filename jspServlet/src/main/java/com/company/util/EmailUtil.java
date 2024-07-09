package com.company.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    private static final String HOST = "smtp.gmail.com"; // Gmail SMTP 서버
    private static final String USERNAME = "your_email@gmail.com"; // 발신자 이메일
    private static final String PASSWORD = "your_password"; // 발신자 이메일 비밀번호

    public static void sendEmail(String recipientEmail, String subject, String message) throws MessagingException {
        // SMTP 서버 설정
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 인증 정보 설정
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        // 메일 세션 생성
        Session session = Session.getInstance(props, auth);

        // 메일 송/수신 옵션 설정
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(USERNAME)); // 발신자 이메일 설정
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail)); // 수신자 이메일 설정
        mimeMessage.setSubject(subject); // 이메일 제목 설정
        mimeMessage.setText(message); // 이메일 내용 설정

        // 이메일 전송
        Transport.send(mimeMessage);
    }
}