package com.company.controller;

import com.company.dao.DataroomDAO;
import com.company.dto.DataroomDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/dataroom/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DataroomController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private DataroomDAO dataroomDAO;

    public void init() {
        dataroomDAO = new DataroomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/list":
                getDataList(request, response);
                break;
            case "/detail":
                getData(request, response);
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
            case "/upload":
                upload(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void getDataList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DataroomDTO> dataList = dataroomDAO.getAllData();
        if (dataList != null && !dataList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"dataList\":" + dataList.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Data not found");
        }
    }

    private void getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        DataroomDTO data = dataroomDAO.getDataByNo(no);
        if (data != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"data\":" + data.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Data not found");
        }
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadPath + File.separator + fileName);

        DataroomDTO data = new DataroomDTO();
        data.setTitle(title);
        data.setContent(content);
        data.setAuthor(author);
        data.setDatafile(fileName);

        boolean result = dataroomDAO.insertData(data);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Upload successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Upload failed");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int no = Integer.parseInt(request.getParameter("dno"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        Part filePart = request.getPart("datafile");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadPath + File.separator + fileName);

        DataroomDTO data = new DataroomDTO();
        data.setDno(no);
        data.setTitle(title);
        data.setContent(content);
        data.setAuthor(author);
        data.setDatafile(fileName);

        boolean result = dataroomDAO.updateData(data);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Update successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int dno = Integer.parseInt(request.getParameter("dno"));
        boolean result = dataroomDAO.deleteData(dno);
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

