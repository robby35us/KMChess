package models;

import java.util.Observable;

import cards.CardInfo;
import views.CardView;

public class CardModel extends Observable {
	private CardInfo cInfo;

	public CardModel(CardInfo cInfo, CardView view){
		this.addObserver(view);
		this.cInfo = cInfo;
	}

	public CardInfo getCInfo() {
		return cInfo;
	}
}
