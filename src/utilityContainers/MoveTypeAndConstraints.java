package utilityContainers;
import java.util.ArrayList;

import definitions.MoveConstraint;
import definitions.MoveType;

/*
 * A transport object for a MoveType and the accompanying constraints.
 */
public class MoveTypeAndConstraints {
	private MoveType moveType;
	private ArrayList<MoveConstraint> constraints;
	
	public MoveTypeAndConstraints(MoveType moveType, ArrayList<MoveConstraint> constraint){
		this.moveType = moveType;
		this.constraints = constraint;
	}
	
	//public getters
	public MoveType getMoveType(){
		return moveType;
	}
	
	public ArrayList<MoveConstraint> getConstraints(){
		return constraints;
	}
}
