package cardEffects;

import game.GameState;

public abstract class ContEffect extends CardEffect {
	abstract public void startContEffect(GameState gs);
	
	abstract public void updateContEffect(GameState gs);
	
	abstract public boolean endCondMet(GameState gs);
	
	abstract public void endContEffect(GameState gs);
}
