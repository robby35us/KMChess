package control;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import abstraction.SpaceAbstraction;
import enums.File;
import enums.PieceColor;
import enums.Rank;
import enums.SpaceColor;
import enums.Turn;
import gameComponents.BoardPresentation;
import gameComponents.SpacePresentation;
import pieces.Piece;
import pieces.PieceImages;

public class SpaceControl implements MouseListener {
	private SpaceAbstraction abs;
	private SpacePresentation pres;
	private boolean mousedown;
	private boolean mouseInBounds = false;

	public SpaceControl(Rank rank, File file){
		abs = new SpaceAbstraction(rank, file);
		pres = new SpacePresentation(abs.getColor(), null);
	}
	
	//Public getters
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
		if(newPiece != null)
			img = PieceImages.getImage(newPiece.getType(), newPiece.getColor(), abs.getColor());
		pres.updateImage(img, repaint);
		return oldPiece != null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { 
		mousedown = false;
		
		if(SpacePresentation.activeSpace != null){
			SpacePresentation.activeSpace.setButtonState(SpacePresentation.UNARMED);
			if(SpacePresentation.activeSpace != this.pres){
				((BoardPresentation) pres.getParent()).setEndSpace(pres);
				SpacePresentation.activeSpace = null;
				return;
			}
		}
		if(SpacePresentation.activeSpace == this.pres){
			SpacePresentation.activeSpace = null;
			((BoardPresentation) pres.getParent()).setStartSpace(null);
		}
		else {
			if(abs.getPiece() != null &&
			   (((BoardPresentation) pres.getParent()).getTurn() == Turn.Player1 && abs.getPiece().getColor() == PieceColor.White ||
				((BoardPresentation) pres.getParent()).getTurn() == Turn.Player2 && abs.getPiece().getColor() == PieceColor.Black)) { 	   
					SpacePresentation.activeSpace = pres;
					pres.setButtonState( SpacePresentation.ARMED );
					((BoardPresentation) pres.getParent()).setStartSpace(pres);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
        this.mouseInBounds = true;
		if ( mousedown ) {
            pres.setButtonState( SpacePresentation.ARMED );
        }
        else {
            pres.setButtonState( SpacePresentation.OVER );
        }
       
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseInBounds = false;
        if( pres != SpacePresentation.activeSpace)
        	pres.setButtonState( SpacePresentation.UNARMED );
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousedown = true;
		pres.setButtonState(SpacePresentation.ARMED);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	   mousedown = false;
	   if(mouseInBounds)
		   pres.setButtonState( SpacePresentation.OVER );
	   else
		   pres.setButtonState(SpacePresentation.UNARMED);
		
	}
}   
