package ia.connect4.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ia.connect4.model.Board;
import ia.connect4.model.ColBoard;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
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
		ArrayList<String> results = new ArrayList<String>(7);
		
		Instances instances = Board.toInstances(plays);
		
		
		for (Iterator<Instance> iterator = instances.iterator(); iterator.hasNext();) {
			Instance instance = iterator.next();
			double result = cls.classifyInstance(instance);
			results.add(instances.classAttribute().value((int)result));
		}
		
		String last = "";
		int column = 0;
		
		int i = 0;
		String classValue = "";
		long seed = System.nanoTime();
		Collections.shuffle(results, new Random(seed));
		Collections.shuffle(plays, new Random(seed));
		for (Iterator<String> iterator = results.iterator(); iterator.hasNext();) {
			classValue = iterator.next();
			if(last.isEmpty()) {
				last = classValue;
				column = plays.get(i).getCol();
				if(last.equals("win")) break;
			}else if(classValue.equals("win")) {
				last = classValue;
				column = plays.get(i).getCol();
				break;
			} else if(last.equals("loss") && classValue.equals("draw")) {
				last = classValue;
				column = plays.get(i).getCol();
			}
			i++;
			
		}
		
		return column;
	}
	
	private Classifier getClassifier() throws IOException, Exception {
		if(classifier != null) return classifier;

		classifier = (MultilayerPerceptron) SerializationHelper.read(modelsPath+"505050.model");

		return classifier;
	}
	
	
}
