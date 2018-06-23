package ia.connect4.model;

/**
 * 
 * Conjunto del tablero resultante de una jugada realizada por la IA y la columna donde la IA
 * ha jugado su ficha.
 *
 */
public class ColBoard {
	
	/**
	 * Columna de la jugada
	 */
	private int col;
	
	/**
	 * Tablero con la jugada
	 */
	private Board board;
	
	public ColBoard(int col, Board board) {
		this.col = col;
		this.board = board;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	

}
