package ia.connect4.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ia.connect4.model.Board;
import ia.connect4.service.IAService;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@RestController
public class AIController {
	
	@Autowired
	private IAService ia;
	
	@GetMapping("/train")
	public int train() {
		NaiveBayes cls = new NaiveBayes();
		//cls.setHiddenLayers("");
		try {
			Instances src = DataSource.read(new FileInputStream(new File("C:\\modelos\\connect-4.arff")));
			src.setClassIndex(src.numAttributes() - 1);
			cls.buildClassifier(src);
			weka.core.SerializationHelper.write("C:\\modelos\\example.model", cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@PostMapping("/getAiTurn")
	public int getAiTurn(@Valid @RequestBody Board board) {
		try {
			ia.classify(board.generatePlays());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Integer> al = new ArrayList<>(7);
		if(board.getA6() == 'b')al.add(0);
		if(board.getB6() == 'b')al.add(1);
		if(board.getC6() == 'b')al.add(2);
		if(board.getD6() == 'b')al.add(3);
		if(board.getE6() == 'b')al.add(4);
		if(board.getF6() == 'b')al.add(5);
		if(board.getG6() == 'b')al.add(6);
		return al.get(new Random().nextInt(al.size()-1));
	}

}
