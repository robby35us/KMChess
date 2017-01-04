package game;

import java.io.IOException;

public class KnightmareChess {

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
