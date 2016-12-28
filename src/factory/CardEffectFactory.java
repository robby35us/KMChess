package factory;

import cardEffects.CardEffect;
import cardEffects.FatalAttraction;

public class CardEffectFactory {
	public static CardEffect getCEffect(int set, int cardNum){
		if(set == 1){
			switch (cardNum) {
				case 1: return new FatalAttraction();
				default: 
					return null;
			}
		}
		return null;
	}
}
