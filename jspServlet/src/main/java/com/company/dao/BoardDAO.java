package com.company.dao;

import com.company.connector.DBConnector;
import com.company.dto.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boards = new ArrayList<>();
        String sql = "SELECT * FROM board";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setNo(rs.getInt("no"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setAuthor(rs.getString("author"));
                board.setResdate(rs.getTimestamp("resdate"));
                board.setHits(rs.getInt("hits"));
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return boards;
    }

    // Other CRUD methods with close() added
    public BoardDTO getBoardByNo(int no) {
        String sql = "SELECT * FROM board WHERE no = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setNo(rs.getInt("no"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setAuthor(rs.getString("author"));
                board.setResdate(rs.getTimestamp("resdate"));
                board.setHits(rs.getInt("hits"));
                return board;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return null;
    }

    public boolean insertBoard(BoardDTO board) {
        String sql = "INSERT INTO board (title, content, author, resdate, hits) VALUES (?, ?, ?, NOW(), 0)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getAuthor());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean updateBoard(BoardDTO board) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE no = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setInt(3, board.getNo());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean deleteBoard(int no) {
        String sql = "DELETE FROM board WHERE no = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }
}
