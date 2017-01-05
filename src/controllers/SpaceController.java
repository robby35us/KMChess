package controllers;		

import static definitions.Constants.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import definitions.Controller;
import definitions.File;
import definitions.MoveType;
import definitions.PieceColor;
import definitions.Rank;
import definitions.SpaceColor;
import definitions.Turn;
import definitions.Visitor;
import game.GameState;
import models.BoardModel;
import models.SpaceModel;
import pieces.Piece;
import views.SpaceView;

public class SpaceController extends Controller implements MouseListener {
	
	
	private SpaceModel model;
	private SpaceView view;
	private BoardController parent;
	private BoardModel parentModel;
	private boolean mousedown;
	private boolean mouseInBounds = false;

	public SpaceController(SpaceView view, SpaceModel model, BoardController parent){
		
		this.view = view;
		this.model = model;
		view.setBackground(model.getColor().getAWTColor());
		this.parent = parent;
		this.parentModel = parent.getModel();
		view.addMouseListener(this);
	}
	
	//Public getters
	public SpaceView getSpaceView() {
		return view;
	}
	
	public BoardController getParent(){
		return parent;
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
		if(parentModel == null){ 
			System.out.println("Space.parentModel == null");
			return;
		}
		if(parentModel.activeSpace != null){
			parentModel.activeSpace.unarmSpace();
			if(parentModel.activeSpace != this){
				parentModel.setEndSpace(this);
				parentModel.activeSpace = null;
				return;
			}
		}
		if(parentModel.activeSpace == this){
			parentModel.activeSpace = null;
			parentModel.setStartSpace(null);
		}
		else {
			if(model.getPiece() != null &&
			    (GameState.globalGS.getTurn() == Turn.Player1 && model.getPiece().getColor() == PieceColor.White ||
				 GameState.globalGS.getTurn() == Turn.Player2 && model.getPiece().getColor() == PieceColor.Black)) { 	   
					parentModel.activeSpace = this;
					this.armSpace();
					parentModel.setStartSpace(this);
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
        if( parentModel == null || this != parentModel.activeSpace)
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

	@Override
	public void createChildTriad() {
		// do nothing
		
	}

	@Override
	public Object accept(Visitor v) {
		return model;
	}

	public File getFile() {
		return this.model.getFile();
	}
	
	public Rank getRank(){
		return this.model.getRank();
	}

	public Piece getPiece() {
		return this.model.getPiece();
	}

	public void changePiece(Piece newPiece, boolean b) {
		@SuppressWarnings("unused")
		Piece oldPiece = this.model.replacePiece(newPiece, b);	
		if(newPiece != null)
			newPiece.setSpace(this);
		//if(oldPiece != null)
		//	oldPiece.setSpace(null);
	}

	public boolean hasPiece() {
		return this.model.hasPiece();
	}

	public SpaceColor getColor() {
		return this.model.getColor();
	}

	public void setParentModel() {
		parentModel = parent.getModel();
	}
	
}   
