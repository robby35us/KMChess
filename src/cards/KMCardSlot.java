package cards;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

public class KMCardSlot extends Panel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardController card;
	private int index;
	private boolean isEmpty;
	
	public KMCardSlot (int index) {
		setLayout(new GridLayout(1,1,10,10));
		setBackground(Color.LIGHT_GRAY);
		this.setCard(CardController.getEmpty());
		this.add(card.getView());
		this.setIndex(index);
		this.isEmpty = true;
	}

	private void setCard(CardController card) {
		this.card = card;
	}
	
	public CardController getCard(){
		return card;
	}
	
	public CardController replaceCard(CardController newCard) {
		isEmpty = false;
		CardController oldCard = card;
		card = newCard;
		this.removeAll();
		this.add(card.getView());
		this.repaint();
		return oldCard;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public int getIndex() {
		return index;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

}
