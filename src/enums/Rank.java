package enums;

/*
 * Symbolizes a Rank, or row, on the chess board.
 * One signifies the Rank closest to the white player. 
 * Eight signifies the Rank closest to the black player.
 */
public enum Rank {
	One, Two, Three, Four, Five, Six, Seven, Eight;
	
	public String toString() {
		return "" + (this.ordinal() + 1);
	}
	
}
