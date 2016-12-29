package cardEffects;

import game.GameState;

public abstract class ContEffect extends CardEffect {
	@Override
	//ContEffect classes only need to override as needed
	public void initiateImmediateEffect(GameState gs) {

	}
	
	protected boolean contEffectEnded = false;
	
	 
	abstract public void startContEffect(GameState gs);
	
	abstract public void updateContEffect(GameState gs);
	
	abstract public boolean endCondMet(GameState gs);
	
	abstract public void endContEffect(GameState gs);


	abstract public void highlightChange(GameState gs);

	abstract public void endHighlightChange(GameState gs);
	
}
