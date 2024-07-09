package com.company.controller;

import com.company.dao.ProductDAO;
import com.company.dto.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products/*")
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;

    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "/list":
                getAllProducts(request, response);
                break;
            case "/detail":
                getProductByNo(request, response);
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
                insertProduct(request, response);
                break;
            case "/update":
                updateProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void getAllProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ProductDTO> products = productDAO.getAllProducts();
        if (products != null && !products.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"products\":" + products.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Products not found");
        }
    }

    private void getProductByNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pno = Integer.parseInt(request.getParameter("pno"));
        ProductDTO product = productDAO.getProductByNo(pno);
        if (product != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"product\":" + product.toString() + "}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDTO product = new ProductDTO();
        product.setCate(request.getParameter("cate"));
        product.setPname(request.getParameter("pname"));
        product.setPcontent(request.getParameter("pcontent"));

        InputStream img1Stream = request.getPart("img1").getInputStream();
        InputStream img2Stream = request.getPart("img2").getInputStream();
        InputStream img3Stream = request.getPart("img3").getInputStream();

        boolean result = productDAO.insertProduct(product, img1Stream, img2Stream, img3Stream);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Insert successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Insert failed");
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDTO product = new ProductDTO();
        product.setPno(Integer.parseInt(request.getParameter("pno")));
        product.setCate(request.getParameter("cate"));
        product.setPname(request.getParameter("pname"));
        product.setPcontent(request.getParameter("pcontent"));

        InputStream img1Stream = request.getPart("img1").getInputStream();
        InputStream img2Stream = request.getPart("img2").getInputStream();
        InputStream img3Stream = request.getPart("img3").getInputStream();

        boolean result = productDAO.updateProduct(product, img1Stream, img2Stream, img3Stream);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"message\":\"Update successful\"}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pno = Integer.parseInt(request.getParameter("pno"));
        boolean result = productDAO.deleteProduct(pno);
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