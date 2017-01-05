package definitions;

import controllers.BoardController;
import controllers.SpaceController;
import definitions.Controller;

public abstract class AppEvent<T extends Controller> implements Visitor{
	
	protected Object data;
	
	public void visit(BoardController board){
		
	}
	
	public void visit(SpaceController space){
		
	}
	
	public void setData(Object o){
		data = o;
	}
	
	public Object getData(){
		return data;
	}
	
	public boolean trigger(Controller controller, Class<T> type){
		if(controller.getClass() == type){
			visit((BoardController) controller);
			return true;
		} else  
			for(Controller c : controller.getChildControllers())
				if(trigger(c, type))
					return true;
		return false;
	}
	
}
