package com.nikhil.uppi123.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.buzzilla.webhose.client.WebhoseClient;
import com.buzzilla.webhose.client.WebhosePost;
import com.buzzilla.webhose.client.WebhoseQuery;
import com.buzzilla.webhose.client.WebhoseQuery.SiteType;
import com.buzzilla.webhose.client.WebhoseResponse;

/**
 * 
 * @author Uppi123
 * Date: 30 - Nov - 2015
 * CMPE 239 - Web And Data Mining
 * San Jose State University
 * 
 */

@Service
public class WebhoseService {
	/*
	 * Service class - uses webhose java sdk to
	 * retrieve search results and returns to the controller
	 * for further processing
	 */
	public List<WebhosePost> getSearchResults(String term, WebhoseClient client)
			throws IOException {
		WebhoseResponse resp = null;
		WebhoseQuery query = getQuery(term);
		System.out.println("querying to string = " + query.toString());
		resp = client.search(query);
		System.out.println("total results = " + resp.totalResults);
		System.out.println("size = " + resp.posts.size());
		// for (WebhosePost post : resp.posts) {
		// System.out.println(post);
		// }
		return resp.posts;
	}

	private WebhoseQuery getQuery(String term) {
		WebhoseQuery query = new WebhoseQuery();
		query.bodyText = term;
		query.language.add("english");
		query.siteTypes.add(SiteType.news);
		return query;
	}
	
}
