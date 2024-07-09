package com.company.dto;

import java.sql.Timestamp;

public class BoardDTO {
    private int no;
    private String title;
    private String content;
    private String author;
    private Timestamp resdate;
    private int hits;
    
	public BoardDTO() {
		super();
	}
	public BoardDTO(int no, String title, String content, String author, Timestamp resdate, int hits) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.author = author;
		this.resdate = resdate;
		this.hits = hits;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getResdate() {
		return resdate;
	}
	public void setResdate(Timestamp resdate) {
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
		return "BoardDTO [no=" + no + ", title=" + title + ", content=" + content + ", author=" + author + ", resdate="
				+ resdate + ", hits=" + hits + "]";
	}
}