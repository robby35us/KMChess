package enums;

/* This enum is used to register whose turn it is, Player1's or 
 * Player2's. This enum is coupled tightly with the Color enum, 
 * such that Color.White.ordinal() returns the same value as 
 * Turn.Player1.ordinal() and likewise for Color.Black.ordinal 
 * and turn.Player2.ordinal(). In fact, these could be reduced 
 * to the same course, but these names are used to make the 
 * language in the code clear.
 */

public enum Turn {
	Player1, Player2;

	public PieceColor getColor() {
		if(this == Player1)
			return PieceColor.White;
		else
			return PieceColor.Black;
	}
	
	public String toString() {
		if(this == Player1)
			return "Player 1";
		else
			return "Player 2";
	}
}