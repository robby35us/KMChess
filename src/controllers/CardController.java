package controllers;

import static definitions.Constants.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import cards.KMCardInfo;
import definitions.Controller;
import definitions.Timing;
import definitions.Visitor;
import guiComponent.CardArea;
import models.CardModel;
import views.CardView;

public class CardController extends Controller implements MouseListener {

    public static CardController activeCard = null;
	
	private CardModel model;
	private CardView view;
	private CardArea parent;
	
	private boolean mousedown;
	private boolean mouseInBounds = false;
	private static CardController empty;
	private static final KMCardInfo emptyCInfo = new KMCardInfo(0, 0, null, null, 0, Timing.None, null);
	    
	static {
		try {
			CardView eView = new CardView(ImageIO.read(new java.io.File("C:\\Users\\robby35us\\Pictures\\KMChess\\Back.jpg")));
			CardModel eModel = new CardModel(emptyCInfo, eView);
			empty = new CardController(eModel, eView, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CardController(CardModel model, CardView view, CardArea parent){
		this.model = model;
		this.view = view;
		this.parent = parent;
		view.addMouseListener(this);
		this.childControllers = new ArrayList<Controller>();
	}
	
	
    public CardController(CardController empty) {
		this.view = new CardView(empty.getImg());
		this.model = new CardModel(empty.getCInfo(), this.view);
		this.parent = empty.parent;
		view.addMouseListener(this);
		this.childControllers = new ArrayList<Controller>();
	}

		public static CardController getEmpty() {
			return new CardController(empty);
		}

		public KMCardInfo getCInfo() {
			return model.getCInfo();
		}

		public BufferedImage getImg() {
			return this.view.getImg();
		}

	public CardView getView(){
		return this.view;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mousedown = false;
		
		if(activeCard != null){
			activeCard.view.setButtonState(UNARMED);		
			if(activeCard == this){
				//if((CurrentTiming != Timing.After && activeCard.getCInfo().getTiming() != Timing.After) ||
				//   (CurrentTiming == Timing.After && (activeCard.getCInfo().getTiming() == Timing.After ||
				//		   							  activeCard.getCInfo().getTiming() == Timing.BeforeOrAfter)))
					parent.setExecutingCard(this);
					activeCard = null;
				return;
			}
		}
		activeCard = this;
		parent.setSelectedCard(this);
	}


	@Override
	public void mouseEntered(MouseEvent e) {
        this.mouseInBounds = true;
		if ( mousedown ) {
            this.view.setButtonState( ARMED );
        }
        else {
            this.view.setButtonState( OVER );
        }
       
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseInBounds = false;
        if( this != activeCard)
        	this.view.setButtonState( UNARMED );
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousedown = true;
		this.view.setButtonState(ARMED);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	   mousedown = false;
	   if(mouseInBounds)
		   this.view.setButtonState( OVER );
	   else
		   this.view.setButtonState(UNARMED);
	}

	@Override
	public Object accept(Visitor e) {
		
		return null;
	}

	@Override
	public void createChildTriad() {
		// intentionally left empty
	}

}
