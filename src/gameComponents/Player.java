package gameComponents;
import enums.PieceColor;
import pieces.King;
import pieces.Piece;
import utilityContainers.ErrorMessage;

/*
 * Represents a single player in the game of chess. A player
 * has a set of pieces (a PlayerSet object), a color, and it
 * has a reference to the opposite king. Currently, it only 
 * serves as a buffer for the PlayerSet object, so that that
 * object doesn't have to keep track of anything outside of 
 * just the pieces that it contains.
 */
public class Player {
	PieceColor color;
	PlayerSet aliveSet;
	PlayerSet capturedSet;
	private King opposingKing;
	
	public Player(PlayerSet set, PieceColor color, King opposingKing){
		this.color = color;
		this.aliveSet = set;
		this.capturedSet = new PlayerSet(color);
		this.opposingKing = opposingKing;
		
		// All pieces register with the OpposingKing as an
		// observer so they can let the OpposingKing know 
		// if they can put him in check.
		for(Piece p : set){
			opposingKing.registerOpposingPieceObserver(p);
		}
	}
	
	// public getters
	public PieceColor getColor(){
		return color;
	}
	
	/*
	 * Adds a piece object to the player. Not only does
	 * it add the piece to the underlying set, but it 
	 * registers the piece as an observer with the 
	 * opposing king object and registers this player's
	 * king as an observer of this piece. There are no
	 * restrictions on the types of pieces added, as per
	 * Knightmare chess rules.
	 */
	public void addPiece(Piece piece) {
		opposingKing.registerOpposingPieceObserver(piece);
		piece.registerKingObserver(aliveSet.getKing());
		aliveSet.addPiece(piece);
		capturedSet.removePiece(piece);
	}
	
	/*
	 * Removes a piece from the underlying set, as well
	 * as removes it from the opposingKing's observer list
	 * and removes this player's king from the observer list
	 * of this piece. Behavior is not guaranteed if the piece
	 * isn't in the set.
	 */
	public boolean losePiece(Piece piece){
		opposingKing.removeOpposingPieceObserver(piece);
		piece.removeKingObserver(aliveSet.getKing());
		capturedSet.addPiece(piece);
		return aliveSet.removePiece(piece);
	}
	
	/*
	 * Checks this player to see if he has been checkmated
	 * by searching for a valid move. Will call setMate
	 * on the passed in ErrorMessage object if there is a 
	 * mate. NOTE: CURRENTLY MIGHT ALSO RETURN MATE IF THERE
	 * IS A STALEMATE. THIS IS NOT DESIRED BEHAVIOR.
	 */
	public void checkForMate(ErrorMessage message) {
		boolean isMate = true;
		for(Piece p : aliveSet){
			if(p.checkForValidMove()){
				// if this is piece has a valid move, then it's not check mate
				isMate = false;
				break;
			}
		}
		if(isMate)
			message.setMate();
	}
	
	public PlayerSet getPlayerSet(){
		return aliveSet;
	}
	
	public PlayerSet getCapturedSet(){
		return capturedSet;
	}
}
