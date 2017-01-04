package interfaces;

import abstractClasses.AppEvent;

public interface Visitable {
	public void accept(AppEvent e);
}
