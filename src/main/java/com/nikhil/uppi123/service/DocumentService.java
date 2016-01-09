package com.nikhil.uppi123.service;

import java.util.ArrayList;
import java.util.List;

import org.carrot2.core.Document;
import org.carrot2.core.LanguageCode;
import org.springframework.stereotype.Service;

import com.buzzilla.webhose.client.WebhosePost;

/**
 * 
 * @author Uppi123
 * Date: 30 - Nov - 2015
 * CMPE 239 - Web And Data Mining
 * San Jose State University
 * 
 */

@Service
public class DocumentService {

	public List<Document> getDocuments(List<WebhosePost> results) {
		/*
		 * Returns list of objects of type Document belonging to Carrot's core
		 * required for using carrot2 library to generate clusters
		 */
		List<Document> documents = new ArrayList<Document>();
		Document doc = null;
		for (WebhosePost post : results) {
			doc = new Document();
			doc.setContentUrl(post.url);
			doc.setLanguage(LanguageCode.ENGLISH);
			doc.setTitle(post.title);
			doc.setSummary(post.text);
			documents.add(doc);
		}
		return documents;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
