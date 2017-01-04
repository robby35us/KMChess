package interfaces;

import control.BoardController;
import control.SpaceController;

public interface Visitor {
	
	public void visit(BoardController board);
	public void visit(SpaceController space);
}
