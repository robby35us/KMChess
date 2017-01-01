package guiComponent;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;

public class MessageBox extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dimension getPreferredSize(){
		Container parent = getParent();
		return new Dimension((int)parent.getSize().getWidth(), (int)parent.getSize().getHeight() / 5);
	}
}
