package com.company.dao;

import com.company.connector.DBConnector;
import com.company.dto.DataroomDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataroomDAO {

    public List<DataroomDTO> getAllData() {
        List<DataroomDTO> dataList = new ArrayList<>();
        String query = "SELECT * FROM dataroom";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                DataroomDTO data = new DataroomDTO();
                data.setDno(rs.getInt("dno"));
                data.setTitle(rs.getString("title"));
                data.setContent(rs.getString("content"));
                data.setAuthor(rs.getString("author"));
                data.setDatafile(rs.getString("datafile"));
                data.setResdate(rs.getTimestamp("resdate").toLocalDateTime());

                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public DataroomDTO getDataByNo(int no) {
        DataroomDTO data = null;
        String query = "SELECT * FROM dataroom WHERE dno = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    data = new DataroomDTO();
                    data.setDno(rs.getInt("dno"));
                    data.setTitle(rs.getString("title"));
                    data.setContent(rs.getString("content"));
                    data.setAuthor(rs.getString("author"));
                    data.setDatafile(rs.getString("datafile"));
                    data.setResdate(rs.getTimestamp("resdate").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean insertData(DataroomDTO data) {
        String query = "INSERT INTO dataroom (title, content, author, datafile, resdate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, data.getTitle());
            pstmt.setString(2, data.getContent());
            pstmt.setString(3, data.getAuthor());
            pstmt.setString(4, data.getDatafile());
            pstmt.setTimestamp(5, Timestamp.valueOf(data.getResdate()));

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateData(DataroomDTO data) {
        String query = "UPDATE dataroom SET title = ?, content = ?, author = ?, datafile = ?, resdate = ? WHERE dno = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, data.getTitle());
            pstmt.setString(2, data.getContent());
            pstmt.setString(3, data.getAuthor());
            pstmt.setString(4, data.getDatafile());
            pstmt.setTimestamp(5, Timestamp.valueOf(data.getResdate()));
            pstmt.setInt(6, data.getDno());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    public boolean deleteData(int dno) {
        String query = "DELETE FROM dataroom WHERE dno = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             pstmt.setInt(1, dno);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    
}   