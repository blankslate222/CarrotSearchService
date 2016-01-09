package com.nikhil.uppi123.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.buzzilla.webhose.client.WebhoseClient;
import com.buzzilla.webhose.client.WebhosePost;
import com.nikhil.uppi123.bean.NewsArticle;
import com.nikhil.uppi123.bean.ResponseObject;
import com.nikhil.uppi123.service.CarrotService;
import com.nikhil.uppi123.service.DocumentService;
import com.nikhil.uppi123.service.WebhoseService;

/**
 * 
 * @author Uppi123
 * Date: 30 - Nov - 2015
 * CMPE 239 - Web And Data Mining
 * San Jose State University
 * 
 */
@Controller
public class CarrotController {
	/*
	 * Spring MVC web controller which handles the 
	 * web service requests and responses
	 */
	@Autowired
	private CarrotService carService;
	@Autowired
	private DocumentService docService;
	@Autowired
	private WebhoseService hoseService;

	@RequestMapping(value = "/search_cluster", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseObject>> clusterSearchResults(
			@RequestParam("term") String term) {

		ResponseEntity<List<ResponseObject>> response = null;
		WebhoseClient client = new WebhoseClient(
				"d72a236b-f6d9-4c52-b2d1-cceb9734579a");

		System.out.println("fetching search results for " + term + "...");
		try {
			List<WebhosePost> results = hoseService.getSearchResults(term,
					client);

			List<Document> docs = docService.getDocuments(results);

			List<Cluster> clusterList = carService.doCluster(docs);

			List<ResponseObject> resp = getResponse(clusterList);
			for (ResponseObject re : resp) {

			}
			response = new ResponseEntity<List<ResponseObject>>(resp,
					HttpStatus.OK);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	private List<ResponseObject> getResponse(List<Cluster> clusters) {
		ResponseObject obj = null;
		NewsArticle news = null;

		List<ResponseObject> response = new ArrayList<ResponseObject>();

		for (Cluster c : clusters) {
			obj = new ResponseObject();
			obj.setClusterLabel(c.getLabel());
			List<Document> docs = c.getDocuments();
			
			// map is used here to remove redundant results
			
			HashMap<String, NewsArticle> map = new HashMap<String, NewsArticle>();

			for (Document doc : docs) {
				if (doc.getContentUrl() == null
						|| "".equals(doc.getContentUrl().trim())
						|| "".equals(doc.getTitle().trim()) || doc == null) {
					continue;
				}
				news = new NewsArticle();
				news.setTitle(doc.getTitle().trim());
				news.setUrl(doc.getContentUrl().trim());
				map.put(news.getUrl().trim(), news);
				// obj.addResult(news);
			}

			for (NewsArticle article : map.values()) {
				obj.addResult(article);
			}
			response.add(obj);
		}

		return response;
	}
}
