package com.company.controller;

import com.company.dao.QnaDAO;
import com.company.dto.QnaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/qna/*")
public class QnaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private QnaDAO qnaDAO;

    public void init() {
        qnaDAO = new QnaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/list":
                qnaList(request, response);
                break;
            case "/detail":
                qnaDetail(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void qnaList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<QnaDTO> qnaList = qnaDAO.getQnaList();
        if (qnaList != null && !qnaList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"qnaList\":" + mapper.writeValueAsString(qnaList) + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "QnA not found");
        }
    }

    private void qnaDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int qno = Integer.parseInt(request.getParameter("qno"));
        QnaDTO qna = qnaDAO.getQnaDetail(qno);
        if (qna != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"qna\":" + mapper.writeValueAsString(qna) + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "QnA not found");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/insert":
                questionInsert(request, response);
                break;
            case "/answer":
                answerInsert(request, response);
                break;
            case "/edit":
                qnaEdit(request, response);
                break;
            case "/delete":
                qnaDelete(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void questionInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QnaDTO qna = new QnaDTO();
        qna.setTitle(request.getParameter("title"));
        qna.setContent(request.getParameter("content"));
        qna.setAuthor(request.getParameter("author"));

        boolean result = qnaDAO.questionInsert(qna);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Insert successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Insert failed");
        }
    }

    private void answerInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QnaDTO qna = new QnaDTO();
        qna.setParno(Integer.parseInt(request.getParameter("parno")));
        qna.setContent(request.getParameter("content"));
        qna.setAuthor(request.getParameter("author"));

        boolean result = qnaDAO.answerInsert(qna);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Answer insert successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Answer insert failed");
        }
    }

    private void qnaEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        QnaDTO qna = new QnaDTO();
        qna.setQno(Integer.parseInt(request.getParameter("qno")));
        qna.setTitle(request.getParameter("title"));
        qna.setContent(request.getParameter("content"));
        qna.setAuthor(request.getParameter("author"));

        boolean result = qnaDAO.qnaEdit(qna);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Edit successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Edit failed");
        }
    }

    private void qnaDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int qno = Integer.parseInt(request.getParameter("qno"));
        int lev = Integer.parseInt(request.getParameter("lev"));
        boolean result = qnaDAO.qnaDelete(qno, lev);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Delete successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Delete failed");
        }
    }
}