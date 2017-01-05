package definitions;

/*
 * Represents the type of Piece object a piece is.
 */
public enum PieceType {
	Pawn(1), Knight(3), Bishop(3), Rook(5), Queen(9), King(10);
	
	private int score;
	
	/*
	 * score is a positive number between 1 and 10 that represents
	 * how much the capture of a piece is worth.
	 */
	private PieceType(int score) {
		this.score = score;
	}
	
	/*
	 * Returns the score value for this PieceType
	 */
	public int getScore(){
		return score;
	}
}
