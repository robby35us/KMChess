package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class CardBorder extends Border {
	
	private boolean display;
	public CardBorder( boolean display){
		this.setBorder(Color.BLACK);
		this.setBorderThickness(4);
		this.setType(LINE);
		this.setMargins(0);
		this.display = display;
	}
	
	public void paint(Graphics g, Color background, int x, int y, int width, int height){
		if(display == true){
			super.paint(g, background, x, y, width, height);
		}
	}
}