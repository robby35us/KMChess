package factory;

import definitions.Timing;
import knightmare.KMCardInfo;

public class CardFactory {
	
	private static String effect;
	private static Timing timing;
	private static String continuous;
	
	
	private CardFactory(){
		
	}
	
	public static KMCardInfo getCInfo(int set, int cardNum){
		if(set == 1) {
			switch (cardNum) {
			case 1: 
				effect = "\tChoose one of your pieces to become a "
						+ "\nmagnet. Until it moves or is captured, no"
						+ "\npiece of any color (except kings) in the"
						+ "\neight adjacent squares can move. A piece"
						+ "\nmay pass through these squares without"
						+ "\neffect, but one that stops there (whether or"
						+ "\nnot it makes a capture) becomes trapped.";
				timing = Timing.After;
				continuous = "Until the \"magnet\" moves or is captured.";
				return new KMCardInfo(set,cardNum, "Fatal Attraction", effect, 8, timing, continuous);
				
			case 2: 
				effect = "\tSwap the positions of one of your roooks and"
						+ "\none of your bishops.";
				timing = Timing.After;
				continuous = null;
				return new KMCardInfo(set,cardNum, "Cathedral", effect, 7, timing, continuous);
				
			case 3: 
				effect = "\tMove one of your pieces, except a pawn, as"
					+ "\nif it were a queen. You cannot capture a piece"
					+ "\nwith this move.";
				timing = Timing.Instead;
				continuous = null;
				return new KMCardInfo(set,cardNum, "Masquerade", effect, 5, timing, continuous);
			
			case 4: 
				effect = "\tEach player must npw use his move"
					+ "\nto capture one of his opponent's pieces, if he"
					+ "\ncan do so. This effects lasts until one of"
					+ "\nthe players cannot make a capture. You"
					+ "\nare not required to play a card to make a" 
					+ "\ncapture, even if you have a card that would"
					+ "\nlet you capture.";
				timing = Timing.After;
				continuous = "Until one player cannot make a capture without using a card.";
				return new KMCardInfo(set,cardNum, "Vendetta", effect, 3, timing, continuous);
				
			case 5: 
				effect = "\tOne of your opponent's pawns on your" 
					+ "\nhalf of the board switches allegiance."
					+ "\nReplace it with one of your own captured"
					+ "\npawns. His pawn is considered dead. It"
					+ "\ncannot return to play.";
				timing = Timing.Before;
				continuous = null;
				return new KMCardInfo(set,cardNum, "Betrayal", effect, 7, timing, continuous);
			
			case 6: 
				effect = "\tOn this move, one of your pawns can"
					+ "\ncapture by moving forward. It cannot"
					+ "\nmove forward unless it captures. If for any"
					+ "\nreason this pawn could already capture by"
					+ "\na forward move, this card lets ift capture"
					+ "\nwith a diagonal move instead.";
				timing = Timing.Instead;
				return new KMCardInfo(set,cardNum, "Breakthourh", effect, 6, timing, continuous);
			case 7: return new KMCardInfo(set,cardNum, "Mystic Shield", effect, 0, timing, continuous);
			case 8: return new KMCardInfo(set,cardNum, "Treason", effect, 0, timing, continuous);
			case 9: return new KMCardInfo(set,cardNum, "Hostage", effect, 0, timing, continuous);
			case 10: return new KMCardInfo(set,cardNum, "Anathema", effect, 0, timing, continuous);
			case 11: return new KMCardInfo(set,cardNum, "Peace Talks", effect, 0, timing, continuous);
			case 12: return new KMCardInfo(set,cardNum, "No Quarter", effect, 0, timing, continuous);
			case 13: return new KMCardInfo(set,cardNum, "Split Knight", effect, 0, timing, continuous);
			case 14: return new KMCardInfo(set,cardNum, "Truce", effect, 0, timing, continuous);
			case 15: return new KMCardInfo(set,cardNum, "Knightmare!", effect, 0, timing, continuous);
			case 16: return new KMCardInfo(set,cardNum, "Man Of Straw", effect, 0, timing, continuous);
			case 17: return new KMCardInfo(set,cardNum, "Dungeon", effect, 0, timing, continuous);
			case 18: return new KMCardInfo(set,cardNum, "Abduction", effect, 0, timing, continuous);
			case 19: return new KMCardInfo(set,cardNum, "Irrestitible Force", effect, 0, timing, continuous);
			case 20: return new KMCardInfo(set,cardNum, "Man-Trap", effect, 0, timing, continuous);
			case 21: return new KMCardInfo(set,cardNum, "Squaring The Circle", effect, 0, timing, continuous);
			case 22: return new KMCardInfo(set,cardNum, "Winged Victory", effect, 0, timing, continuous);
			case 23: return new KMCardInfo(set,cardNum, "Dark Mirror", effect, 0, timing, continuous);
			case 24: return new KMCardInfo(set,cardNum, "Merciless", effect, 0, timing, continuous);
			case 25: return new KMCardInfo(set,cardNum, "Disintegration", effect, 0, timing, continuous);
			case 26: return new KMCardInfo(set,cardNum, "Think Again!", effect, 0, timing, continuous);
			case 27: return new KMCardInfo(set,cardNum, "Ghostwald", effect, 0, timing, continuous);
			case 28: return new KMCardInfo(set,cardNum, "Crusade", effect, 0, timing, continuous);
			case 29: return new KMCardInfo(set,cardNum, "Onslaught", effect, 0, timing, continuous);
			case 30: return new KMCardInfo(set,cardNum, "Evangelists", effect, 0, timing, continuous);
			case 31: return new KMCardInfo(set,cardNum, "Revenge", effect, 0, timing, continuous);
			case 32: return new KMCardInfo(set,cardNum, "Evil Eye", effect, 0, timing, continuous);
			case 33: return new KMCardInfo(set,cardNum, "Fortification", effect, 0, timing, continuous);
			case 34: return new KMCardInfo(set,cardNum, "Long Jump", effect, 0, timing, continuous);
			case 35: return new KMCardInfo(set,cardNum, "Crab", effect, 0, timing, continuous);
			case 36: return new KMCardInfo(set,cardNum, "Curse", effect, 0, timing, continuous);
			case 37: return new KMCardInfo(set,cardNum, "Plots Within Plots", effect, 0, timing, continuous);
			case 38: return new KMCardInfo(set,cardNum, "Bog", effect, 0, timing, continuous);
			case 39: return new KMCardInfo(set,cardNum, "Riposte", effect, 0, timing, continuous);
			case 40: return new KMCardInfo(set,cardNum, "Cowardice", effect, 0, timing, continuous);
			case 41: return new KMCardInfo(set,cardNum, "Under Elf Hill", effect, 0, timing, continuous);
			case 42: return new KMCardInfo(set,cardNum, "Doomsayer", effect, 0, timing, continuous);
			case 43: return new KMCardInfo(set,cardNum, "Dubbing", effect, 0, timing, continuous);
			case 44: return new KMCardInfo(set,cardNum, "Heresy", effect, 0, timing, continuous);
			case 45: return new KMCardInfo(set,cardNum, "Guardian", effect, 0, timing, continuous);
			case 46: return new KMCardInfo(set,cardNum, "Earthquake", effect, 0, timing, continuous);
			case 47: return new KMCardInfo(set,cardNum, "Pacifism", effect, 0, timing, continuous);
			case 48: return new KMCardInfo(set,cardNum, "Toll", effect, 0, timing, continuous);
			case 49: return new KMCardInfo(set,cardNum, "Haunting Memories", effect, 0, timing, continuous);
			case 50: return new KMCardInfo(set,cardNum, "Forced March", effect, 0, timing, continuous);
			case 51: return new KMCardInfo(set,cardNum, "Figure Dance", effect, 0, timing, continuous);
			case 52: return new KMCardInfo(set,cardNum, "Passing In The Night", effect, 0, timing, continuous);
			case 53: return new KMCardInfo(set,cardNum, "Sanctuary", effect, 0, timing, continuous);
			case 54: return new KMCardInfo(set,cardNum, "Bombard", effect, 0, timing, continuous);
			case 55: return new KMCardInfo(set,cardNum, "Doppelganger", effect, 0, timing, continuous);
			case 56: return new KMCardInfo(set,cardNum, "Chaos", effect, 0, timing, continuous);
			case 57: return new KMCardInfo(set,cardNum, "Confabulation", effect, 0, timing, continuous);
			case 58: return new KMCardInfo(set,cardNum, "Lost Castle", effect, 0, timing, continuous);
			case 59: return new KMCardInfo(set,cardNum, "Tournament", effect, 0, timing, continuous);
			case 60: return new KMCardInfo(set,cardNum, "Charge!", effect, 0, timing, continuous);
			case 61: return new KMCardInfo(set,cardNum, "Fog Of War", effect, 0, timing, continuous);
			case 62: return new KMCardInfo(set,cardNum, "Vulture", effect, 0, timing, continuous);
			case 63: return new KMCardInfo(set,cardNum, "Panic", effect, 0, timing, continuous);
			case 64: return new KMCardInfo(set,cardNum, "Holy War", effect, 0, timing, continuous);
			case 65: return new KMCardInfo(set,cardNum, "Hidden Passage", effect, 0, timing, continuous);
			case 66: return new KMCardInfo(set,cardNum, "Challenge", effect, 0, timing, continuous);
			case 67: return new KMCardInfo(set,cardNum, "Forbidden City", effect, 0, timing, continuous);
			case 68: return new KMCardInfo(set,cardNum, "Resurrection", effect, 0, timing, continuous);
			case 69: return new KMCardInfo(set,cardNum, "Assassin", effect, 0, timing, continuous);
			case 70: return new KMCardInfo(set,cardNum, "Legacy", effect, 0, timing, continuous);
			case 71: return new KMCardInfo(set,cardNum, "Siege", effect, 0, timing, continuous);
			case 72: return new KMCardInfo(set,cardNum, "Rebirth", effect, 0, timing, continuous);
			case 73: return new KMCardInfo(set,cardNum, "Madman", effect, 0, timing, continuous);
			case 74: return new KMCardInfo(set,cardNum, "Fanatic", effect, 0, timing, continuous);
			case 75: return new KMCardInfo(set,cardNum, "Fireball", effect, 0, timing, continuous);
			case 76: return new KMCardInfo(set,cardNum, "Coup", effect, 0, timing, continuous);
			case 77: return new KMCardInfo(set,cardNum, "Annexation", effect, 0, timing, continuous);
			case 78: return new KMCardInfo(set,cardNum, "Nutrality", effect, 0, timing, continuous);
			case 79: return new KMCardInfo(set,cardNum, "Holy Quest", effect, 0, timing, continuous);
			case 80: return new KMCardInfo(set,cardNum, "Blessing", effect, 0, timing, continuous);
			default: return null;
			}
		}
		if(set == 2)
			return null;
		return null;
	}
}

