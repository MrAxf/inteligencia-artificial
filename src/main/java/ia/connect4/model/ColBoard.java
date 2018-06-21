package ia.connect4.model;

public class ColBoard {
	
	private int col;
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
