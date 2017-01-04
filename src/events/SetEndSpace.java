package events;

import abstractClasses.AppEvent;
import controllers.BoardController;
import controllers.SpaceController;
import models.BoardModel;

public class SetEndSpace extends AppEvent<BoardController> {

	public SetEndSpace(SpaceController space){
		
		data = space;
	}
	
	@Override
	public void visit(BoardController board){
		this.data = (BoardModel) board.accept(this);
		((BoardModel) data).setEndSpace((SpaceController) getData());
	}

}