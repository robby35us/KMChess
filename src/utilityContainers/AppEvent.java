package utilityContainers;

import abstraction.SpaceModel;
import control.BoardController;
import control.Controller;
import control.SpaceController;
import interfaces.Visitor;

public class AppEvent implements Visitor{

	@Override
	public void visit(BoardController board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  void visit(SpaceController space) {
		space.accept(this);
	}

}
