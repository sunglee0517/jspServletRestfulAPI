package com.company.dto;

import java.time.LocalDateTime;

public class ProductDTO {
    private int pno;
    private String cate;
    private String pname;
    private String pcontent;
    private String img1;
    private String img2;
    private String img3;
    private LocalDateTime resdate; 
    private int hits;
	public ProductDTO() {
		super();
	}
	public ProductDTO(int pno, String cate, String pname, String pcontent, String img1, String img2, String img3,
			LocalDateTime resdate, int hits) {
		super();
		this.pno = pno;
		this.cate = cate;
		this.pname = pname;
		this.pcontent = pcontent;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.resdate = resdate;
		this.hits = hits;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public LocalDateTime getResdate() {
		return resdate;
	}
	public void setResdate(LocalDateTime resdate) {
		this.resdate = resdate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "ProductDTO [pno=" + pno + ", cate=" + cate + ", pname=" + pname + ", pcontent=" + pcontent + ", img1="
				+ img1 + ", img2=" + img2 + ", img3=" + img3 + ", resdate=" + resdate + ", hits=" + hits + "]";
	}
}
