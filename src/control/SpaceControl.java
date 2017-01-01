package control;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import abstraction.SpaceAbstraction;
import enums.File;
import enums.MoveType;
import enums.PieceColor;
import enums.Rank;
import enums.SpaceColor;
import enums.Turn;
import game.GameState;
import pieces.Piece;
import pieces.PieceImages;
import presentation.SpacePresentation;

public class SpaceControl implements MouseListener {
	private SpaceAbstraction abs;
	private SpacePresentation pres;
	private BoardControl parent;
	private boolean mousedown;
	private boolean mouseInBounds = false;

	public SpaceControl(Rank rank, File file, BoardControl parent){
		abs = new SpaceAbstraction(rank, file);
		pres = new SpacePresentation(abs.getColor(), null);
		pres.addMouseListener(this);
		this.parent = parent;
	}
	
	//Public getters
	public SpacePresentation getSpacePres() {
		return pres;
	}
	
	public BoardControl getParent(){
		return parent;
	}
	
	public Rank getRank(){
		return abs.getRank();
	}
	
	public File getFile(){
		return abs.getFile();
	}
	
	public SpaceColor getColor(){
		return abs.getColor();
	}
	
	public Piece getPiece(){
		return abs.getPiece();
	}	
	
	
	/*
	 * Returns true if there is a piece in this Space,
	 * false otherwise.
	 */
	public boolean hasPiece(){
		return abs.hasPiece();
	}
	
	public boolean changePiece(Piece newPiece, Boolean repaint){
		Piece oldPiece = abs.replacePiece(newPiece);
		Image img = null;
		if(newPiece != null){
			img = PieceImages.getImage(newPiece.getType(), newPiece.getColor(), abs.getColor());
			newPiece.setSpace(this);
		}
		pres.updateImage(img, repaint);
		return oldPiece != null;
	}
	
	/*
	 * Returns the Space on the board to the left of this one, or 
	 * rather, the Space on the same Rank one file less. Returns 
	 * null if no such Space exists
	 */
	public SpaceControl getSpaceLeft(){
		return parent.getNextSpace(MoveType.Left.getRankOffset(), 
				                  MoveType.Left.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board to the right of this one, or
	 * rather, the Space on the same Rank one file greater. Returns
	 * null if no such Space exists.
	 */
	public SpaceControl getSpaceRight(){
		return parent.getNextSpace(MoveType.Right.getRankOffset(), 
                				  MoveType.Right.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board in front of or above this one, or
	 * rather, the Space on the same File one Rank greater. Returns null
	 * if no such Space exists.
	 */
	public SpaceControl getSpaceForward(){
		return parent.getNextSpace(MoveType.Forward.getRankOffset(), 
                				  MoveType.Forward.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board behind or below this one, or rather,
	 * the Space on the Same File one Rank lesser. Returns null if no such 
	 * Space exists.
	 */
	public SpaceControl getSpaceBackward(){
		return parent.getNextSpace(MoveType.Backward.getRankOffset(), 
                				  MoveType.Backward.getFileOffset(),this);
		
	}

	public void armSpace(){
		this.pres.setButtonState(SpacePresentation.ARMED);
	}
	
	public void unarmSpace(){
		this.pres.setButtonState(SpacePresentation.UNARMED);
	}
	
	public void setSpaceToOver(){
		this.pres.setButtonState(SpacePresentation.OVER);
	}
	
	public void setDisabled(){
		this.pres.setButtonState(SpacePresentation.DISABLED);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { 
		mousedown = false;
		
		if(parent.activeSpace != null){
			parent.activeSpace.unarmSpace();
			if(parent.activeSpace != this){
				parent.setEndSpace(this);
				parent.activeSpace = null;
				return;
			}
		}
		if(parent.activeSpace == this){
			parent.activeSpace = null;
			parent.setStartSpace(null);
		}
		else {
			if(abs.getPiece() != null &&
			    (GameState.globalGS.getTurn() == Turn.Player1 && abs.getPiece().getColor() == PieceColor.White ||
				 GameState.globalGS.getTurn() == Turn.Player2 && abs.getPiece().getColor() == PieceColor.Black)) { 	   
					parent.activeSpace = this;
					this.armSpace();
					parent.setStartSpace(this);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
        this.mouseInBounds = true;
		if ( mousedown ) {
            this.armSpace();;
        }
        else {
            this.setSpaceToOver();
        }
       
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseInBounds = false;
        if( this != parent.activeSpace)
        	this.unarmSpace();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousedown = true;
		this.armSpace();;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	   mousedown = false;
	   if(mouseInBounds)
		   this.setSpaceToOver();
	   else
		   this.unarmSpace();
		
	}

	
}   
