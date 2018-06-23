package ia.connect4.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ia.connect4.model.Board;
import ia.connect4.service.IAService;

/**
 * 
 * Controladador de la API de la inteligencia artificial.
 */
@RestController
public class AIController {
	
	/**
	 * Conexión con el servicio de la inteligencia artificial.
	 */
	@Autowired
	private IAService ia;
	
	/**
	 * Ruta de la API que devuelve la jugada de la IA haciendo uso del servicio 
	 * de inteligencia artificial
	 * @param board Estado del tablero actual (JSON {a1: 'x', a2: 'b', ...})
	 * @return Columna donde la IA hace su jugada
	 */
	@PostMapping("/getAiTurn")
	public int getAiTurn(@Valid @RequestBody Board board) {
		int column = 0;
		try {
			//Almacena el número de la columna por donde decide
			//realizar la jugada la IA
			column = ia.classify(board.generatePlays());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return column;
	}

}
