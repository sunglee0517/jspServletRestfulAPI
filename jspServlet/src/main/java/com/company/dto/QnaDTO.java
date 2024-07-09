package com.company.dto;

import java.time.LocalDateTime;

public class QnaDTO {
    private int qno;
    private int lev;
    private int parno;
    private String title;
    private String content;
    private String author;
    private LocalDateTime resdate;
    private int hits;
	public QnaDTO() {
		super();
	}
	public QnaDTO(int qno, int lev, int parno, String title, String content, String author, LocalDateTime resdate,
			int hits) {
		super();
		this.qno = qno;
		this.lev = lev;
		this.parno = parno;
		this.title = title;
		this.content = content;
		this.author = author;
		this.resdate = resdate;
		this.hits = hits;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getParno() {
		return parno;
	}
	public void setParno(int parno) {
		this.parno = parno;
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
		return "QnaDTO [qno=" + qno + ", lev=" + lev + ", parno=" + parno + ", title=" + title + ", content=" + content
				+ ", author=" + author + ", resdate=" + resdate + ", hits=" + hits + "]";
	}
    
}
