package control;

import java.util.ArrayList;

import interfaces.Visitable;
import utilityContainers.AppEvent;

public abstract class Controller implements Visitable {
	protected Controller parentController = null;
	protected ArrayList<Controller> childControllers = null;
	public abstract void createChildTriad();
	public abstract void handleEvent(AppEvent e);
}
