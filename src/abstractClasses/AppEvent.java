package abstractClasses;

import control.BoardController;
import control.SpaceController;
import interfaces.Visitor;

public abstract class AppEvent implements Visitor{
	
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
	public abstract void trigger();
	
}
