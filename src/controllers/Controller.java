package controllers;

import java.util.ArrayList;

import interfaces.Visitable;

public abstract class Controller implements Visitable {
	protected Controller parentController = null;
	protected ArrayList<Controller> childControllers = null;
	public abstract void createChildTriad();
	public ArrayList<Controller> getChildControllers(){
		return childControllers;
	}
}
