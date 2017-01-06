package guiComponent;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import cardEffects.CardEffectFactory;
import cards.CardImageFactory;
import cards.CardInfo;
import cards.Deck;
import controllers.CardController;
import definitions.CardEffect;
import definitions.ContEffect;
import game.GameState;
import game.GameWindow;
import models.CardModel;
import views.CardView;

public class CardArea extends Panel implements ActionListener  {

	private static final int HAND_SIZE = 6;
	
	private Deck deck;
	private Deck discard;
	private ArrayList<CardController> hand;
	private Panel availableCards;
	private Button noCard;
	
	public CardArea(Deck deck){
		this.setLayout(new FlowLayout());
		availableCards = new Panel();
		availableCards.setLayout(new GridLayout(3,2,10,10));
		this.deck = deck;
		this.discard = new Deck();
		this.hand = new ArrayList<CardController>(HAND_SIZE);
		for(int i = 0 ; i < HAND_SIZE; i++) {
			CardController cc = new CardController(CardController.getEmpty());
			hand.add(cc);
			availableCards.add(cc.getView());
		}
		this.add(availableCards);

		
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
		this.getComponent(0).repaint();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private volatile CardController executingCard;
	
	private volatile boolean noCardPlayed;

	public void setExecutingCard(CardController card) {
			setSelectedCard(card);
			executingCard = card;
	}
	
	public void completeCardExecution(GameState gs){
		CardEffect effect = CardEffectFactory.getCEffect(executingCard.getCInfo().getSetNumber(),
												  executingCard.getCInfo().getCardNum());
		effect.initiateImmediateEffect(gs);
		if(effect.getClass().getSuperclass().equals(ContEffect.class)){
			((ContEffect) effect).startContEffect(gs);
			gs.addContEffect((ContEffect) effect);
		}
		//cardSlots.remove(cardSlot);
		discard.addCInfo(executingCard.getCInfo());
		availableCards.remove(executingCard.getView());
		hand.remove(executingCard);
		
		CardInfo cInfo = deck.removeCInfo();
		if(cInfo != null){
			CardView v = new CardView(CardImageFactory.getImage(cInfo.getSetNumber(), cInfo.getCardNum()));
			CardModel m = new CardModel(cInfo, v);
			CardController c = new CardController(m, v, this);
			hand.add(c);
			availableCards.add(v);
		}else{
			CardController cc = new CardController(CardController.getEmpty());
			hand.add(cc);
			cc.getView().setEnabled(false);
			availableCards.add(cc.getView());
		}
		executingCard = null;
		revalidate();
		repaint();
	}

	@SuppressWarnings("unused")
	private void createChildTriad() {
	}

	public void setSelectedCard(CardController card) {
		if(card == null)
			card = new CardController(CardController.getEmpty());
		((GameWindow) getParent()).setSelectedCard(card);
	}

	public void refreshHand() {
		int numToCheck = hand.size();
		for(int i = 0; i < numToCheck; i++){
			if(hand.get(i).isEmpty()){
				availableCards.remove(hand.get(i).getView());
				hand.remove(i);
				CardInfo cInfo = deck.removeCInfo();
				CardView v = new CardView(CardImageFactory.getImage(cInfo.getSetNumber(), cInfo.getCardNum()));
				CardModel m = new CardModel(cInfo, v);
				CardController c = new CardController(m, v, this);
				hand.add(c);
				availableCards.add(v);
				i--;
				numToCheck--;
			}
		}
		revalidate();
		repaint();
	}

	public CardController getExecutingCard() {
		return executingCard;
	}

	public boolean noCardPlayed() {
		return noCardPlayed;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("Button Clicked");
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
