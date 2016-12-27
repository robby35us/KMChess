package utility;

/*
 * Used to transport flags about errors and events like Check around to
 * various parts of the code. Has a series of flags that can be set.
 */
public class ErrorMessage {
	
	// symbolizes that no flag has been set
	private boolean noError = true;
	
	// flags all set to false
	private boolean check = false;
	private boolean mate = false;
	private boolean illegalPattern = false;
	private boolean wrongColorMoving = false;
	private boolean wrongColorCaptured = false;
	private boolean disallowedMove = false;
	private boolean nullInput = false;
	private boolean invalidInput = false;
	private boolean noPieceToMove = false;
	private boolean constraintNotMet = false;
	private boolean promotePawn = false;
	private boolean unableToPromotePawn = false;
	
	@Override
	public String toString(){
		String output = "";
		if(noError)
			output = "No error reported.\n";
		else {
			if(mate)
				output += "Mate reported\n";
			if(check)
				output += "Check reported\n";
			if(illegalPattern)
				output += "Illegal pattern reported\n";
			if(disallowedMove)
				output += "disallowed move reported\n";
			if(wrongColorMoving)
				output += "Wrong Color moving reported\n";
			if(wrongColorCaptured)
				output += "Wrong Color captured reported\n";
			if(invalidInput)
				output += "Invalid input reported\n";
			if(nullInput)
				output += "Null input reported\n";
			if(noPieceToMove)
				output += "No piece to move reported\n";
			if(constraintNotMet)
				output += "Constraint not met reported\n";
			if(promotePawn)
				output += "Pawn promotion reported\n";
			if(unableToPromotePawn)
				output += "Pawn promotion fail reported\n";
		}
		return output;
		
	}

	// returns true if a flag has been set.
	public boolean hasError(){
		return !noError;
	}
	
	// public setters. Set the given flag to true 
	// and noError to false. 
	public void setCheck(){
		check = true;
		noError = false;
	}
	
	public void setMate(){
		mate = true;
		noError = false;
	}
	
	public void setIllegalPattern(){
		illegalPattern = true;
		noError = false;
	}
	
	public void setDisallowedMove(){
		disallowedMove = true;
		noError = false;
	}
	public void setWrongColorMoving(){
		wrongColorMoving = true;
		noError = false;
	}
	
	public void setWrongColorCaptured(){
		wrongColorCaptured = true;
		noError = false;
	}
	
	public void setNullInput() {
		nullInput = true;
		noError = false;
	}

	public void setInvalidInput() {
		invalidInput  = true;
		noError = false;
	}
	
	public void setNoPieceToMove() {
		noPieceToMove  = true;
		noError = false;
	}
	

	public void setConstraintNotMet() {
		constraintNotMet  = true;
		noError = false;
	}

	public void setPromotePawn() {
		promotePawn = true;
		noError = false;
	}
	

	public void setUnableToPromotePawn() {
		unableToPromotePawn = true;
		noError = false;// TODO Auto-generated method stub
	}
	
	
	
	
	// public getters
	public boolean getCheck(){
		return check;
	}
	
	public boolean getMate(){
		return mate;
	}
	
	public boolean getIllegalPattern(){
		return illegalPattern;
	}
	
	public boolean getDisallowedMove(){
		return disallowedMove;
	}
	
	public boolean getWrongColorMoving(){
		return wrongColorMoving;
	}
	
	public boolean getWrongColorCaptured(){
		return wrongColorCaptured;
	}

	public boolean getNullInput() {
		return nullInput;
	}
	
	public boolean getInvalidInput(){
		return invalidInput;
	}
	
	public boolean getNoPieceToMove(){
		return noPieceToMove;
	}
	
	public boolean getConstraintNotMet(){
		return constraintNotMet;
	}

	public boolean getPromotePawn(){
		return promotePawn;
	}

	public boolean getUnableToPromotePawn(){
		return unableToPromotePawn;
	}
}
