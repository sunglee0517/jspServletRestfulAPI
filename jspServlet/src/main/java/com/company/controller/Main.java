package com.company.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/jspServlet")
public class Main extends HttpServlet {

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "천재 김기태";

        ServletContext application = request.getServletContext();
        String realPath = request.getSession().getServletContext().getRealPath("/");

        application.setAttribute("realPath", realPath);  //${realPath }
        
        /*
        ProductDAO dao = new ProductDAO();
        List<ProductDTO> bestList = dao.getBestProductList();
        List<ProductDTO> newList = dao.getNewProductList();

        request.setAttribute("bestList", bestList);
        request.setAttribute("newList", newList);
        
    */
        
        request.setAttribute("user", user);
        

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
        view.forward(request, response);
        //Resolve = dispatch+forward
    }
}