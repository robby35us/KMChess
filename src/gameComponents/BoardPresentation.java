package gameComponents;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JOptionPane;

import enums.File;
import enums.Rank;
import enums.SpaceColor;
import enums.Timing;
import enums.Turn;

/* Board.java
 * The board class represents the chess board.
 * A board is simply a 2-D array of space objects.
 * A board object is not aware of the Piece class 
 * or of what pieces are on it. It only knows about
 * the spaces it contains, and how to return one of
 * it's Space objects.
 */
public class BoardPresentation extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The 2-D Space array that makes up a Board object.
	
	private Label[] rowLabels;
	private Label cornerLabel;
	private Label[] colLabels;
	volatile private SpacePresentation startSpace = null;
	volatile private SpacePresentation endSpace = null;
	volatile private Turn turn = null;
	private static final Dimension d = new Dimension(15,15);
	
	/*
	 * The constructor initializes the Space array using the enums
	 * Rank and File. The size of the board is dictated by the number
	 * of values in these enums.
	 */
	public BoardPresentation(){
		
		
		setLayout(new GridLayout(9,9,0,0));
		rowLabels = new Label[8];
		for(int i = 0; i < spaces.length; i++){
			rowLabels[i] = new Label("" + (8 - i), Label.CENTER);
			rowLabels[i].setSize(d );
			add(rowLabels[i]);
			
			for(int j = 0; j < spaces[i].length; j++){
				Rank r = Rank.values()[7-i];
				File f = File.values()[j];
				
				SpacePresentation space = this.getSpace(r, f);
				space.setSize(d);
				add(space);
				
			}	
		}
		cornerLabel = new Label();
		cornerLabel.setSize(d);
		add(cornerLabel);
		colLabels = new Label[8];
		for(int i = 0; i < colLabels.length; i++){
			colLabels[i] = new Label("" + (char)('a' + i), Label.CENTER);
			colLabels[i].setSize(d);
			add(colLabels[i]);
		}
		repaint();
	
	}
	
	public void setStartSpace(SpacePresentation space){
		if(startSpace != null)
			startSpace.setButtonState(SpacePresentation.UNARMED);
		if(space != null)
			space.setButtonState(SpacePresentation.ARMED);
		startSpace = space;
	}
	
	public SpacePresentation getStartSpace(){
		return startSpace;
	}
	
	public void setEndSpace(SpacePresentation space){
		if(endSpace != null)
			endSpace.setButtonState(SpacePresentation.UNARMED);
		if(space != null)
			space.setButtonState(SpacePresentation.ARMED);
		endSpace = space;
	}

	public SpacePresentation getEndSpace(){
		return endSpace;
	}
	

	
	/*
	 * Returns the Space on the Board at the given coordinates.
	 */
	public SpacePresentation getSpace(Rank rank, File file){
		if(rank == null || file == null)
			return null;
		return spaces[rank.ordinal()][file.ordinal()];
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public void infoBox(String infoMessage, String title) {
		JOptionPane.showMessageDialog(null, infoMessage, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean playCard(Timing after) {
		Object defaultValue = new Label("Yes!");
		Object[] selectionValues = {defaultValue, new Label("No!")};
		Object selection = JOptionPane.showInputDialog(getParent(), "You have not played a card this round. Would you like a chance to play one now?", "Play a card?", 
				JOptionPane.QUESTION_MESSAGE, null, selectionValues, defaultValue);
		return defaultValue == selection;
	}
}
