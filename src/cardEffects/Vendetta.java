package cardEffects;

import java.awt.Color;
import java.util.ArrayList;

import components.Piece;
import components.PlayerSet;
import components.Space;
import constraints.MustCapture;
import definitions.MoveType;
import definitions.Timing;
import definitions.Turn;
import game.GameState;
import game.MoveBuilder;
import graphics.SpaceBorder;
import knightmare.KMCard;
import utility.ErrorMessage;

public class Vendetta extends ContEffect {
	
	private MustCapture mc;
	
	private ArrayList<Space> movable;
	private ArrayList<Space> highlighted;
	private SpaceBorder highlight;
	private boolean	highlightingOn;
	public Vendetta () {
		cardName = "Vendetta";
		highlight  = new SpaceBorder(Color.GREEN);
	}

	private void addMcToAll(GameState gs){
		for(Turn turn : Turn.values()){
			PlayerSet set = gs.getPlayerSet(turn);
			for(Piece p : set){
				ArrayList<MoveType> mts = p.getMoveTypes();
				for(MoveType mt : mts){
					if(!p.getConstraints(mt).contains(mc))
						p.getConstraints(mt).add(mc);
				}
			}
		}
	}
	
	@Override
	public void startContEffect(GameState gs) {
		mc = new MustCapture();
		addMcToAll(gs);
		resetMovable(gs);
		/*gs.getBoard().infoBox("Vendetta is an active card. Each player must now" +
				  "\nuse his move to capture one of his opponent's pieces." +
				  "\nThis effect lasts until one of the players cannot capture" +
				  "\nwithout the use of a card.", cardName);
		*/
	}

	@Override
	public void updateContEffect(GameState gs) {
		addMcToAll(gs);
		resetMovable(gs);
	}

	public void updateHighlighting(GameState gs){
		if(highlightingOn){
			for(Space s : movable){
				if(!highlighted.contains(s)){
					highlighted.add(s);
					s.setArmedBorder(highlight);
					s.setUnarmedBorder(highlight);
					s.setButtonState(Space.ARMED);
					s.repaint();
				}
			}
			ArrayList<Space> unHighlight = new ArrayList<Space>();
			for(Space s : highlighted){
				if(!movable.contains(s)){
					s.setArmedBorder(Space.defaultArmedBorder);
					s.setUnarmedBorder(Space.defaultUnarmedBorder);
					s.setButtonState(Space.UNARMED);
					unHighlight.add(s);
					s.repaint();
				}
			}
			for(Space s : unHighlight){
				highlighted.remove(s);
			}
		}
	}
	private void resetMovable(GameState gs) {
		movable = new ArrayList<Space>();
		PlayerSet current, other;
		if(KMCard.CurrentTiming == Timing.Before){
			current = gs.getPlayerSet(gs.getBoard().getTurn());
			other = gs.getPlayerSet(gs.getBoard().getTurn() == Turn.Player1 ? Turn.Player2 : Turn.Player1);
		}else{ 
			other = gs.getPlayerSet(gs.getBoard().getTurn());
			current = gs.getPlayerSet(gs.getBoard().getTurn() == Turn.Player1 ? Turn.Player2 : Turn.Player1);
		}
		for(Piece c : current){
			//System.out.println("Checking if " + c.getColor() +  c.getType() + " can attack: ");
			for(Piece o: other){
				//System.out.println("\tthe " + o.getColor() + o.getType());
				ErrorMessage message = new ErrorMessage();
				if(MoveBuilder.buildMoveObject(c.getSpace(), o.getSpace(), gs, message) != null){
					movable.add(c.getSpace());
					//System.out.println("Reset Movable: Adding " + c + " to movable.");
					break;
				}
			}
		}
	}

	@Override
	public boolean endCondMet(GameState gs) {
		resetMovable(gs);
		return movable.isEmpty();
	}

	@Override
	public void endContEffect(GameState gs) {
		for(Turn turn : Turn.values()){
			PlayerSet set = gs.getPlayerSet(turn);
			for(Piece p : set){
				ArrayList<MoveType> mts = p.getMoveTypes();
				for(MoveType mt : mts){
					p.getConstraints(mt).remove(mc);
				}
			}
		}
		String player;
		if(KMCard.CurrentTiming == Timing.Before)
			player = gs.getBoard().getTurn().toString();
		else
			player = gs.getBoard().getTurn() == Turn.Player1 ? Turn.Player2.toString() : Turn.Player1.toString();
		gs.getBoard().infoBox(player + " is not able to make a capture. Vendetta is no longer in affect.", cardName);
	}

	@Override
	public void highlightChange(GameState gs) {
		highlightingOn = true;
		highlighted = new ArrayList<Space>();
		for(Space space: movable){
			space.setArmedBorder(highlight);
			space.setUnarmedBorder(highlight);
			space.setButtonState(Space.ARMED);
			highlighted.add(space);
			space.repaint();
		}
		gs.getBoard().infoBox("Vendetta is an active card. Each player must now" +
				  "\nuse his move to capture one of his opponent's pieces." +
				  "\nThis effect lasts until one of the players cannot capture" +
				  "\nwithout the use of a card.", cardName);
	}

	@Override
	public void endHighlightChange(GameState gs) {
		highlightingOn = false;
		for(Space s : highlighted){
			s.setArmedBorder(Space.defaultArmedBorder);
			s.setUnarmedBorder(Space.defaultUnarmedBorder);
			s.setButtonState(Space.UNARMED);
			s.repaint();
		}
		highlighted.clear();
	}

	

}
