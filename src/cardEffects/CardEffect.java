package cardEffects;

import game.GameState;

public abstract class CardEffect {

	protected String cardName;
	
	public String getName() {
		return cardName;
	}
	
	abstract public boolean initiateImmediateEffect(GameState gs);
}
