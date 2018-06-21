package ia.connect4.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ia.connect4.model.Board;
import ia.connect4.model.ColBoard;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

@Service
public class IAService {
	
	private static Classifier classifier;
	
	@Value("${aiModels.path}")
	private String modelsPath;

	public int classify(ArrayList<ColBoard> plays) throws Exception {
		Classifier cls = getClassifier();
		ArrayList<Double> results = new ArrayList<Double>(7);
		
		Instances instances = Board.toInstances(plays);
		
		
		for (Iterator<Instance> iterator = instances.iterator(); iterator.hasNext();) {
			Instance instance = iterator.next();
			double result = cls.classifyInstance(instance);
			results.add(result);
		}
		
		String last = "";
		int column = 0;
		
		int i = 0;
		String classValue = "";
		for (Iterator<Double> iterator = results.iterator(); iterator.hasNext();) {
			Double res = iterator.next();
			classValue = instances.classAttribute().value(res.intValue());
			if(last.isEmpty() || (!last.equals(classValue) && last.equals("loss"))) {
				last = classValue;
				column = plays.get(i).getCol();
				if(last.equals("win")) break;
			}
			i++;
			
		}
		
		return column;
	}
	
	private Classifier getClassifier() throws IOException, Exception {
		if(classifier != null) return classifier;

		classifier = (NaiveBayes) SerializationHelper.read(modelsPath+"example.model");

		return classifier;
	}
	
	
}
