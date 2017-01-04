package control;

import java.util.ArrayList;

import abstractClasses.AppEvent;
import interfaces.Visitable;

public abstract class Controller implements Visitable {
	protected Controller parentController = null;
	protected ArrayList<Controller> childControllers = null;
	public abstract void createChildTriad();
	public abstract void handleEvent(AppEvent e);
}
