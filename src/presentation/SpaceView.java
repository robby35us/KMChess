package presentation;

import java.awt.Canvas;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;

import java.util.Observable;
import java.util.Observer;

import abstraction.SpaceModel;
import enums.SpaceColor;
import graphics.SpaceBorder;
import pieces.Piece;
import pieces.PieceImages;

import static constants.Constants.*;


public class SpaceView extends Canvas implements Observer{
	
	private static final long serialVersionUID = 1L;
	   

    public static final Dimension MINIMUM_SIZE = new Dimension(50,50);
    public static final Dimension MAXIMUM_SIZE = new Dimension(100,100);
    
    
    private int buttonState = UNARMED;
	private Image[] images = new Image[4];
	private MediaTracker tracker;
	private SpaceBorder[] borders = new SpaceBorder[4];
	
	public SpaceView(SpaceColor background) {
        tracker = new MediaTracker( this );
        setUnarmedBorder(defaultUnarmedBorder );
        setArmedBorder( defaultArmedBorder );
        
        this.setMinimumSize(MINIMUM_SIZE);
        this.setMaximumSize(MAXIMUM_SIZE);
        if(background == SpaceColor.White)
        	setBackground(Color.WHITE);
        else
           setBackground(Color.LIGHT_GRAY);
        updateImage(null, true);
	 }

    private void setArmedBorder(SpaceBorder border) {
    	borders[ARMED] = border;
    	borders[OVER] = border;
	}

	private void setUnarmedBorder(SpaceBorder border) {
		borders[UNARMED] = border;
		borders[DISABLED] = border;
	}
			
	private void updateImage(Image img, boolean repaint){
		setImage(UNARMED, img, repaint);
		setImage(OVER, img, repaint);
		setImage(ARMED, img, repaint);
		setImage(DISABLED, img, repaint );
	}
	

 	private synchronized void setImage( int id, Image image, boolean repaint ) {
 		if (images[id] != image ) {
            images[id] = image;
            if ( image != null ) {
            	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
                tracker.addImage( image, id );
                tracker.checkID( id, true );
            }
        if(repaint && buttonState == id )
            repaint();
        }
    }

 	public void setButtonState(int id){
 		buttonState = id;
 		repaint();
 	}
 	
	@Override
    public Dimension getPreferredSize() { 
    	if(images[buttonState] == null){     		
    		return super.getPreferredSize();
    	}
        Dimension pref = new Dimension();
        try {
            if ( ! tracker.checkID( buttonState ) ) {
                tracker.waitForID( buttonState );
            }
            if ( ! tracker.isErrorID( buttonState ) ) {
               
                pref.width = images[buttonState].getWidth( this );
                pref.height = images[buttonState].getHeight( this );
            }
            int maxWidthAdd = 0;
            int maxHeightAdd = 0;
            for ( int i = 0; i < DISABLED; i++ ) {
                Insets insets = borders[i].getInsets();
                maxWidthAdd = Math.max( maxWidthAdd, 
                                        insets.left+insets.right );
                maxHeightAdd = Math.max( maxHeightAdd, 
                                         insets.top+insets.bottom );
            }
            pref.width += maxWidthAdd;
            pref.height += maxHeightAdd;
        }
        catch ( InterruptedException ie ) {
        	ie.printStackTrace();
        }
        return pref;
    }
	    
    @Override
    public synchronized void paint( Graphics g ) {
    		
    	if(images[buttonState] == null)
    		super.paint(g);
        Dimension size = this.getSize();
        borders[buttonState].paint( g, getBackground(),  0, 0, size.width, size.height );
        if(images[buttonState] == null)
    		return;
        try {
            if ( ! tracker.checkID( buttonState ) ) {
                tracker.waitForID( buttonState );
            }
            if ( ! tracker.isErrorID( buttonState ) ) {
                Insets insets = borders[buttonState].getInsets();
                int imageWidth = images[buttonState].getWidth( this );
                int imageHeight = images[buttonState].getHeight( this );
                int x = insets.left +
                        ( ( ( size.width - ( insets.left + insets.right ) ) -
                            imageWidth ) / 2 );
                int y = insets.top +
                        ( ( ( size.height - ( insets.top + insets.bottom ) ) -
                            imageHeight ) / 2 );
                g.drawImage(images[buttonState], x, y, this );
            }
        } catch ( InterruptedException ie ) {
        	ie.printStackTrace();
        }
    }

	@Override
	public void update(Observable arg0, Object arg1) {
			Piece p = (Piece) arg1;
			this.updateImage(PieceImages.getImage(p.getType(), p.getColor(), ((SpaceModel)arg0).getColor()), arg0.hasChanged());
	}
    
}

       


     