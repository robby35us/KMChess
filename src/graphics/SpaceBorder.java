package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class SpaceBorder extends Border {
	
	private boolean display;
	public SpaceBorder( boolean display){
		this.setBorder(Color.BLACK);
		this.setBorderThickness(4);
		this.setType(LINE);
		this.setMargins(3);
		this.display = display;
	}
	
	public SpaceBorder(Color color) {
		this.setBorder(color);
		this.setBorderThickness(5);
		this.setType(LINE);
		this.setMargins(3);
		this.display = true;
	}

	public void paint(Graphics g, Color background, int x, int y, int width, int height){
		if(display == true){
			super.paint(g, background, x, y, width, height);
		}
	}
}