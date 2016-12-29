package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import definitions.Timing;
import knightmare.KMCardInfo;

public class CInfoArea extends Panel {
	
	private Panel panel1;
	private Panel panel2;
	
	@SuppressWarnings("unused")
	private KMCardInfo cInfo;
	private TextField title;
	private TextField isUnique;
	private TextField points;
	private TextArea mainEffect;
	private TextArea timing;
	private TextArea contEffect;

	private static final String defaultTitle = "No Card Selected";
	private static final Boolean defaultUnique = false;
	private static final int defaultPoints = 0;
	private static final String defaultMEffect = "";
	private static final Timing defaultTiming = Timing.None;
	private static final String defaultContEffect = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CInfoArea () {
		setLayout(new GridLayout(2, 1));
		
		panel1 = new Panel();
		panel1.setLayout(new GridLayout(3,3, 10, 10));
		
		
		panel1.add(new Label("Unique:"));
		panel1.add(new Label("Title:"));
		panel1.add(new Label("Points:"));
		
		
		
		isUnique = new TextField(defaultUnique.toString());
		isUnique.setEditable(false);
		panel1.add(isUnique);
		
		title = new TextField(defaultTitle);
		title.setEditable(false);
		panel1.add(title);
		
		points = new TextField(defaultPoints);
		points.setEditable(false);
		panel1.add(points);
		
		
		panel1.add(new Label("TimingInfo:"));
		panel1.add(new Label("Effect:"));
		panel1.add(new Label("Continuous Effect:"));
		
		add(panel1);
		
		panel2 = new Panel();
		panel2.setLayout(new GridLayout(1, 3, 10, 0));
		
		
		
	
		timing = new TextArea(defaultTiming.toString(), 5, 20, TextArea.SCROLLBARS_NONE);
		timing.setEditable(false);
		panel2.add(timing);
		
		mainEffect = new TextArea(defaultMEffect, 5, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		mainEffect.setEditable(false);
		panel2.add(mainEffect);
		
		
		contEffect = new TextArea(defaultContEffect, 5, 20, TextArea.SCROLLBARS_NONE);
		contEffect.setEditable(false);
		panel2.add(contEffect);
		
		add(panel2);
	}
	
	public Dimension getPreferredSize() {
		Dimension parentDim = getParent().getSize();
		return new Dimension((int) parentDim.getSize().getWidth() / 2 - 10, (int) parentDim.getSize().getHeight() / 5 );
	}

	public void setCardInfo(KMCardInfo cInfo) {
		this.cInfo = cInfo;
		if(cInfo != null) {
			title.setText(cInfo.getName());
			points.setText("" + cInfo.getPoints());
			mainEffect.setText(cInfo.getEffect());
			timing.setText(cInfo.getTiming().toString());
			contEffect.setText(cInfo.getContEffect());
		}
		repaint();
	}

}
