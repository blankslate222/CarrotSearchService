package com.nikhil.uppi123.service;

import java.util.ArrayList;
import java.util.List;

import org.carrot2.core.Document;
import org.carrot2.core.LanguageCode;
import org.springframework.stereotype.Service;

import com.buzzilla.webhose.client.WebhosePost;

@Service
public class DocumentService {

	public List<Document> getDocuments(List<WebhosePost> results) {
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
