package events;

import controllers.BoardController;
import definitions.AppEvent;
import models.BoardModel;

public class GetEndSpace extends AppEvent<BoardController> {

	@Override
	public void visit(BoardController board){
		this.data = (BoardModel) board.accept(this);
		this.data = ((BoardModel) data).getEndSpace();
	}

}