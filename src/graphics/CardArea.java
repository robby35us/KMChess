package graphics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import io.ChessGame;
import knightmare.KMCard;
import knightmare.KMCardInfo;
import knightmare.KMCardSlot;
import knightmare.KMDeck;

public class CardArea extends Panel {

	private static final int HAND_SIZE = 6;
	
	private KMDeck deck;
	private KMDeck discard;
	private KMCardSlot[] hand;

	//private static KMCardImages cardImages = new KMCardImages();
	
	public CardArea(KMDeck deck){
		this.setLayout(new GridLayout(3,2,10,10));
		this.deck = deck;
		this.discard = new KMDeck();
		this.hand = new KMCardSlot[HAND_SIZE];
		for(int i = 0 ; i < HAND_SIZE; i++) {
			this.hand[i] = new KMCardSlot(i);
			add(this.hand[i]);
		}
	}
	
	public Dimension getPreferredSize(){
		Container parent = getParent();
		return new Dimension((int) parent.getSize().getWidth() /7 , (int) parent.getSize().getHeight()/2);
		
	}
	public void paint(Graphics g){
		super.paint(g);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private volatile KMCard executingCard;

	private volatile KMCard selectedCard;

	public void setExecutingCard( KMCard card) {
		setSelectedCard(card);
		executingCard = card;
	}
	
	public void completeCardExecution(){
		KMCardSlot cardSlot= (KMCardSlot) executingCard.getParent();
		int index = cardSlot.getIndex();
		this.remove(cardSlot);
		discard.addCInfo(executingCard.getCInfo());
		hand[index] = new KMCardSlot(index);
		KMCardInfo cInfo = deck.removeCInfo();
		hand[index].add(new KMCard(KMCardImages.getImage(cInfo.getSetNumber(), cInfo.getCardNum()),cInfo));
	}

	public void setSelectedCard(KMCard card) {
		selectedCard = card;
		((ChessGame) getParent()).setSelectedCard(card);
	}

	public void refreshHand() {
		//System.out.println("Refreshing the hand.");
		for(int i = 0; i < HAND_SIZE; i++){
			if(hand[i].isEmpty()){
				//System.out.println("Grabing new card for hand!");
				KMCardInfo cInfo = deck.removeCInfo();
				hand[i].replaceCard(new KMCard(KMCardImages.getImage(cInfo.getSetNumber(), cInfo.getCardNum()), cInfo));
			}
		}
		repaint();
	}
}
