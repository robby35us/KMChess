package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import knightmare.KMCardInfo;



public class KMCardImages {
	private static String[] KMChess1Cards = new String[KMCardInfo.set1Size];
	private static String[] KMChess2Cards = new String[KMCardInfo.set2Size];

	static {
		for(int i = 0; i < KMChess1Cards.length; i++){
			KMChess1Cards[i] = "C:\\Users\\robby35us\\Pictures\\KMChess\\KMChess1" + (i + 1) + ".jpg";
		}
		//for(int i = 0; i < KMChess2Cards.length; i++){
		//	KMChess2Cards[i] = "C:\\Users\\robby35us\\Pictures\\KMChess\\KMChess2" + (i + 1) + ".jpg";
		//}
	}
	
	public static BufferedImage getImage(int setNum, int cardNum){
		String[] set; 
		if(setNum == 1)
			set = KMChess1Cards;
		else if(setNum == 2)
			set = KMChess2Cards;
		else
			return null;
		try {
			return ImageIO.read(new File(set[cardNum - 1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}


