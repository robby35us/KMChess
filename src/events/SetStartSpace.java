package events;

import abstractClasses.AppEvent;
import controllers.BoardController;
import controllers.SpaceController;
import models.BoardModel;

public class SetStartSpace extends AppEvent<BoardController> {

	public SetStartSpace(SpaceController space){
		
		data = space;
	}
	
	@Override
	public void visit(BoardController board){
		this.data = (BoardModel) board.accept(this);
		((BoardModel) data).setStartSpace((SpaceController) getData());
	}

}