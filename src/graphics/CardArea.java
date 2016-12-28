package graphics;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cardEffects.CardEffect;
import cardEffects.ContEffect;
import factory.CardEffectFactory;
import game.GameState;
import io.ChessGame;
import knightmare.KMCard;
import knightmare.KMCardInfo;
import knightmare.KMCardSlot;
import knightmare.KMDeck;

public class CardArea extends Panel implements ActionListener  {

	private static final int HAND_SIZE = 6;
	
	private KMDeck deck;
	private KMDeck discard;
	private KMCardSlot[] hand;
	private Panel cardSlots;
	private Button noCard;

	//private static KMCardImages cardImages = new KMCardImages();
	
	public CardArea(KMDeck deck){
		this.setLayout(new FlowLayout());
		cardSlots = new Panel();
		cardSlots.setLayout(new GridLayout(3,2,10,10));
		this.deck = deck;
		this.discard = new KMDeck();
		this.hand = new KMCardSlot[HAND_SIZE];
		for(int i = 0 ; i < HAND_SIZE; i++) {
			this.hand[i] = new KMCardSlot(i);
			
			cardSlots.add(this.hand[i]);
		}
		this.add(cardSlots);

		
		//System.out.println(this.getComponentCount());
		
		noCard = new Button("Skip Playing A Card");
		noCard.addActionListener(this);
		noCard.setEnabled(false);
		this.add(noCard);
		//System.out.println(this.getComponentCount());
		
		noCardPlayed = false;
	}
	
	public Dimension getPreferredSize(){
		Container parent = getParent();
		Dimension d = new Dimension((int) parent.getSize().getWidth() /7 , (int) parent.getSize().getHeight()/2 - 20);
		this.getComponent(0).setPreferredSize(d);
		return new Dimension((int)d.getWidth(),(int) (d.getHeight() + 20));
	}
	public void paint(Graphics g){
		super.paint(g);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private volatile KMCard executingCard;

	@SuppressWarnings("unused")
	private volatile KMCard selectedCard;
	
	private volatile boolean noCardPlayed;

	public void setExecutingCard( KMCard card) {
		setSelectedCard(card);
		executingCard = card;
	}
	
	public void completeCardExecution(GameState gs){
		//System.out.println("Entered completeCardExecution");
		CardEffect effect = CardEffectFactory.getCEffect(executingCard.getCInfo().getSetNumber(),
												  executingCard.getCInfo().getCardNum());
		effect.initiateImmediateEffect(gs);
		//System.out.println(effect.getClass());
		//System.out.println(effect.getClass().getSuperclass());
		if(effect.getClass().getSuperclass().equals(ContEffect.class)){
			((ContEffect) effect).startContEffect(gs);
			gs.addContEffect((ContEffect) effect);
			//System.out.println("Continuing effect registered!");
		}
		
		KMCardSlot cardSlot= (KMCardSlot) executingCard.getParent();
		int index = cardSlot.getIndex();
		//cardSlots.remove(cardSlot);
		discard.addCInfo(executingCard.getCInfo());
		
		
		hand[index] = new KMCardSlot(index);
		KMCardInfo cInfo = deck.removeCInfo();
		if(cInfo != null){
			cardSlot.remove(executingCard);
			hand[index].add(new KMCard(KMCardImages.getImage(cInfo.getSetNumber(), cInfo.getCardNum()),cInfo));
		}else{
			cardSlots.remove(cardSlot);
			hand[index] = new KMCardSlot(index);
			cardSlots.add(hand[index]);
		}
		executingCard = null;
		repaint();
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

	public KMCard getExecutingCard() {
		return executingCard;
	}

	public boolean noCardPlayed() {
		return noCardPlayed;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Button Clicked");
		noCardPlayed = true;
	}

	public void activateSkipButton() {
		noCard.setEnabled(true);
		
	}

	public void resetNoCardButton() {
		noCardPlayed = false;
		noCard.setEnabled(false);
	}

}
