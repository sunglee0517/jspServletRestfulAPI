package com.company.controller;

import com.company.dao.MemberDAO;
import com.company.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/members/*")
public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberDAO memberDAO;

    public void init() {
        memberDAO = new MemberDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/mypage":
                mypage(request, response);
                break;
            case "/getMemberList":
                getMemberList(request, response);
                break;
            case "/getMember":
                getMember(request, response);
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
            case "/login":
                login(request, response);
                break;
            case "/join":
                join(request, response);
                break;
            case "/myInfoEdit":
                myInfoEdit(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        MemberDTO member = memberDAO.login(id, pw);
        if (member != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Login successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login failed");
        }
    }

    private void join(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberDTO member = new MemberDTO();
        member.setId(request.getParameter("id"));
        member.setPw(request.getParameter("pw"));
        member.setName(request.getParameter("name"));
        member.setBirth(request.getParameter("birth"));
        member.setEmail(request.getParameter("email"));
        member.setAddr1(request.getParameter("addr1"));
        member.setAddr2(request.getParameter("addr2"));
        member.setPostcode(request.getParameter("postcode"));

        boolean result = memberDAO.join(member);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Join successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Join failed");
        }
    }

    private void mypage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        MemberDTO member = memberDAO.getMember(id);
        if (member != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"member\":" + member.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
        }
    }

    private void myInfoEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberDTO member = new MemberDTO();
        member.setId(request.getParameter("id"));
        member.setPw(request.getParameter("pw"));
        member.setName(request.getParameter("name"));
        member.setBirth(request.getParameter("birth"));
        member.setEmail(request.getParameter("email"));
        member.setAddr1(request.getParameter("addr1"));
        member.setAddr2(request.getParameter("addr2"));
        member.setPostcode(request.getParameter("postcode"));

        boolean result = memberDAO.updateMember(member);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Update successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        boolean result = memberDAO.deleteMember(id);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Logout successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Logout failed");
        }
    }

    private void getMemberList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MemberDTO> memberList = memberDAO.getMemberList();
        if (memberList != null && !memberList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"members\":" + memberList.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Members not found");
        }
    }

    private void getMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        MemberDTO member = memberDAO.getMember(id);
        if (member != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"member\":" + member.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
        }
    }
}
