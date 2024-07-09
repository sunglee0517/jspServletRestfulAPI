package com.company.dto;

import java.time.LocalDateTime;

public class DataroomDTO {
    private int dno;
    private String title;
    private String content;
    private String author;
    private String datafile;
    private LocalDateTime resdate;
    private int hits;
	public DataroomDTO() {
		super();
	}
	public DataroomDTO(int dno, String title, String content, String author, String datafile, LocalDateTime resdate,
			int hits) {
		super();
		this.dno = dno;
		this.title = title;
		this.content = content;
		this.author = author;
		this.datafile = datafile;
		this.resdate = resdate;
		this.hits = hits;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
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
	public String getDatafile() {
		return datafile;
	}
	public void setDatafile(String datafile) {
		this.datafile = datafile;
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
		return "DataroomDTO [dno=" + dno + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", datafile=" + datafile + ", resdate=" + resdate + ", hits=" + hits + "]";
	}
}
