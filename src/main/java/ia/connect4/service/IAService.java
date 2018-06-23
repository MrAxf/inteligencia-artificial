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

/**
 * 
 * Servicio de la inteligencia artificial.
 * En esta clase se encuentran declaras todas las operaciones de clasificación de la aplicación.
 *
 */
@Service
public class IAService {
	
	/**
	 * Clasificador de weka.
	 * Se obtine mediante el patrón singleton.
	 */
	private static Classifier classifier;
	
	/**
	 * Ruta donde se encuentra el modelo entrenado de red neuronal.
	 */
	@Value("${aiModels.path}")
	private String modelsPath;
	
	/**
	 * Método encargado de clasificar las jugadas de la IA y devolver la mejor.
	 * @param plays Jugadas de la IA
	 * @return Columna de la hecha por la IA
	 * @throws Exception
	 */
	public int classify(ArrayList<ColBoard> plays) throws Exception {
		//Obtenemos el clasificador
		Classifier cls = getClassifier();
		
		//ArrayList para guardar los resultados.
		ArrayList<String> results = new ArrayList<String>(7);
		
		//Convertimos las jugadas en instancias de WEKA.
		//Generamos un objetos Instances con las posibles jugadas que puede realizar la IA 
		//con el fin de poder ser clasificadas por la red neuronal y decidir la mejor jugada.
		Instances instances = Board.toInstances(plays);
		
		//iteramos sobre las instancias
		for (Iterator<Instance> iterator = instances.iterator(); iterator.hasNext();) {
			Instance instance = iterator.next();
			//Obtenemos el resultado de la clasificación (Siempre es un double)
			double result = cls.classifyInstance(instance);
			//Obtenemos el valor en String de la clasificación
			results.add(instances.classAttribute().value((int)result));
		}
		
		//Declaración de variables auxiliares
		String last = "";
		int column = 0;
		int i = 0;
		String classValue = "";
		
		//Reordenación aleatoria de las jugadas para evitar predilección de la IA 
		//por columnas específicas.
		long seed = System.nanoTime();
		Collections.shuffle(results, new Random(seed));
		Collections.shuffle(plays, new Random(seed));
		
		//Iteramos sobre los resultados para obtener el mejor.
		for (Iterator<String> iterator = results.iterator(); iterator.hasNext();) {
			classValue = iterator.next();
			
			//Asignamos el posible resultado a devolver en caso de que no haya ninguno (primera iteración)
			if(last.isEmpty()) {
				last = classValue;
				column = plays.get(i).getCol();
				if(last.equals("win")) break;
			
			//Si el resultado de la iteración es igual a 'win' nos quedamos con ese resultado.
			}else if(classValue.equals("win")) {
				last = classValue;
				column = plays.get(i).getCol();
				break;
			
			//Si el resultado de la iteración es igual a 'draw' y el mejor resultado que tenemos 
			//de momento es 'loss', ponemos el resultado de esta iteración como mejor resultado.
			} else if(last.equals("loss") && classValue.equals("draw")) {
				last = classValue;
				column = plays.get(i).getCol();
			}
			i++;
		}
		
		//Devolvemos la jugada de la IA
		return column;
	}
	
	/**
	 * Método que te devuelve el clasificador weka;
	 * Si está inicializado lo devuelve tal cual.
	 * Si no, carga el modelo desde el archivo especificado y lo devuelve.
	 * @return Clasificador.
	 * @throws IOException
	 * @throws Exception
	 */
	private Classifier getClassifier() throws IOException, Exception {
		//Si está inicializado lo devuelve.
		if(classifier != null) return classifier;
		
		//Si no está inicializado crea el clasificador desde el archivo especificado.
		classifier = (MultilayerPerceptron) SerializationHelper.read(modelsPath+"505050.model");
		return classifier;
	}
	
	
}
