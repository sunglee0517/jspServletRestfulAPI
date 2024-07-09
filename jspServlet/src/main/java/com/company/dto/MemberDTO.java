package com.company.dto;

import java.sql.Timestamp;

public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String birth;
    private String email;
    private String addr1;
    private String addr2;
    private String postcode;
    private Timestamp regdate;

    public MemberDTO() {
        super();
    }

    public MemberDTO(String id, String pw, String name, String birth, String email, String addr1, String addr2,
                     String postcode, Timestamp regdate) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.postcode = postcode;
        this.regdate = regdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", email=" + email
                + ", addr1=" + addr1 + ", addr2=" + addr2 + ", postcode=" + postcode + ", regdate=" + regdate + "]";
    }
}