package abstraction;

import enums.File;
import enums.Rank;
import enums.SpaceColor;
import pieces.Piece;

public class SpaceAbstraction {
	private Rank rank;
	private File file;
	private SpaceColor color;
	private Piece piece; // null means no piece
	
	public SpaceAbstraction (Rank rank, File file){
		this.rank = rank;
		this.file = file;
		// The color is set by taking the sum of the coordinates and querying 
		// whether it is odd or even;
		this.color = ((rank.ordinal() + file.ordinal()) & 1) == 0 ? SpaceColor.Gray : SpaceColor.White;
	
		this.piece = null; 
	}
	
	//Public getters
	public Rank getRank(){
		return rank;
	}
	
	public File getFile(){
		return file;
	}
	
	public SpaceColor getColor(){
		return color;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public Piece replacePiece(Piece newPiece){
		Piece oldPiece = piece;
		piece = newPiece;
		return oldPiece;
	}
	
	/*
	 * Returns true if there is a piece in this Space,
	 * false otherwise.
	 */
	public boolean hasPiece(){
		return piece != null;
	}
}
