
package game;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import abstractClasses.ActualMove;
import abstractClasses.ContEffect;
import cards.KMDeck;
import controllers.BoardController;
import controllers.SpaceController;
import enums.*;
import gameComponents.*;
import guiComponent.CInfoArea;
import guiComponent.CardArea;
import guiComponent.MessageBox;
import guiComponent.SelectedCardArea;
import pieces.Piece;
import pieces.PieceFactory;
import standardMoves.*;
import utilityContainers.ErrorMessage;

/*
 * As advertised, this class keeps track of the game state. It holds
 * the Board, two Player objects, a PieceFactory for making new pieces,
 * a list of ErrorMessages and tracks the previous move. It also
 * provides various operations and methods that involve changing the
 * game state.
 */
public class GameState implements ItemListener {
	private BoardController board;
	private MessageBox messageBox;
	private TextArea gameMessage;
	private List contEffectsArea;
	private Integer contEffectSelected;
	private CardArea cardArea;
	private static final int messageRows = 5;
	private static final int messageCols = 40;
	
	private static final String noContEffectsItem = "No Continuous Effects In Play";
	private Font textFont;
	private Player whitePlayer;
	private Player blackPlayer;
	private PieceFactory factory;
	private ArrayList<ErrorMessage> messages;
	private ActualMove prevMove;
	private CInfoArea cInfoArea;
	private SelectedCardArea selectedCardArea;
	private ArrayList<ContEffect> contEffects;
	volatile private Turn turn = null;
	
	public static GameState globalGS;
	
	/*
	 * This constructor sets up a regular game, and initializes the Board
	 * with regular piece placement, as well as both Player objects.
	 */
	public GameState() {
		this(null);
		setupGameState(); // sets the Board
		globalGS = this;
	}
	
	/*
	 * This constructor is used for testing with a irregular board. It 
	 * does not place pieces on the given Board or create Player objects.
	 */
	public GameState(BoardController board) {
		Piece.setGameState(this);
		this.board = board;
		
		this.messageBox = new MessageBox();
		this.messageBox.setLayout(new GridLayout(1,2));
		
		this.gameMessage = new TextArea("Welcome to Knightmare Chess!",
				messageRows, messageCols, TextArea.SCROLLBARS_NONE);
		this.textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		this.gameMessage.setFont(textFont);
		this.messageBox.add(gameMessage);
		
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		Label l = new Label("Select a continuous effect to view the effect on the board.");
		l.setFont(textFont);
		p.add(l, BorderLayout.NORTH);
	
		this.contEffectsArea = new List();
		this.contEffectsArea.add(noContEffectsItem);
		this.contEffectsArea.setFont(textFont);
		this.contEffectsArea.addItemListener(this);
		p.add(contEffectsArea, BorderLayout.CENTER);
		this.messageBox.add(p);
	
		
		this.contEffects = new ArrayList<ContEffect>();
		
		this.cardArea = new CardArea(new KMDeck(KMDeck.FULL));
		
		this.cInfoArea = new CInfoArea();
		this.selectedCardArea = new SelectedCardArea();
		//this.cardArea.setSize(new Dimension(550, 100));
		this.messages = new ArrayList<ErrorMessage>();
	}

	/*
	 * Does the work of setting up the board state for a regular game.
	 * Initializes the Players, their PlayerSet's, the Board, and the
	 * factory.
	 */
	public void setupGameState() {
		board = new BoardController();
		factory = new PieceFactory(board, this);
		
		PlayerSet [] sets = new PlayerSet[2];
		sets[0] = new PlayerSet(factory, PieceColor.White);
		sets[1] = new PlayerSet(factory, PieceColor.Black);
		
		whitePlayer = new Player(sets[0], PieceColor.White, sets[1].getKing());
		blackPlayer = new Player(sets[1], PieceColor.Black, sets[0].getKing());
		
		BoardSetup.setupChessBoard(sets[0], sets[1], board);
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	/*
	 * Moves the piece to it's new positions, using the information provided
	 * in move and turn to get the initial and destination location and for
	 * which player should lose the captured piece.
	 */
	public ErrorMessage makeMove(ActualMove move, Turn turn, ErrorMessage message) {
		Piece moving = move.getInitialSpace().getPiece();
		
		// Complete the move
		Piece captured = movePiece(move, true);
		Player opposite = turn == Turn.Player1 ? blackPlayer : whitePlayer;
		
		// remove the captured piece, if any, from the board
		if(captured != null){
			if(opposite != null)
				opposite.losePiece(captured);
			captured.setSpace(null);
		}
		
		// verify that this move didn't place it's own king in check
		if(!moving.notifyKingObservers()){
			
			// reset everything to how it was before the move.
			SpaceController capturedSpace = undoMove(move, captured, true);
			if(captured != null){
				opposite = captured.getColor() == PieceColor.White ? whitePlayer : blackPlayer;
				if(opposite != null)
					opposite.addPiece(captured);
				
				captured.setSpace(capturedSpace);
			}
			
			// return that this move would have placed the king in check
			message.setCheck();
			return message;
		}
		
		// check for pawn promotion
		if(moving.getType() == PieceType.Pawn && 
		   ((moving.getColor() == PieceColor.White && moving.getSpace().getRank() == Rank.Eight) ||
		   (moving.getColor() == PieceColor.Black && moving.getSpace().getRank() == Rank.One))){
			message.setPromotePawn();
		}
		
		setPreviousMove(move);
		return message;
	}
	
	/*
	 * Removes the given pawn from the board and the Player's PlayerSet
	 * and replaces it with a piece of the given type. NOTE: THIS FUNCTION
	 * ALWAYS RETURNS TRUE!!
	 */
	public boolean promotePawn(Piece moving, PieceType promotionType) {
		PieceColor color = moving.getColor();
		Player player = color == PieceColor.White ? whitePlayer : blackPlayer;
		Piece newPiece = (factory.makePiece(promotionType, promotionType, color));
		moving.getSpace().changePiece(newPiece, true);
		player.losePiece(moving);
		player.addPiece(newPiece);
		return true;
	}

	/*
	 * Move the piece at move.getInitialSpace() to move.getDestinationSpace().
	 * Returns the captured piece, if any.
	 */
	public static Piece movePiece(ActualMove move, Boolean repaint){
		Piece moving = move.getInitialSpace().getPiece();
		
		// set space variables
		SpaceController capturedSpace;
		SpaceController dest = move.getDestinationSpace();
		if(move.getClass() == MoveEnPassantRight.class || move.getClass() == MoveEnPassantLeft.class)
			capturedSpace = (moving.getColor() == PieceColor.White) ? dest.getSpaceBackward() : dest.getSpaceForward();
		else
			capturedSpace = dest;
		
		// get the destination piece
		Piece captured = capturedSpace.getPiece();
		
		// this is in case the capturedSpace is different than the destination space
		capturedSpace.changePiece(null, repaint);
		
		// move piece
		move.getDestinationSpace().changePiece(moving, repaint);
		move.getInitialSpace().changePiece(null, repaint);

		return captured;
	}
	
	/*
	 * Set's everything back to the way it was before the last move.
	 */
	public static SpaceController undoMove(ActualMove move, Piece captured, Boolean repaint){
		Piece moving = move.getDestinationSpace().getPiece();
		
		// set spaces
		SpaceController capturedSpace;
		SpaceController dest = move.getDestinationSpace();
		if(move.getClass() == MoveEnPassantRight.class || move.getClass() == MoveEnPassantLeft.class)
			capturedSpace = (moving.getColor() == PieceColor.White) ? dest.getSpaceBackward() : dest.getSpaceForward();
		else
			capturedSpace = dest;
		
		// move replace pieces
		move.getInitialSpace().changePiece(moving, repaint);
		dest.changePiece(null, repaint); // in case dest is different than captureSpace
		capturedSpace.changePiece(captured, repaint);
		
		return capturedSpace;
	}

	/*
	 * Verifies that universal constraints are met. Currently checks that there is a moving 
	 * piece, that it is the right color, and that the piece to be captured, if any, is of
	 * the opposite color. 
	 */
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn, ErrorMessage message) {
		SpaceController init = move.getInitialSpace();
		SpaceController dest = move.getDestinationSpace();
		Piece moving = init.getPiece();
		Piece captured = dest.getPiece();
		
		if(moving == null || moving.getColor() != PieceColor.values()[turn.ordinal()]){
			message.setWrongColorMoving();
			return false;
		}
		if(captured != null && captured.getColor() == moving.getColor()){
			message.setWrongColorCaptured();
			return false;
		}
		return true;
	}
	
	/*
	 * Checks to see if the given player has been put in Checkmate.
	 * Returns the given ErrorMessage object.
	 */
	public ErrorMessage checkForMate(Turn turn, ErrorMessage message) {
		Player player = turn == Turn.Player1 ? whitePlayer : blackPlayer;
		if(whitePlayer != null){
			player.checkForMate(message);
		}
		return message;
	}

	// public getters
	public BoardController getBoard() {
		return board;
	}
	
	public ArrayList<ErrorMessage> getMessages() {
		return messages;
	}
	
	public ActualMove getPreviousMove(){
		return prevMove;
	}
	
	// public setters
	public void setPreviousMove(ActualMove prevMove){
		this.prevMove = prevMove;
	}

	public Panel getMessageBox() {
		return messageBox;
	}

	public CardArea getCardArea() {
		return cardArea;
	}
	
	public TextArea getGameMessage() {
		return gameMessage;
	}

	public List getContEffects() {
		return contEffectsArea;
	}

	public CInfoArea getCInfoArea() {
		return cInfoArea;
	}

	public SelectedCardArea getSelectedCardArea() {
		return selectedCardArea;
	}

	public void addContEffect(ContEffect newContEffect){
		contEffects.add(newContEffect);
		if(contEffectsArea.getItems()[0] == noContEffectsItem){
			contEffectsArea.removeAll();
		}
		contEffectsArea.add(newContEffect.getName());
		newContEffect.highlightChange(this);
		newContEffect.endHighlightChange(this);
	}
	
/*	public void setEffectsArea(TextArea effectsArea) {
		this.effectsArea = effectsArea;
	}
*/	
	public void updateContEffects() {
		ArrayList<ContEffect> removed = new ArrayList<ContEffect>();
		for(ContEffect ce : contEffects){
			ce.updateContEffect(this);
			if(ce.endCondMet(this)){
				
				if(ce.getName() == contEffectsArea.getSelectedItem())
					ce.endHighlightChange(this);
				ce.endContEffect(this );
				removed.add(ce);
			}else if(ce.getName() == contEffectsArea.getSelectedItem()){
				ce.updateHighlighting(this);
			}
		}
		for(ContEffect r : removed){
			if(contEffects.indexOf(r) == contEffectsArea.getSelectedIndex())
				contEffectSelected = -1;
			contEffects.remove(r);
			contEffectsArea.remove(r.getName());
		}
		if(contEffectsArea.getItemCount() == 0)
			contEffectsArea.add(noContEffectsItem);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		//System.out.println("Item " + arg0.getItem().toString() + "'s state changed to " + (ItemEvent.SELECTED  == arg0.getStateChange() ? " Selected." : "Deseclected")); 
		if(contEffects.size() >0){
			ContEffect ce = contEffects.get((Integer)arg0.getItem());
			if(!arg0.getItem().equals(contEffectSelected)){
				for(ContEffect other : contEffects){
					if(other != ce){
						other.endHighlightChange(this);
					}
				}
				ce.highlightChange(this);
				contEffectSelected = (Integer) arg0.getItem();
			} else {
				//System.out.println("de-selecting item");
				ce.endHighlightChange(this);
				contEffectSelected = null;
				contEffectsArea.deselect((Integer)arg0.getItem()); 
			}
		}else
			contEffectsArea.deselect((Integer) arg0.getItem());
	}	
	
	public PlayerSet getPlayerSet(Turn turn){
		if(turn == Turn.Player1)
			return this.whitePlayer.getPlayerSet();
		else
			return this.blackPlayer.getPlayerSet();
	}
}
	
