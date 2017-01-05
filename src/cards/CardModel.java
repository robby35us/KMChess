package cards;

import java.util.Observable;

public class CardModel extends Observable {
	private KMCardInfo cInfo;

	public CardModel(KMCardInfo cInfo, CardView view){
		this.addObserver(view);
	}

	public KMCardInfo getCInfo() {
		return cInfo;
	}
	

    
}
