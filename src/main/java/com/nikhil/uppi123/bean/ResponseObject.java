package com.nikhil.uppi123.bean;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {

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
