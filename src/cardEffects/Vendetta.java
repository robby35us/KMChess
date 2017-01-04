package cardEffects;

import java.awt.Color;
import java.util.ArrayList;

import abstractClasses.ContEffect;
import cards.KMCard;
import constraints.MustCapture;
import enums.MoveType;
import enums.Timing;
import enums.Turn;
import game.GameState;
import game.GameWindow;
import game.MoveBuilder;
import gameComponents.PlayerSet;
import graphics.SpaceBorder;
import pieces.Piece;
import control.SpaceController;
import utilityContainers.ErrorMessage;
import static constants.Constants.*;

public class Vendetta extends ContEffect {
	
	private MustCapture mc;
	
	private ArrayList<SpaceController> movable;
	private ArrayList<SpaceController> highlighted;
	private SpaceBorder highlight;
	private boolean	highlightingOn;
	public Vendetta () {
		highlight  = new SpaceBorder(Color.GREEN);
	}

	private void addMcToAll(GameState gs){
		for(Turn turn : Turn.values()){
			PlayerSet set = gs.getPlayerSet(turn);
			for(Piece p : set){
				ArrayList<MoveType> mts = p.getMoveTypes();
				for(MoveType mt : mts){
					if(mt != MoveType.NonStandard && !p.getConstraints(mt).contains(mc))
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
			for(SpaceController s : movable){
				if(!highlighted.contains(s)){
					highlighted.add(s);
					//s.getSpaceView().setArmedBorder(highlight);
					s.getSpaceView().setUnarmedBorder(highlight);
					//s.armSpace();
					s.getSpaceView().repaint();
				}
			}
			ArrayList<SpaceController> unHighlight = new ArrayList<SpaceController>();
			for(SpaceController s : highlighted){
				if(!movable.contains(s)){
					//s.getSpaceView().setArmedBorder(defaultArmedBorder);
					s.getSpaceView().setUnarmedBorder(defaultUnarmedBorder);
					//s.unarmSpace();
					unHighlight.add(s);
					s.getSpaceView().repaint();
				}
			}
			for(SpaceController s : unHighlight){
				highlighted.remove(s);
			}
		}
	}
	private void resetMovable(GameState gs) {
		movable = new ArrayList<SpaceController>();
		PlayerSet current, other;
		if(KMCard.CurrentTiming == Timing.Before){
			current = gs.getPlayerSet(gs.getTurn());
			other = gs.getPlayerSet(gs.getTurn() == Turn.Player1 ? Turn.Player2 : Turn.Player1);
		}else{ 
			other = gs.getPlayerSet(gs.getTurn());
			current = gs.getPlayerSet(gs.getTurn() == Turn.Player1 ? Turn.Player2 : Turn.Player1);
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
			player = gs.getTurn().toString();
		else
			player = gs.getTurn() == Turn.Player1 ? Turn.Player2.toString() : Turn.Player1.toString();
		GameWindow.globalGW.infoBox(player + " is not able to make a capture. Vendetta is no longer in affect.", getName());
	}

	@Override
	public void highlightChange(GameState gs) {
		highlightingOn = true;
		highlighted = new ArrayList<SpaceController>();
		for(SpaceController space: movable){
			//space.getSpaceView().setArmedBorder(highlight);
			space.getSpaceView().setUnarmedBorder(highlight);
			//space.armSpace();
			highlighted.add(space);
			space.getSpaceView().repaint();
		}
		GameWindow.globalGW.infoBox("Vendetta is an active card. Each player must now" +
				  "\nuse his move to capture one of his opponent's pieces." +
				  "\nThis effect lasts until one of the players cannot capture" +
				  "\nwithout the use of a card.", getName());
	}

	@Override
	public void endHighlightChange(GameState gs) {
		highlightingOn = false;
		for(SpaceController s : highlighted){
			//s.getSpaceView().setArmedBorder(defaultArmedBorder);
			s.getSpaceView().setUnarmedBorder(defaultUnarmedBorder);
			//s.unarmSpace();
			s.getSpaceView()
			.repaint();
		}
		highlighted.clear();
	}

	@Override
	public boolean playable(GameState gs) {
		return true;
	}

	

}
