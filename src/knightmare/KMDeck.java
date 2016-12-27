package knightmare;

import java.util.ArrayList;

import factory.CardFactory;

public class KMDeck {

	public static final int FULL = 1;
	
	private ArrayList<KMCardInfo> cards;
	
	public KMDeck (){
		cards = new ArrayList<KMCardInfo>();
	}
	
	public KMDeck(int setIdentifier){
		this();
		if(setIdentifier == FULL){
			for(int i = 0; i < KMCardInfo.set1Size ; i++){
				this.addCInfo(CardFactory.getCInfo(1, i + 1 ));
			}
			for(int j = 0; j < KMCardInfo.set2Size; j++){
				this.addCInfo(CardFactory.getCInfo(1, j + 1));
			}
			
		}
	}
	
	public void addCInfo(KMCardInfo cInfo) {
		cards.add(cInfo);
	}
	
	public KMCardInfo removeCInfo(){
		return cards.remove(0);
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}

}
