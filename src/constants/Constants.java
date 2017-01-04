package constants;

import enums.File;
import enums.Rank;
import graphics.SpaceBorder;

public class Constants {	
	public static final int UNARMED = 0;
    public static final int ARMED = 1;
    public static final int OVER = 2;
    public static final int DISABLED = 3;
    
    public static final int numRanks = Rank.values().length;
	public static final int numFiles = File.values().length;
    
    public static final SpaceBorder defaultArmedBorder =
	        new SpaceBorder( true );

	public static final SpaceBorder defaultUnarmedBorder =
	        new SpaceBorder( false ); 
    
	private Constants(){}
}
