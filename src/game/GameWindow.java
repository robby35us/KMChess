package game;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import cards.KMCard;
import interfaces.IOFramework;
import utilityContainers.ErrorMessage;
import utilityContainers.MoveInput;
import enums.PieceColor;
import enums.PieceType;
import enums.Turn;
import gameComponents.Board;
import gameComponents.Space;
import guiComponent.CardArea;

public class GameWindow extends Frame implements WindowListener, IOFramework{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameState gs;
	private boolean playKnightmareMode;

	public GameWindow(GameState gs){
		setLayout(new BorderLayout());
		this.setSize(this.getMaximumSize());
		this.setState(MAXIMIZED_BOTH);
		
		this.gs = gs;
		//addComponents(gs.getBoard());
		add(gs.getMessageBox(), BorderLayout.NORTH);
		gs.getMessageBox().setSize(this.getWidth(), this.getHeight()/5);
		add(gs.getBoard(), BorderLayout.CENTER);
		add(gs.getCInfoArea(), BorderLayout.SOUTH);
		add(gs.getCardArea(), BorderLayout.WEST);
		add(gs.getSelectedCardArea(), BorderLayout.EAST);
		
		addWindowListener(this);
		
		this.setTitle("Knightmare Chess");
		this.setVisible(true);
		//this.setSize(getMaximumSize());
		this.runGameIntro();
		//System.out.println(playKnightmareMode);
		if(playKnightmareMode){
			this.refreshHand();
		}else {
			gs.getCardArea().setEnabled(false);
		} 
	}
	
	/*
	private void addComponents(Board board){
		for(int i = 0; i < buttons.length; i++){
			rowLabels[i] = new Label("" + (8 - i), Label.CENTER);
			rowLabels[i].setSize(d);
			add(rowLabels[i]);
			
			for(int j = 0; j < buttons[i].length; j++){
				Rank r = Rank.values()[7-i];
				File f = File.values()[j];
				
				Space space = board.getSpace(r, f);
				space.setSize(d);
				add(board.getSpace(r, f));
				
			}	
		}
		cornerLabel = new Label();
		cornerLabel.setSize(d);
		add(cornerLabel);
		for(int i = 0; i < colLabels.length; i++){
			colLabels[i] = new Label("" + (char)('a' + i), Label.CENTER);
			colLabels[i].setSize(d);
			add(colLabels[i]);
		}
		repaint();
	}
	
	*/


	@Override
	public void windowActivated(WindowEvent arg0) {
		//this.setState(MAXIMIZED_BOTH);
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.dispose();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	

	@Override
	public void displayBoard() {
		gs.getBoard().repaint();
	}


	@Override
	public synchronized MoveInput getMoveInput(PieceColor color, ErrorMessage message) {
		Board board = gs.getBoard();
		CardArea ca = gs.getCardArea();
		while((board.getStartSpace() == null || board.getEndSpace() == null) &&
			  (ca.getExecutingCard() == null)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
		}
		if(board.getStartSpace() != null && board.getEndSpace() != null){
			Space start = board.getStartSpace();
			Space end = board.getEndSpace();
			board.setStartSpace(null);
			board.setEndSpace(null);
			return new MoveInput(start, end);
		}
		return null;
	}
	
	public synchronized void getAfterExecutingCard() {
		CardArea ca = gs.getCardArea();
		while(playKnightmareMode && ca.getExecutingCard() == null && !ca.noCardPlayed()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
		}
	}


	@Override
	public void runGameIntro() {
		/*TextArea gm = gs.getGameMessage();
		gm.setText("Welcome");
		gm.repaint();
		*/
		infoBox("Welcome to Knightmare Chess!", "Welcome!");
		String defaultSelection = "Knightmare Chess";
		String[] selections = {defaultSelection, "Regular Chess"};
		Object selection = JOptionPane.showInputDialog(this, "Which version of chess would you like to play?", 
				                    "Choose Which Version", JOptionPane.QUESTION_MESSAGE, 
				                    null, selections, defaultSelection);
		playKnightmareMode = selection == defaultSelection;
	}


	@Override
	public void displayMessage(ErrorMessage message) {
		infoBox(message.toString(), "Alert");
	}


	@Override
	public PieceType promotePawnTo() {
		TextArea gm = gs.getGameMessage();
		gm.setText("A pawn has been promoted.");
		gm.repaint();
		infoBox("A pawn has been promoted.", "Alert!");
		return PieceType.Queen;
	}


	@Override
	public void displayGetMoveInputText(Turn turn) {
		TextArea mb = gs.getGameMessage();
		mb.setText("It is now player " + turn.toString() 
				+ "'s turn.\nSelect your move.");
		mb.repaint();
		
	}


	public void setSelectedCard(KMCard card) {
		gs.getSelectedCardArea().setCard(card.getCInfo().getSetNumber(),
				                         card.getCInfo().getCardNum());
		gs.getCInfoArea().setCardInfo(card.getCInfo());
		
	}


	@Override
	public void refreshHand() {
		gs.getCardArea().refreshHand();
		this.repaint();
	}

	@Override
	public KMCard getExecutingCard() {
		return gs.getCardArea().getExecutingCard();
	}
}