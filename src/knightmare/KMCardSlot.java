package knightmare;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

public class KMCardSlot extends Panel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private KMCard card;
	private int index;
	private boolean isEmpty;
	
	public KMCardSlot (int index) {
		setLayout(new GridLayout(1,1,10,10));
		setBackground(Color.LIGHT_GRAY);
		this.setCard(KMCard.getEmpty());
		this.add(card);
		this.setIndex(index);
		this.isEmpty = true;
	}

	private void setCard(KMCard card) {
		this.card = card;
	}
	
	public KMCard getCard(){
		return card;
	}
	
	public KMCard replaceCard(KMCard newCard) {
		isEmpty = false;
		KMCard oldCard = card;
		card = newCard;
		this.removeAll();
		this.add(card);
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
