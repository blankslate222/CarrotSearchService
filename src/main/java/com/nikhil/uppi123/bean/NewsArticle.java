package com.nikhil.uppi123.bean;

/**
 * 
 * @author Uppi123
 * Date: 30 - Nov - 2015
 * CMPE 239 - Web And Data Mining
 * San Jose State University
 * 
 */

public class NewsArticle {
	/*
	 * POJO to store key value pair
	 * sent as part of web service's return value
	 * which is the ResponseObject
	 */
	private String url;
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
