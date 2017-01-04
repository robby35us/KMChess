package interfaces;

import controllers.BoardController;
import controllers.SpaceController;

public interface Visitor {
	
	public void visit(BoardController board);
	public void visit(SpaceController space);
}
