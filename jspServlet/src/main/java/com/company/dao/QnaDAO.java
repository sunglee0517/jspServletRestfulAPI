package com.company.dao;

import com.company.connector.DBConnector;
import com.company.dto.QnaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QnaDAO {

    public List<QnaDTO> getQnaList() {
        List<QnaDTO> qnaList = new ArrayList<>();
        String sql = "SELECT * FROM qna";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                QnaDTO qna = new QnaDTO();
                qna.setQno(rs.getInt("qno"));
                qna.setLev(rs.getInt("lev"));
                qna.setParno(rs.getInt("parno"));
                qna.setTitle(rs.getString("title"));
                qna.setContent(rs.getString("content"));
                qna.setAuthor(rs.getString("author"));
                qna.setResdate(rs.getTimestamp("resdate").toLocalDateTime());
                qna.setHits(rs.getInt("hits"));
                qnaList.add(qna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return qnaList;
    }

    public QnaDTO getQnaDetail(int qno) {
        String sql = "SELECT * FROM qna WHERE qno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                QnaDTO qna = new QnaDTO();
                qna.setQno(rs.getInt("qno"));
                qna.setLev(rs.getInt("lev"));
                qna.setParno(rs.getInt("parno"));
                qna.setTitle(rs.getString("title"));
                qna.setContent(rs.getString("content"));
                qna.setAuthor(rs.getString("author"));
                qna.setResdate(rs.getTimestamp("resdate").toLocalDateTime());
                qna.setHits(rs.getInt("hits"));
                return qna;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return null;
    }

    public boolean questionInsert(QnaDTO qna) {
        String sql = "INSERT INTO qna (lev, parno, title, content, author, resdate, hits) VALUES (0, ?, ?, ?, ?, NOW(), 0)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qna.getParno());
            pstmt.setString(2, qna.getTitle());
            pstmt.setString(3, qna.getContent());
            pstmt.setString(4, qna.getAuthor());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean answerInsert(QnaDTO qna) {
        String sql = "INSERT INTO qna (lev, parno, title, content, author, resdate, hits) VALUES (1, ?, ?, ?, ?, NOW(), 0)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qna.getParno());
            pstmt.setString(2, qna.getTitle());
            pstmt.setString(3, qna.getContent());
            pstmt.setString(4, qna.getAuthor());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean qnaEdit(QnaDTO qna) {
        String sql = "UPDATE qna SET title = ?, content = ? WHERE qno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, qna.getTitle());
            pstmt.setString(2, qna.getContent());
            pstmt.setInt(3, qna.getQno());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean qnaDelete(int qno, int lev) {
        String sql;
        if (lev == 0) { // Question
            sql = "DELETE FROM qna WHERE parno = ? OR qno = ?";
        } else { // Answer
            sql = "DELETE FROM qna WHERE qno = ?";
        }
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qno);
            if (lev == 0) {
                pstmt.setInt(2, qno);
            }

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }
}
