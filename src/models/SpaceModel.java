package models;

import java.util.Observable;
import java.util.Observer;

import definitions.File;
import definitions.Rank;
import definitions.SpaceColor;
import pieces.Piece;

public class SpaceModel extends Observable{
	
	private Rank rank;
	private File file;
	private SpaceColor color;
	private Piece piece; // null means no piece
	
	public SpaceModel (Rank rank, File file, Observer view){
		this.rank = rank;
		this.file = file;
		// The color is set by taking the sum of the coordinates and querying 
		// whether it is odd or even;
		this.color = ((rank.ordinal() + file.ordinal()) & 1) == 0 ? SpaceColor.Gray : SpaceColor.White;
	
		this.piece = null; 
		this.addObserver(view);
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
	

	/*
	 * The setter method for Piece. Returns whether or
	 * not a piece was replaced. Set the value to null
	 * to represent that a piece has been moved from 
	 * this space without a replacement.
	 */
	public Piece replacePiece(Piece newPiece, boolean repaint){
		Piece oldPiece = piece;
		piece = newPiece;
		if(repaint){
			this.setChanged();
			//System.out.print("Changed == " + this.hasChanged());
		}
		this.notifyObservers(piece);
		//this.clearChanged();
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
