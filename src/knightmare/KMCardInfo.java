package knightmare;

import definitions.Timing;

public class KMCardInfo {
	private int setNum;
	private int cardNum;
	private String name;
	private int points;
	private String effect;
	private Timing timing;
	private String contEffect;
	
	public static final int set1Size = 6; //80;
	public static final int set2Size = 0;//78;
	
	
	public KMCardInfo(int setNum, int cardNum,
			String name, String effect, int points,
			Timing whenPlayed, String contEffect){
		this.setNum = setNum;
		this.cardNum = cardNum;
		this.name = name;
		this.effect = effect;
		
		this.points = points;
		this.timing = whenPlayed;
		this.contEffect = contEffect;
	}
	
	public int getSetNumber() {
		return setNum;
	}

	public int getCardNum() {
		return cardNum;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEffect(){
		return effect;
	}
	
	public Timing getTiming(){
		return timing;
	}
	
	public String getContEffect() {
		return contEffect;
	}
	
	public int getPoints() {
		return points;
	}
}
