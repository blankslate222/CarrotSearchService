package com.nikhil.uppi123.service;

import java.util.List;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.springframework.stereotype.Service;

@Service
public class CarrotService {

	public List<Cluster> doCluster(List<Document> documents) {

		Controller controller = ControllerFactory.createSimple();

		ProcessingResult byTopicClusters = controller.process(documents,
				"data mining", LingoClusteringAlgorithm.class);

		List<Cluster> clustersByTopic = byTopicClusters.getClusters();

		System.out.println("by topic - num of clusters = "
				+ clustersByTopic.size());
		for (Cluster c : clustersByTopic) {
			System.out.println("Cluster Label = " + c.getLabel());
			System.out.println("Documents in this cluster:");
			List<Document> docs = c.getAllDocuments();
			for (Document doc : docs)
				System.out.println(doc.getTitle());
		}

		return clustersByTopic;
	}

}
