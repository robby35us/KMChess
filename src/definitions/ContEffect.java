package definitions;

import game.GameState;

/*
 * The base class for all continuous effect classes. Can also be used for short term/
 * temporary effects. There are two types of abstract methods that must be defined by
 * subclasses of ContEffect: continuous effect management methods and highlighting, or
 * effect reminder/hints, management methods. These methods will be called by the 
 * contEffectManager.
 */
public abstract class ContEffect extends CardEffect {
	
	/*
	 * This is the place holder method for the non-continuous portion of a continuous effect.
	 * It does nothing and exists only so that continuous effects that do not have 
	 * a separate immediate effect do not have to implement this abstract method from 
	 * CardEffect. It is called when a card effect is executed and before startContEffect() 
	 * is called. It can assume that it will be called only once in the lifetime of the 
	 * effect object. 
	 */
	public void initiateImmediateEffect(GameState gs) {
		//do nothing
	}
	

	/*
	 * The continuous effect management methods. An effect is started once. Then
	 * updateContEffect() and endCondMet() are called periodically, in that order. If 
	 * endCondMet() ever returns true, endContEffect() will be called and no other 
	 * methods will ever be called on the object afterwards. 
	 */
	
	// Called once after the immediate effect is initiated.
	abstract public void startContEffect(GameState gs);
	
	// Can be called multiple times and at any time in the turn cycle.
	abstract public void updateContEffect(GameState gs);
	
	// Can be called multiple times and at any time in the turn cycle. 
	abstract public boolean endCondMet(GameState gs);
	
	// Once called, no other methods will be called on the object.
	abstract public void endContEffect(GameState gs);


	
	
	/* Called once when the object is created and then again every time the effect is 
	 * clicked on in the continuous effects list.
	 */
	abstract public void highlightChange(GameState gs);

	/* Can and will be called multiple times throughout the object life-cycle, whether 
	 * or not the object is highlighted. The object is responsible for producing the 
	 * correct behavior in either case.
	 */ 
	abstract public void updateHighlighting(GameState gs);
	
	// Called once after the object is created and then again every time the effect is
	// de-selected in the continuous effects list.
	abstract public void endHighlightChange(GameState gs);

	
	
}
