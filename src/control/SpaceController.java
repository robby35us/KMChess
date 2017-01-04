package control;		

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import abstractClasses.AppEvent;
import abstraction.SpaceModel;
import static constants.Constants.*;

import enums.File;
import enums.MoveType;
import enums.PieceColor;
import enums.Rank;
import enums.Turn;
import game.GameState;
import presentation.SpaceView;

public class SpaceController extends Controller implements MouseListener {
	
	
	private SpaceModel model;
	private SpaceView view;
	private BoardController parent;
	private boolean mousedown;
	private boolean mouseInBounds = false;

	public SpaceController(SpaceView view, SpaceModel model, BoardController parent){
		
		this.view = view;
		view.addMouseListener(this);
		this.model = model;
		view.setBackground(model.getColor().getAWTColor());
		this.parent = parent;
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

	@Override
	public void createChildTriad() {
		// do nothing
		
	}

	@Override
	public void accept(AppEvent e) {
		e.setData(model);
	}

	@Override
	public void handleEvent(AppEvent e) {
	}

	public File getFile() {
		return this.model.getFile();
	}
	
	public Rank getRank(){
		return this.model.getRank();
	}
	
}   
