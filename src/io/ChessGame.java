package io;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import game.Start;
import knightmare.KMCard;
import components.Board;
import definitions.File;
import definitions.IOFramework;
import definitions.PieceColor;
import definitions.PieceType;
import definitions.Rank;
import definitions.Turn;
import game.GameState;
import utility.ErrorMessage;
import utility.MoveInput;
import components.Space;

public class ChessGame extends Frame implements WindowListener, IOFramework{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension d = new Dimension(15,15);
	private Label[] rowLabels = new Label[8]; 
	private Label[] colLabels = new Label[8];
	private Space[][] buttons = new Space[8][8];
	
	
	private Label cornerLabel;
	private GameState gs;

	private ChessGame(){
		setLayout(new BorderLayout());
		this.setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
		
		this.gs = new GameState();
		//addComponents(gs.getBoard());
		add(gs.getMessageBox(), BorderLayout.NORTH);
		gs.getMessageBox().setSize(this.getWidth(), this.getHeight()/5);
		add(gs.getBoard(), BorderLayout.CENTER);
		add(gs.getCInfoArea(), BorderLayout.SOUTH);
		add(gs.getCardArea(), BorderLayout.WEST);
		add(gs.getSelectedCardArea(), BorderLayout.EAST);
		
		addWindowListener(this);
		this.setVisible(true);
		this.refreshHand();
		try {
			//System.out.println("Game Started");
			Start.playGame(this,gs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static void main(String[] args){
		ChessGame game = new ChessGame();
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
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
	


	@Override
	public void displayBoard() {
		gs.getBoard().repaint();
	}


	@Override
	public synchronized MoveInput getMoveInput(PieceColor color, ErrorMessage message) {
		Board board = gs.getBoard();
		while(board.getStartSpace() == null || board.getEndSpace() == null){
			  
			}
		
		Space start = board.getStartSpace();
		Space end = board.getEndSpace();
		board.setStartSpace(null);
		board.setEndSpace(null);
		return new MoveInput(start, end);
	}


	@Override
	public void runGameIntro() {
		
		gs.getGameMessage().setText("Welcome to Chess! Press any key to continue!");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void displayMessage(ErrorMessage message) {
		TextArea gm = gs.getGameMessage();
		gm.setText(message.toString());
		gm.repaint();
	}


	@Override
	public PieceType promotePawnTo() {
		TextArea gm = gs.getGameMessage();
		gm.setText("A pawn has been promoted.");
		gm.repaint();
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
	}
}
