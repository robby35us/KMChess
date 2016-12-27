package depricated;
import definitions.*;
import components.*;

/* Space.java
 * A space object represents a single space on the
 * chess board. It holds it's rank and file information
 * and it's color. It also holds information about which 
 * piece, if any, is occupying it. It contains a reference
 * to the board object and some convenience functions to 
 * return adjacent spaces.
 */
public class Space {
	private Rank rank;
	private File file;
	private SpaceColor color;
	private Piece piece; // null means no piece
	private Board board;
	
	
	/*
	 * Public Constructor
	 */
	public Space (Rank rank, File file, Board board){
		this.rank = rank;
		this.file = file;
		
		// The color is set by taking the sum of the coordinates and querying 
		// whether it is odd or even;
		this.color = ((rank.ordinal() + file.ordinal()) & 1) == 0 ? SpaceColor.Gray : SpaceColor.White;
		
		this.piece = null; 
		this.board = board;

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
	 * Returns true if there is a piece in this Space,
	 * false otherwise.
	 */
	public boolean hasPiece(){
		return piece != null;
	}
	
	/*
	 * The setter method for Piece. Returns whether or
	 * not a piece was replaced. Set the value to null
	 * to represent that a piece has been moved from 
	 * this space without a replacement.
	 */
	public boolean changePiece(Piece newPiece){
		boolean wasPrevPiece = piece != null;
		piece = newPiece;
		if(newPiece != null)
			piece.setSpace(this);
		else{
			//System.out.println("Piece at space " + this + " set to null.");
		}
		return wasPrevPiece;

	}
	
	/*
	 * Returns the Space on the board to the left of this one, or 
	 * rather, the Space on the same Rank one file less. Returns 
	 * null if no such Space exists
	 */
	public Space getSpaceLeft(){
		return board.getNextSpace(MoveType.Left.getRankOffset(), 
				                  MoveType.Left.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board to the right of this one, or
	 * rather, the Space on the same Rank one file greater. Returns
	 * null if no such Space exists.
	 */
	public Space getSpaceRight(){
		return board.getNextSpace(MoveType.Right.getRankOffset(), 
                				  MoveType.Right.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board in front of or above this one, or
	 * rather, the Space on the same File one Rank greater. Returns null
	 * if no such Space exists.
	 */
	public Space getSpaceForward(){
		return board.getNextSpace(MoveType.Forward.getRankOffset(), 
                				  MoveType.Forward.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board behind or below this one, or rather,
	 * the Space on the Same File one Rank lesser. Returns null if no such 
	 * Space exists.
	 */
	public Space getSpaceBackward(){
		return board.getNextSpace(MoveType.Backward.getRankOffset(), 
                				  MoveType.Backward.getFileOffset(),this);
	}
}
