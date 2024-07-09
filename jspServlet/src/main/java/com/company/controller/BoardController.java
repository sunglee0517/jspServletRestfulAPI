package com.company.controller;

import com.company.dao.BoardDAO;
import com.company.dto.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/boards/*")
public class BoardController extends HttpServlet {

    private BoardDAO boardDAO;

    public void init() {
        boardDAO = new BoardDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/list":
                getAllBoards(request, response);
                break;
            case "/detail":
                getBoardByNo(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/insert":
                insertBoard(request, response);
                break;
            case "/update":
                updateBoard(request, response);
                break;
            case "/delete":
                deleteBoard(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void getAllBoards(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<BoardDTO> boards = boardDAO.getAllBoards();
        if (boards != null && !boards.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"boards\":" + boards.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Boards not found");
        }
    }

    private void getBoardByNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        BoardDTO board = boardDAO.getBoardByNo(no);
        if (board != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"board\":" + board.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Board not found");
        }
    }

    private void insertBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BoardDTO board = new BoardDTO();
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setAuthor(request.getParameter("author"));

        boolean result = boardDAO.insertBoard(board);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Insert successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Insert failed");
        }
    }

    private void updateBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BoardDTO board = new BoardDTO();
        board.setNo(Integer.parseInt(request.getParameter("no")));
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setAuthor(request.getParameter("author"));

        boolean result = boardDAO.updateBoard(board);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Update successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
        }
    }

    private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        boolean result = boardDAO.deleteBoard(no);
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