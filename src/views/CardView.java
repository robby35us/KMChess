package views;

import static definitions.Constants.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import graphics.CardBorder;

public class CardView extends Canvas implements Observer{

	private static final long serialVersionUID = 1L;
    public static final Dimension MINIMUM_SIZE = new Dimension(100,190);
    public static final Dimension MAXIMUM_SIZE = new Dimension(100,190);	

    private int buttonState = UNARMED;
	private BufferedImage[] images = new BufferedImage[4];
	private MediaTracker tracker;
	private CardBorder[] borders = new CardBorder[4];

	@SuppressWarnings("unused")
	private boolean generatedDisabled;

	public CardView(BufferedImage image){
		setBackground(Color.GRAY);
		tracker = new MediaTracker( this );
		setUnarmedBorder( defaultUnarmedCardBorder );
		setArmedBorder( defaultArmedCardBorder );
		this.setMinimumSize(MINIMUM_SIZE);
		this.setMaximumSize(MAXIMUM_SIZE);

        setImage(UNARMED, image );
        setImage(OVER, image);
        setImage(ARMED, image);
        setImage(DISABLED, image);
	}
	
    private void setArmedBorder(CardBorder border) {
    	borders[ARMED] = border;
    	borders[OVER] = border;
	
    }

	private void setUnarmedBorder(CardBorder border) {
		borders[UNARMED] = border;
		borders[DISABLED] = border;
	
	}
    
 	private synchronized void setImage( int id, BufferedImage image ) {
        if ( images[id] != image ) {
            images[id] = image;
            if ( image != null ) {
                tracker.addImage( image, id );
                tracker.checkID( id, true );
            }
        }
        if ( buttonState == id ) {
        	repaint();
        } 
    }
	
	public int getButtonState() {
        return buttonState;
    }

    public void setButtonState( int buttonState ) {
        if ( buttonState != this.buttonState ) {
            this.buttonState = buttonState;
            repaint();
            if(buttonState == ARMED){
            	Container parent = this.getParent();
            	parent.addNotify();
            }
            //System.out.println("ButtonState changed : "  + mouseOverCount++ );
        }
    }
    
    public Dimension getPreferredSize() {
    	Dimension parentDim = getParent().getSize();
    	Insets insets = borders[buttonState].getInsets();
    	return new Dimension((int)(parentDim.getWidth()/2 - (10 + insets.left + insets.right) ),
    			(int)( parentDim.getHeight() / 3 - (20 + insets.top + insets.bottom)));
    }
    
    public synchronized void paint( Graphics g ) {
		
		if(images[buttonState] == null) {
			super.paint(g);
		}
		borders[buttonState].paint( g, getBackground(), 
                0, 0, this.getWidth(), this.getHeight() ); 
        if(images[buttonState] == null) {
			return;
		}
        try {
            if ( ! tracker.checkID( buttonState ) ) {
                tracker.waitForID( buttonState );
            }
            if ( ! tracker.isErrorID( buttonState ) ) {
                Insets insets = borders[buttonState].getInsets();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
               
                g.drawImage(images[buttonState], insets.left, insets.top, this.getWidth() - insets.right, this.getHeight() - insets.bottom,
                							     0, 0, images[buttonState].getWidth(),
                							     	   images[buttonState].getHeight(), this );
            }
        }catch ( InterruptedException ie ) {
        }
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public BufferedImage getImg() {
		return images[UNARMED];
	}

}
