package cardEffects;

import game.GameState;

/*
 * The CardEffect class is the parent class for all card effects. It defines
 * some basic functionality that all card effects should have. Card effects
 * come in two types, instant only effects and those with continuous effects. Instant only
 * effects can subclass this class directly. Those with continuous effects must subclass
 * ContEffect instead. Note that effects are a separate entity from the card object 
 * that shares the same name field. Card effect objects only exist when a card is executed.
 */
public abstract class CardEffect {

	/* 
	 * The name of this card. For use in dialog boxes.
	 * 
	 */
	private String cardName;
	
	/*
	 * Currently just sets the name of the card based on the subclass's name.
	 * Subclasses should be named the same as the card name with special characters
	 * removed and and ' ' characters replaced with a '_'.
	 */
	protected CardEffect() {
		String[] words =  this.getClass().getSimpleName().split("_");
		cardName = words[0];
		for(int i = 1; i < words.length; i ++){
			cardName += " " + words[i];
		}
	}
	
	/*
	 * Returns the name of the CardEffect. Should be used as the title for 
	 * card effect related dialog boxes.
	 */
	public String getName() {
		return cardName;
	}
	
	/*
	 * This is the action method for an effect. It is called when a card effect
	 * is executed. It is called for all effects, whether continuous or not. 
	 * It can assume that it will be called only once in the lifetime of the 
	 * effect object.
	 */
	abstract public void initiateImmediateEffect(GameState gs);
	
	/*
	 * This function is called when a card is selected to be played. This method 
	 * should not cause any of the effect's state to change, as it can be called
	 * multiple times in the same turn.
	 */
	abstract public boolean playable(GameState gs);
}
