package com.nikhil.uppi123.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Uppi123
 * Date: 30 - Nov - 2015
 * CMPE 239 - Web And Data Mining
 * San Jose State University
 * 
 */

public class ResponseObject {
	/*
	 * Controller's response object POJO - sent as JSON 
	 * in HTTP response body
	 */
	private String clusterLabel;
	private List<NewsArticle> results = new ArrayList<NewsArticle>();

	public String getClusterLabel() {
		return clusterLabel;
	}

	public void setClusterLabel(String clusterLabel) {
		this.clusterLabel = clusterLabel;
	}

	public void addResult(NewsArticle result) {
		results.add(result);
	}
	
	public List<NewsArticle> getResults() {
		List<NewsArticle> res = new ArrayList<NewsArticle>();
		for (NewsArticle n : results) {
			res.add(n);
		}
		return res;
	}
}
