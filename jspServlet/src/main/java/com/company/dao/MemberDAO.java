package com.company.dao;

import com.company.connector.DBConnector;
import com.company.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public MemberDTO login(String id, String pw) {
        String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MemberDTO(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("email"),
                        rs.getString("addr1"),
                        rs.getString("addr2"),
                        rs.getString("postcode"),
                        rs.getTimestamp("regdate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return null;
    }

    public boolean join(MemberDTO member) {
        String sql = "INSERT INTO member (id, pw, name, birth, email, addr1, addr2, postcode, regdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getBirth());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getAddr1());
            pstmt.setString(7, member.getAddr2());
            pstmt.setString(8, member.getPostcode());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean updateMember(MemberDTO member) {
        String sql = "UPDATE member SET pw = ?, name = ?, birth = ?, email = ?, addr1 = ?, addr2 = ?, postcode = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPw());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getBirth());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getAddr1());
            pstmt.setString(6, member.getAddr2());
            pstmt.setString(7, member.getPostcode());
            pstmt.setString(8, member.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public boolean deleteMember(String id) {
        String sql = "DELETE FROM member WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(pstmt, conn);
        }
        return false;
    }

    public MemberDTO getMember(String id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MemberDTO(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("email"),
                        rs.getString("addr1"),
                        rs.getString("addr2"),
                        rs.getString("postcode"),
                        rs.getTimestamp("regdate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return null;
    }

    public List<MemberDTO> getMemberList() {
        List<MemberDTO> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDTO member = new MemberDTO(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("birth"),
                        rs.getString("email"),
                        rs.getString("addr1"),
                        rs.getString("addr2"),
                        rs.getString("postcode"),
                        rs.getTimestamp("regdate")
                );
                memberList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnector.close(rs, pstmt, conn);
        }
        return memberList;
    }
}