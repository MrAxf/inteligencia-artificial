package ia.connect4.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import ia.connect4.model.ColBoard;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class IAService {
	
	private static Classifier classifier;
	private static Instances instances;
	private static ArrayList<Attribute> instanceAttributes;

	public int classify(ArrayList<ColBoard> plays) throws Exception {
		Classifier cls = getClassifier();
		Instances itcs = getInstances();
		ArrayList<Double> results = new ArrayList<Double>(7);
		
		itcs.clear();
		
		/*for (Iterator<ColBoard> iterator = plays.iterator(); iterator.hasNext();) {
			ColBoard play = iterator.next();
			itcs.add(play.getBoard().toInstance(instanceAttributes, itcs));
		}
		
		for (Iterator<Instance> iterator = itcs.iterator(); iterator.hasNext();) {
			Instance instance = iterator.next();
			double result = cls.classifyInstance(instance);
			results.add(result);
		}
		
		return results.get(0).intValue();*/
		Instances src = DataSource.read(new FileInputStream(new File("C:\\modelos\\connect-4.arff")));
		src.setClassIndex(src.numAttributes() - 1);
		double result = cls.classifyInstance(src.get(0));
		String s = src.classAttribute().value((int)result);
		System.out.println(src.classAttribute().value((int)result));
		return 0;
	}
	
	private Classifier getClassifier() throws IOException, Exception {
		if(classifier != null) return classifier;

		classifier = (NaiveBayes) SerializationHelper.read("C:\\modelos\\example.model");

		return classifier;
	}
	
	private Instances getInstances() {
		ArrayList<Attribute> attributes = new ArrayList<Attribute>(43);
		ArrayList<String> boardSpace = new ArrayList<String>(3);
		boardSpace.add("x");
		boardSpace.add("o");
		boardSpace.add("b");
		
		attributes.add(new Attribute("a1", boardSpace));
		attributes.add(new Attribute("a2", boardSpace));
		attributes.add(new Attribute("a3", boardSpace));
		attributes.add(new Attribute("a4", boardSpace));
		attributes.add(new Attribute("a5", boardSpace));
		attributes.add(new Attribute("a6", boardSpace));
		
		attributes.add(new Attribute("b1", boardSpace));
		attributes.add(new Attribute("b2", boardSpace));
		attributes.add(new Attribute("b3", boardSpace));
		attributes.add(new Attribute("b4", boardSpace));
		attributes.add(new Attribute("b5", boardSpace));
		attributes.add(new Attribute("b6", boardSpace));
		
		attributes.add(new Attribute("c1", boardSpace));
		attributes.add(new Attribute("c2", boardSpace));
		attributes.add(new Attribute("c3", boardSpace));
		attributes.add(new Attribute("c4", boardSpace));
		attributes.add(new Attribute("c5", boardSpace));
		attributes.add(new Attribute("c6", boardSpace));
		
		attributes.add(new Attribute("d1", boardSpace));
		attributes.add(new Attribute("d2", boardSpace));
		attributes.add(new Attribute("d3", boardSpace));
		attributes.add(new Attribute("d4", boardSpace));
		attributes.add(new Attribute("d5", boardSpace));
		attributes.add(new Attribute("d6", boardSpace));
		
		attributes.add(new Attribute("e1", boardSpace));
		attributes.add(new Attribute("e2", boardSpace));
		attributes.add(new Attribute("e3", boardSpace));
		attributes.add(new Attribute("e4", boardSpace));
		attributes.add(new Attribute("e5", boardSpace));
		attributes.add(new Attribute("e6", boardSpace));
		
		attributes.add(new Attribute("f1", boardSpace));
		attributes.add(new Attribute("f2", boardSpace));
		attributes.add(new Attribute("f3", boardSpace));
		attributes.add(new Attribute("f4", boardSpace));
		attributes.add(new Attribute("f5", boardSpace));
		attributes.add(new Attribute("f6", boardSpace));
		
		attributes.add(new Attribute("g1", boardSpace));
		attributes.add(new Attribute("g2", boardSpace));
		attributes.add(new Attribute("g3", boardSpace));
		attributes.add(new Attribute("g4", boardSpace));
		attributes.add(new Attribute("g5", boardSpace));
		attributes.add(new Attribute("g6", boardSpace));
		
		ArrayList<String> classes = new ArrayList<String>(3);
		classes.add("win");
		classes.add("loss");
		classes.add("draw");
		
		attributes.add(new Attribute("Class", classes));
		
		instanceAttributes = attributes;
		
		instances = new Instances("connect4", attributes, 7);
		
		return instances;
	}
	
	
}
