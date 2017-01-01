package presentation;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;

import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import enums.SpaceColor;
import graphics.SpaceBorder;
import pieces.Piece;
import presentation.SpacePresentation;


public class SpacePresentation extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Piece p;
	   
	public static final int UNARMED = 0;
    public static final int ARMED = 1;
    public static final int OVER = 2;
    public static final int DISABLED = 3;
    public static final Dimension MINIMUM_SIZE = new Dimension(50,50);
    public static final Dimension MAXIMUM_SIZE = new Dimension(100,100);
    
    
    private int buttonState = UNARMED;
	private Image[] images = new Image[4];
	private MediaTracker tracker;
	private SpaceBorder[] borders = new SpaceBorder[4];
	
	@SuppressWarnings("unused")
	private boolean generatedDisabled;

	
	public static final SpaceBorder defaultUnarmedBorder =
	        new SpaceBorder( false );
	public static final SpaceBorder defaultArmedBorder =
	        new SpaceBorder( true );
	    
	    
	 
	public SpacePresentation( SpaceColor background, Image image ) {
        this();
        if(background == SpaceColor.White)
        	setBackground(Color.WHITE);
        else
           setBackground(Color.LIGHT_GRAY);
        setUnarmedImage( image );
    } 
	
	private SpacePresentation() {
	        tracker = new MediaTracker( this );
	        setUnarmedBorder( defaultUnarmedBorder );
	        setArmedBorder( defaultArmedBorder );
	        
	        this.setMinimumSize(MINIMUM_SIZE);
	        this.setMaximumSize(MAXIMUM_SIZE);        
	 }

	    public void setArmedBorder(SpaceBorder border) {
	    	borders[ARMED] = border;
	    	borders[OVER] = border;
		
	}

		public void setUnarmedBorder(SpaceBorder border) {
			borders[UNARMED] = border;
			borders[DISABLED] = border;
		
	}
		
		/*
		 * The setter method for Piece. Returns whether or
		 * not a piece was replaced. Set the value to null
		 * to represent that a piece has been moved from 
		 * this space without a replacement.
		 */
		public void updateImage(Image img, boolean repaint){
			setUnarmedImage(img);
			setArmedImage(img);
			setOverImage(img);
			setDisabledImage(img);
			if(repaint)
				repaint();
		}
		
		 
	 	public void setUnarmedImage( Image image ) {
	        if (image != null)
	        	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	 		setImage( UNARMED, image );
	        //if ( images[ARMED] == null ) {
	         //   setArmedImage( image );
	        //}
	        //if ( images[OVER] == null ) {
	        //    setOverImage( image );
	        //}
	        //if ( ( images[DISABLED] == null ) ||
	         //    generatedDisabled ) {
	         //   setDisabledImage( null );
	       // }
	    }

	    public void setArmedImage( Image image ) {
	        if ( image != null ) {
	        	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	            setImage( ARMED, image );
	        }
	        else {
	            setImage( ARMED, images[UNARMED] );
	        }
	    }

	    public void setOverImage( Image image ) {
	        if ( image != null ) {
	        	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	            setImage( OVER, image );
	        }
	        else {
	            setImage( OVER, images[UNARMED] );
	        }
	    }

	    public void setDisabledImage( Image image ) {
	        generatedDisabled = false;
	        if ( ( image == null ) &&
	             ( images[UNARMED] != null ) ) {
	        generatedDisabled = true;
	        image = createImage( 
	            new FilteredImageSource(images[UNARMED].getSource(), 
	                                    new ImageFilter() ) );
	        }
	        if(image != null)
	        	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	        setImage( DISABLED, image );
	    }

	 	private synchronized void setImage( int id, Image image ) {
	        if ( images[id] != image ) {
	            images[id] = image;
	            if ( image != null ) {
	                tracker.addImage( image, id );
	                tracker.checkID( id, true );
	            }
	            //if ( buttonState == id ) {
	            //   repaint();
	            //}
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
	    
	    
	    public void disable() {
	            setButtonState( DISABLED );
	            super.setEnabled(false);
	        }

	        public void enable() {
	            setButtonState( UNARMED );
	            super.setEnabled(true);
	        }
	        
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
	            }
	            return pref;
	        }
	    

    	public synchronized void paint( Graphics g ) {
    		
    		if(images[buttonState] == null) {
    			super.paint(g);
    		}
            Dimension size = this.getSize();
            borders[buttonState].paint( g, getBackground(), 
                                        0, 0, size.width, size.height );
            if(images[buttonState] == null) {
    			return;
    		}
            try {
                if ( ! tracker.checkID( buttonState ) ) {
                    tracker.waitForID( buttonState );
                }
                if ( ! tracker.isErrorID( buttonState ) ) {
                    Insets insets = borders[buttonState].getInsets();
                    int imageWidth = images[buttonState].getWidth( this );
                    int imageHeight = images[buttonState].getHeight( this );
                    //int imageWidth = this.getWidth();
                    //int imageHeight = this.getHeight();
                    int x = insets.left +
                            ( ( ( size.width - ( insets.left + insets.right ) ) -
                                imageWidth ) / 2 );
                    int y = insets.top +
                            ( ( ( size.height - ( insets.top + insets.bottom ) ) -
                                imageHeight ) / 2 );
                    //System.out.println(this.getWidth());
                    //System.out.println(this.getHeight());
                    //Image img = images[buttonState].getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
                    g.drawImage(images[buttonState], x, y, this );
                   // System.out.println(img.getWidth(this));
                    //System.out.println(img.getHeight(this));
                }
            }
            catch ( InterruptedException ie ) {
        }
    } 
    
}

       


     