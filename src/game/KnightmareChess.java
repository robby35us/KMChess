package game;

import java.io.IOException;

public class KnightmareChess {

	
	public static final int UNARMED = 0;
    public static final int ARMED = 1;
    public static final int OVER = 2;
    public static final int DISABLED = 3;
    
	public static void main(String[] args){
		GameState gs = new GameState();
		GameWindow gameWindow = new GameWindow(gs);
		try {
			//System.out.println("Game Started");
			Play.playGame(gameWindow,gs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
