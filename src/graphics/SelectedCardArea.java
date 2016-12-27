package graphics;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SelectedCardArea extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage selectedCard;
	
	public SelectedCardArea() {
		try {
			selectedCard = ImageIO.read(new java.io.File("C:\\Users\\robby35us\\Pictures\\KMChess\\Back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  BufferedImage getCard() {
		return selectedCard;
	}
	
	public void setCard(int setNum, int cardNum){
		selectedCard = KMCardImages.getImage(setNum, cardNum);
		this.repaint();
	}
	
	public Dimension getPreferredSize(){
		Container parent = getParent();
		return new Dimension((int) parent.getWidth() / 6 , (int) parent.getHeight() / 2);
	}
	
	public void paint(Graphics g){
		if(selectedCard == null){
			super.paint(g);
			return;
		}
		//selectedCard = (selectedCard.getScaledInstance((int)this.getPreferredSize().getWidth(), (int)this.getPreferredSize().getHeight(),Image.SCALE_DEFAULT));
		try {
				
				//BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                //System.out.println(this.getWidth());
                //System.out.println(this.getHeight());
                //Image img = images[buttonState].getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
			Graphics2D g2d = (Graphics2D) g;
			 g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(selectedCard, 0, 0, this.getWidth(), this.getHeight(),
                						  0, 0, selectedCard.getWidth(), selectedCard.getHeight(), this );
                
               
               
               // System.out.println(img.getWidth(this));
                //System.out.println(img.getHeight(this));
            }
		catch(Error e){
			System.out.println(e.getMessage());
        }
	}
}