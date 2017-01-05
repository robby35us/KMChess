package cards;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class CardImageFactory {
	private static final String imageLoc = "C:\\Users\\robby35us\\Pictures\\KMChess\\KMChess" ;
	private static final String dotJPG = ".jpg";
	
	public static BufferedImage getImage(int setNum, int cardNum){
		if(setNum != 1) // should be changed to include 2 at some point
			return null;
		try {
			return ImageIO.read(new File(imageLoc + setNum + (cardNum) + dotJPG));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}


