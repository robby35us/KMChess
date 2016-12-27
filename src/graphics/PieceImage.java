package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import definitions.PieceType;

public class PieceImage extends Image {
	PieceType pt;								
	public PieceImage (PieceType pt){
		this.pt = pt;
	}
	
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String arg0, ImageObserver arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
