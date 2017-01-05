package cardEffects;

import definitions.CardEffect;

public class CardEffectFactory {
	public static CardEffect getCEffect(int set, int cardNum){
		if(set == 1){
			switch (cardNum) {
				case 1: return new Fatal_Attraction();
				case 2: return new Cathedral();
				case 3: return new Masquerade();
				case 4: return new Vendetta();
				default: 
					return null;
			}
		}
		return null;
	}
}
