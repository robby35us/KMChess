package cards;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import enums.Timing;
import graphics.CardBorder;
import guiComponent.CardArea;


public class KMCard extends Canvas implements MouseListener{

	private static final long serialVersionUID = 1L;
	   
    public static final int UNARMED = 0;
    public static final int ARMED = 1;
    public static final int OVER = 2;
    public static final int DISABLED = 3;
    public static final Dimension MINIMUM_SIZE = new Dimension(100,190);
    public static final Dimension MAXIMUM_SIZE = new Dimension(100,190);
    public static KMCard activeCard = null;
   
    private KMCardInfo cInfo;
    private int buttonState = UNARMED;
	private BufferedImage[] images = new BufferedImage[4];
	private MediaTracker tracker;
	private CardBorder[] borders = new CardBorder[4];
	
	@SuppressWarnings("unused")
	private boolean generatedDisabled;
	private boolean mousedown;
	private boolean mouseInBounds = false;
	
	public static volatile Timing CurrentTiming = Timing.None;

	private static final CardBorder defaultUnarmedBorder =
	        new CardBorder( false );
	    private static final CardBorder defaultArmedBorder =
	        new CardBorder( true );
	private static KMCard empty;
	private static final KMCardInfo emptyCInfo = new KMCardInfo(0, 0, null, null, 0, Timing.None, null);
	    
	static {
		try {
			empty = new KMCard(ImageIO.read(new java.io.File("C:\\Users\\robby35us\\Pictures\\KMChess\\Back.jpg")), emptyCInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		empty.setBackground(Color.LIGHT_GRAY);
		
	}
	    
	
	    private KMCard() {
	    	setBackground(Color.GRAY);
	        tracker = new MediaTracker( this );
	        setUnarmedBorder( defaultUnarmedBorder );
	        setArmedBorder( defaultArmedBorder );
	        this.setMinimumSize(MINIMUM_SIZE);
	        this.setMaximumSize(MAXIMUM_SIZE);
	        this.addMouseListener(this);
	        
	 }
	    
	    private void setArmedBorder(CardBorder border) {
	    	borders[ARMED] = border;
	    	borders[OVER] = border;
		
	}

		private void setUnarmedBorder(CardBorder border) {
			borders[UNARMED] = border;
			borders[DISABLED] = border;
		
	}
	  
	    public KMCard(BufferedImage image, KMCardInfo cInfo) {
	        this();
	        setUnarmedImage( image );
	        setArmedImage(image);
	        setOverImage(image);
	        setDisabledImage(image);
	        this.cInfo = cInfo;
	    }     
	    
	    public KMCard(KMCard empty) {
			this(empty.images[UNARMED], empty.cInfo);
		}

		public void setUnarmedImage( BufferedImage image ) {
	        if (image != null)
	        	//image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
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
	    
	    public void setArmedImage( BufferedImage image ) {
	        if ( image != null ) {
	        	//image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	            setImage( ARMED, image );
	        }
	        else {
	            setImage( ARMED, images[UNARMED] );
	        }
	    }

	    public void setOverImage( BufferedImage image ) {
	        if ( image != null ) {
	        	//image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	            setImage( OVER, image );
	        }
	        else {
	            setImage( OVER, images[UNARMED] );
	        }
	    }

	    public void setDisabledImage( BufferedImage image ) {
	        generatedDisabled = false;
	        if ( ( image == null ) &&
	             ( images[UNARMED] != null ) ) {
	        generatedDisabled = true;
	        //image = createImage( 
	         //   new FilteredImageSource(images[UNARMED].getSource(), 
	          //                          new ImageFilter() ) );
	        }
	        //if(image != null)
	        //	image = image.getScaledInstance((int)this.getMinimumSize().getWidth(), (int)this.getMinimumSize().getHeight(),Image.SCALE_DEFAULT);
	        setImage( DISABLED, image );
	    }
	    
	 	private synchronized void setImage( int id, BufferedImage image ) {
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

	    protected void setButtonState( int buttonState ) {
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
        	Dimension parentDim = getParent().getSize();
        	Insets insets = borders[buttonState].getInsets();
        	return new Dimension((int)(parentDim.getWidth()/2 - (10 + insets.left + insets.right) ),
        			(int)( parentDim.getHeight() / 3 - (20 + insets.top + insets.bottom)));
        	
        	/*if(images[buttonState] == null){     		
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
            */
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
                    //int imageWidth = images[buttonState].getWidth( this );
                    //int imageHeight = images[buttonState].getHeight( this );
                    //int imageWidth = this.getWidth();
                    //int imageHeight = this.getHeight();
                    /*int x = insets.left +
                            ( ( ( size.width - ( insets.left + insets.right ) ) -
                                imageWidth ) / 2 );
                    int y = insets.top +
                            ( ( ( size.height - ( insets.top + insets.bottom ) ) -
                                imageHeight ) / 2 );
                    *///System.out.println(this.getWidth());
                    //System.out.println(this.getHeight());
                    //Image img = images[buttonState].getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                   
                    g.drawImage(images[buttonState], insets.left, insets.top, this.getWidth() - insets.right, this.getHeight() - insets.bottom,
                    							     0, 0, images[buttonState].getWidth(),
                    							     	   images[buttonState].getHeight(), this );
                    // System.out.println(img.getWidth(this));
                    //System.out.println(img.getHeight(this));
                }
            }
            catch ( InterruptedException ie ) {
        }
    } 
    
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mousedown = false;
		
		if(activeCard != null){
			activeCard.setButtonState(UNARMED);		
			if(activeCard == this){
				//if((CurrentTiming != Timing.After && activeCard.getCInfo().getTiming() != Timing.After) ||
				//   (CurrentTiming == Timing.After && (activeCard.getCInfo().getTiming() == Timing.After ||
				//		   							  activeCard.getCInfo().getTiming() == Timing.BeforeOrAfter)))
					((CardArea) this.getParent().getParent().getParent()).setExecutingCard(this);
				activeCard = null;
				return;
			}
		}
		activeCard = this;
		((CardArea) this.getParent().getParent().getParent()).setSelectedCard(this);
	}


	@Override
	public void mouseEntered(MouseEvent e) {
        this.mouseInBounds = true;
		if ( mousedown ) {
            setButtonState( ARMED );
        }
        else {
            setButtonState( OVER );
        }
       
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseInBounds = false;
        if( this != activeCard)
        	setButtonState( UNARMED );
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousedown = true;
		setButtonState(ARMED);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	   mousedown = false;
	   if(mouseInBounds)
		   setButtonState( OVER );
	   else
		   setButtonState(UNARMED);
		
	}

	public static KMCard getEmpty() {
		
		return new KMCard(empty);
	}

	public KMCardInfo getCInfo() {
		return cInfo;
	}

	public BufferedImage getImg() {
		return images[UNARMED];
	}

}
