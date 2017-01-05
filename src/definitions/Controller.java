package definitions;


import java.util.ArrayList;

public abstract class Controller implements Visitable {
	protected Controller parentController = null;
	protected ArrayList<Controller> childControllers = null;
	public abstract void createChildTriad();
	public ArrayList<Controller> getChildControllers(){
		return childControllers;
	}
}
