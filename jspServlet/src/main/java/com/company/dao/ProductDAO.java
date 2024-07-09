package com.company.dao;

import com.company.connector.DBConnector;
import com.company.dto.ProductDTO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setPno(rs.getInt("pno"));
                product.setCate(rs.getString("cate"));
                product.setPname(rs.getString("pname"));
                product.setPcontent(rs.getString("pcontent"));
                product.setImg1(rs.getString("img1"));
                product.setImg2(rs.getString("img2"));
                product.setImg3(rs.getString("img3"));
                product.setResdate(rs.getTimestamp("resdate").toLocalDateTime());
                product.setHits(rs.getInt("hits"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductDTO getProductByNo(int pno) {
        String sql = "SELECT * FROM product WHERE pno = ?";
        ProductDTO product = null;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new ProductDTO();
                    product.setPno(rs.getInt("pno"));
                    product.setCate(rs.getString("cate"));
                    product.setPname(rs.getString("pname"));
                    product.setPcontent(rs.getString("pcontent"));
                    product.setImg1(rs.getString("img1"));
                    product.setImg2(rs.getString("img2"));
                    product.setImg3(rs.getString("img3"));
                    product.setResdate(rs.getTimestamp("resdate").toLocalDateTime());
                    product.setHits(rs.getInt("hits"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean insertProduct(ProductDTO product, InputStream img1Stream, InputStream img2Stream, InputStream img3Stream) {
        String sql = "INSERT INTO product (cate, pname, pcontent, img1, img2, img3, resdate, hits) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getCate());
            pstmt.setString(2, product.getPname());
            pstmt.setString(3, product.getPcontent());

            // Upload images to server
            String img1FileName = saveImage(img1Stream);
            String img2FileName = saveImage(img2Stream);
            String img3FileName = saveImage(img3Stream);

            pstmt.setString(4, img1FileName);
            pstmt.setString(5, img2FileName);
            pstmt.setString(6, img3FileName);
            pstmt.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(8, 0); // Initial hits

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String saveImage(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        Path tempFile = Files.createTempFile("img", ".jpg");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        return tempFile.getFileName().toString();
    }

    public boolean updateProduct(ProductDTO product, InputStream img1Stream, InputStream img2Stream, InputStream img3Stream) {
        String sql = "UPDATE product SET cate=?, pname=?, pcontent=?, img1=?, img2=?, img3=? WHERE pno=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getCate());
            pstmt.setString(2, product.getPname());
            pstmt.setString(3, product.getPcontent());

            // Upload images to server if provided
            if (img1Stream != null) {
                String img1FileName = saveImage(img1Stream);
                pstmt.setString(4, img1FileName);
            } else {
                pstmt.setString(4, product.getImg1());
            }

            if (img2Stream != null) {
                String img2FileName = saveImage(img2Stream);
                pstmt.setString(5, img2FileName);
            } else {
                pstmt.setString(5, product.getImg2());
            }

            if (img3Stream != null) {
                String img3FileName = saveImage(img3Stream);
                pstmt.setString(6, img3FileName);
            } else {
                pstmt.setString(6, product.getImg3());
            }

            pstmt.setInt(7, product.getPno());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int pno) {
        String sql = "DELETE FROM product WHERE pno = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pno);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}