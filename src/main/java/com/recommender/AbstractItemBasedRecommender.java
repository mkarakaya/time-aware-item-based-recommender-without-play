package com.recommender;

import com.recommender.data.Data;
import com.recommender.similarity.Similarity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * every item based recommender should extend this class and override methods when needed.
 * @author mokarakaya
 *
 */
abstract class AbstractItemBasedRecommender implements Recommender{
	final Similarity similarity;
	final Data data;

    AbstractItemBasedRecommender(Data data,Similarity similarity){
        this.data=data;
        this.similarity=similarity;

    }

	List<Integer> getRecommendationList(int numberOfRecommendation, Map<Integer, Double> predictionMap) {
		return predictionMap.entrySet().stream()
				.sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
				.limit(numberOfRecommendation)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}
