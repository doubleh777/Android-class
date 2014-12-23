package com.example.news;

public class Article {

	private int articleNumber;
	private String title;
	private String writer;
	private String writeDate;
	private String imgName;
	
	public Article(int articleNumber, String title, String writer, String writeDate, String imgName){
		this.articleNumber = articleNumber;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.imgName = imgName;
	}
}
