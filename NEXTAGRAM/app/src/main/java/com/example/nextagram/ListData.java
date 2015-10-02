package com.example.nextagram;

public class ListData {

	private String writerName;
	private String articleTitle;
	private String articleBody;
	private String articleImageName;
	
	public ListData(String writerName, String articleTitle, String articleBody, String articleImageName){
		this.writerName = writerName;
		this.articleTitle = articleTitle;
		this.articleBody = articleBody;
		this.articleImageName = articleImageName;
	}

	public String getArticleWriter() {
		return writerName;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public String getArticleBody() {
		return articleBody;
	}

	public String getArticleImageName() {
		return articleImageName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}

	public void setArticleImageName(String articleImageName) {
		this.articleImageName = articleImageName;
	}
	
}
