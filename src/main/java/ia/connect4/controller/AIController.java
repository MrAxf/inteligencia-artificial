package ia.connect4.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ia.connect4.model.Board;
import ia.connect4.service.IAService;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@RestController
public class AIController {
	
	@Autowired
	private IAService ia;
	
	@Value("${aiModels.path}")
	private String modelsPath;
	
	@GetMapping("/train")
	public int train() {
		NaiveBayes cls = new NaiveBayes();
		//cls.setHiddenLayers("");
		try {
			Instances src = DataSource.read(new FileInputStream(new File(modelsPath+"connect-4.arff")));
			src.setClassIndex(src.numAttributes() - 1);
			cls.buildClassifier(src);
			weka.core.SerializationHelper.write(modelsPath+"example.model", cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@PostMapping("/getAiTurn")
	public int getAiTurn(@Valid @RequestBody Board board) {
		int column = 0;
		try {
			column = ia.classify(board.generatePlays());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return column;
	}

}
