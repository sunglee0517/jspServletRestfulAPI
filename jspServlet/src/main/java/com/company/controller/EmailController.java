package com.company.controller;

import com.company.util.EmailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/email/send")
public class EmailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipientEmail = request.getParameter("recipientEmail");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        try {
            EmailUtil.sendEmail(recipientEmail, subject, message);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Email sent successfully.");
        } catch (MessagingException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to send email: " + e.getMessage());
        }
    }
}