package cardEffects;

import game.GameState;

public abstract class ContEffect extends CardEffect {
	
	
	protected boolean contEffectEnded = false;
	protected String contEffectName;
	 
	abstract public void startContEffect(GameState gs);
	
	abstract public void updateContEffect(GameState gs);
	
	abstract public boolean endCondMet(GameState gs);
	
	abstract public void endContEffect(GameState gs);

	public String getName() {
		return contEffectName;
	}

	abstract public void highlightChange(GameState gs);

	abstract public void endHighlightChange(GameState gs);
	
}
