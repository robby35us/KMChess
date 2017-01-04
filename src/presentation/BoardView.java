package presentation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.Iterator;

import javax.swing.JOptionPane;

import control.BoardController;
import control.SpaceController;
import enums.File;
import enums.Rank;
import enums.Timing;

/* Board.java
 * The board class represents the chess board.
 * A board is simply a 2-D array of space objects.
 * A board object is not aware of the Piece class 
 * or of what pieces are on it. It only knows about
 * the spaces it contains, and how to return one of
 * it's Space objects.
 */
public class BoardView extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Dimension d = new Dimension(15,15);
	
	/*
	 * The constructor initializes the Space array using the enums
	 * Rank and File. The size of the board is dictated by the number
	 * of values in these enums.
	 */
	public BoardView(Iterator<SpaceController> spaces){
		
		
		setLayout(new GridLayout(BoardController.numRanks + 1, BoardController.numFiles + 1, 0, 0));
		for(int i = 0; i < BoardController.numRanks; i++){
			Label l = new Label(Rank.values()[7 - i].toString(), Label.CENTER);
			l.setSize(d );
			add(l);
			for(int j = 0; j < BoardController.numFiles; j++){
				SpaceView spacePres = spaces.next().getSpaceView();
				add(spacePres);
				
			}	
		}
		Label corner = new Label();
		corner.setSize(d);
		add(corner);
		for(int i = 0; i < BoardController.numFiles; i++){
			Label l = new Label(File.values()[0].toString(), Label.CENTER);
			l.setSize(d);
			add(l);
		}
		repaint();
	
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
