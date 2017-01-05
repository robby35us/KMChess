package cards;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	public static final int FULL = 1;
	
	private ArrayList<CardInfo> cards;
	
	public Deck (){
		cards = new ArrayList<CardInfo>();
	}
	
	public Deck(int setIdentifier){
		this();
		if(setIdentifier == FULL){
			for(int i = 0; i < CardInfo.set1Size ; i++){
				this.addCInfo(CardInfoFactory.getCInfo(1, i + 1 ));
			}
			for(int j = 0; j < CardInfo.set2Size; j++){
				this.addCInfo(CardInfoFactory.getCInfo(1, j + 1));
			}
		}
		ArrayList<CardInfo> shuffled = new ArrayList<CardInfo>(cards.size());
		Random r = new Random();
		while(!cards.isEmpty()){
			int index = r.nextInt(cards.size());
			shuffled.add(cards.remove(index));
		}
		cards = shuffled;
	}
	
	public void addCInfo(CardInfo cInfo) {
		cards.add(cInfo);
	}
	
	public CardInfo removeCInfo(){
		if(!cards.isEmpty())
			return cards.remove(0);
		else
			return null;
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}

}
