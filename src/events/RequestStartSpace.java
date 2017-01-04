package events;

import abstractClasses.AppEvent;
import abstraction.BoardModel;
import control.BoardController;

public class RequestStartSpace extends AppEvent {

	@Override
	public void visit(BoardController board){
		board.accept(this);
		((BoardModel) data);
	}
	
	@Override
	public void trigger() {
		// TODO Auto-generated method stub

	}

}
