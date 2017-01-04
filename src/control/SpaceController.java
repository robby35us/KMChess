package control;		

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import abstraction.SpaceModel;
import static constants.Constants.*;
import enums.File;
import enums.MoveType;
import enums.PieceColor;
import enums.Rank;
import enums.SpaceColor;
import enums.Turn;
import game.GameState;
import pieces.Piece;
import presentation.SpaceView;

public class SpaceController implements MouseListener {
	
	
	private SpaceModel model;
	private SpaceView view;
	private BoardControl parent;
	private boolean mousedown;
	private boolean mouseInBounds = false;

	public SpaceController(Rank rank, File file, BoardControl parent){
		
		view = new SpaceView();
		view.addMouseListener(this);
		model = new SpaceModel(rank, file, view);
		view.setBackground(model.getColor().getAWTColor());
		this.parent = parent;
	}
	
	//Public getters
	public SpaceView getSpaceView() {
		return view;
	}
	
	public BoardControl getParent(){
		return parent;
	}
	
	public Rank getRank(){
		return model.getRank();
	}
	
	public File getFile(){
		return model.getFile();
	}
	
	public SpaceColor getColor(){
		return model.getColor();
	}
	
	public Piece getPiece(){
		return model.getPiece();
	}	
	
	
	/*
	 * Returns true if there is a piece in this Space,
	 * false otherwise.
	 */
	public boolean hasPiece(){
		return model.hasPiece();
	}
	
	public Piece changePiece(Piece newPiece, Boolean repaint){
		return model.replacePiece(newPiece, repaint);
	}
	
	/*
	 * Returns the Space on the board to the left of this one, or 
	 * rather, the Space on the same Rank one file less. Returns 
	 * null if no such Space exists
	 */
	public SpaceController getSpaceLeft(){
		return parent.getNextSpace(MoveType.Left.getRankOffset(), 
				                  MoveType.Left.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board to the right of this one, or
	 * rather, the Space on the same Rank one file greater. Returns
	 * null if no such Space exists.
	 */
	public SpaceController getSpaceRight(){
		return parent.getNextSpace(MoveType.Right.getRankOffset(), 
                				  MoveType.Right.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board in front of or above this one, or
	 * rather, the Space on the same File one Rank greater. Returns null
	 * if no such Space exists.
	 */
	public SpaceController getSpaceForward(){
		return parent.getNextSpace(MoveType.Forward.getRankOffset(), 
                				  MoveType.Forward.getFileOffset(),this);
	}
	
	/*
	 * Returns the Space on the board behind or below this one, or rather,
	 * the Space on the Same File one Rank lesser. Returns null if no such 
	 * Space exists.
	 */
	public SpaceController getSpaceBackward(){
		return parent.getNextSpace(MoveType.Backward.getRankOffset(), 
                				  MoveType.Backward.getFileOffset(),this);
		
	}

	public void armSpace(){
		this.view.setButtonState(ARMED);
	}
	
	public void unarmSpace(){
		this.view.setButtonState(UNARMED);
	}
	
	public void setSpaceToOver(){
		this.view.setButtonState(OVER);
	}
	
	public void setDisabled(){
		this.view.setButtonState(DISABLED);
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
			if(model.getPiece() != null &&
			    (GameState.globalGS.getTurn() == Turn.Player1 && model.getPiece().getColor() == PieceColor.White ||
				 GameState.globalGS.getTurn() == Turn.Player2 && model.getPiece().getColor() == PieceColor.Black)) { 	   
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
