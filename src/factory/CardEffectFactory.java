package factory;

import cardEffects.CardEffect;
import cardEffects.Cathedral;
import cardEffects.FatalAttraction;

public class CardEffectFactory {
	public static CardEffect getCEffect(int set, int cardNum){
		if(set == 1){
			switch (cardNum) {
				case 1: return new FatalAttraction();
				case 2: return new Cathedral();
				default: 
					return null;
			}
		}
		return null;
	}
}
