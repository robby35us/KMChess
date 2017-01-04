package testComponents;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.BoardController;
import controllers.SpaceController;

import java.util.ArrayList;

import enums.File;
import enums.Rank;
import enums.SpaceColor;

public class BoardTest extends BoardController {

	@Test
	public void testGetSpace(){
		BoardController board = new BoardController();
		ArrayList<SpaceController> spaces = new ArrayList<SpaceController>();
		SpaceColor color = SpaceColor.Gray;
		SpaceController space;
		for(Rank r : Rank.values())
			for(File f : File.values()){
				space = board.getSpace(r,f);
				assertEquals(color, space.getColor());
				assertFalse(spaces.contains(space));
				spaces.add(space);
				if(f != File.H){
					if(color == SpaceColor.White)
						color = SpaceColor.Gray;
					else
						color = SpaceColor.White;
				}	
			}
		assertEquals(64, spaces.size());
	}

	@Test
	public void testGetNextSpace(){
		BoardController board = new BoardController();
		ArrayList<SpaceController> spaces = new ArrayList<SpaceController>();
		for(Rank r : Rank.values())
			for(File f : File.values())
				spaces.add(board.getSpace(r, f));
		SpaceController space;
		for(int spaceNum = 0; spaceNum < spaces.size(); spaceNum++){
			space = spaces.get(spaceNum);
			int rankOrdinal = spaceNum / 8;
			int lowerRankOffset = -rankOrdinal;
			int upperRankOffset = 7 - rankOrdinal;
			int spaceNum2 = 0;
			for(int i = lowerRankOffset; i <= upperRankOffset; i++){
				int fileOrdinal = spaceNum % 8;
				int lowerFileOffset = -fileOrdinal;
				int upperFileOffset = 7 - fileOrdinal;
				for(int j = lowerFileOffset; j <= upperFileOffset; j++, spaceNum2++){
//					System.out.println("spaceNum: " + spaceNum);
//					System.out.println("spaceNum2: " + spaceNum2);
//					System.out.println("i: " + i);
//					System.out.println("j: " + j);
					assertEquals(spaces.get(spaceNum2), board.getNextSpace(i, j, space));
				}
			}
		}
	}
	
	@Test
	public void testGetNextSpaceOutOfBounds(){
		BoardController board = new BoardController();
		assertNull(board.getNextSpace(0, 0, null));
		assertNull(board.getNextSpace(-1, -1, board.getSpace(Rank.One, File.A)));
		assertNull(board.getNextSpace(1, 1, board.getSpace(Rank.Eight, File.H)));
		assertNull(board.getNextSpace(-1, 1, board.getSpace(Rank.One, File.H)));
		assertNull(board.getNextSpace(1, -1, board.getSpace(Rank.Eight, File.A)));
	}
	
	@Test
	public void testGetSpaceNullValue(){
		BoardController board = new BoardController();
		assertNull(board.getSpace(null, File.A));
		assertNull(board.getSpace(Rank.Eight, null));
		assertNull(board.getSpace(null, null));
	}
}
