package cardEffects;

import game.GameState;

/*
 * The CardEffect class is the parent class for all card effects. It contains
 * some basic functionality that all card effects should have. Card effects
 * come in two types, instant only effects and those with continuous effects. Instant only
 * effects can subclass this class directly. Those with continuous effects must subclass
 * ContEffect instead.
 */
public abstract class CardEffect {

	/* 
	 * The name of this card. For use in dialog boxes.
	 * Sub classes are expected to set this field directly.
	 */
	private String cardName;
	
	protected CardEffect() {
		String[] words =  this.getClass().getSimpleName().split("_");
		cardName = words[0];
		for(int i = 1; i < words.length; i ++){
			cardName += " " + words[i];
		}
	}
	
	/*
	 * Returns the name of the CardEffect. 
	 */
	public String getName() {
		return cardName;
	}
	
	abstract public void initiateImmediateEffect(GameState gs);
	abstract public boolean playable(GameState gs);
}
