package models;

import java.util.Observable;

import cards.KMCardInfo;
import views.CardView;

public class CardModel extends Observable {
	private KMCardInfo cInfo;

	public CardModel(KMCardInfo cInfo, CardView view){
		this.addObserver(view);
		this.cInfo = cInfo;
	}

	public KMCardInfo getCInfo() {
		return cInfo;
	}
}
