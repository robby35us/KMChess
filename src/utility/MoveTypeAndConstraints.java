package utility;
import constraints.MoveConstraint;
import definitions.MoveType;

/*
 * A transport object for a MoveType and the accompanying constraints.
 */
public class MoveTypeAndConstraints {
	private MoveType moveType;
	private MoveConstraint[] constraints;
	
	public MoveTypeAndConstraints(MoveType moveType, MoveConstraint[] constraints){
		this.moveType = moveType;
		this.constraints = constraints;
	}
	
	//public getters
	public MoveType getMoveType(){
		return moveType;
	}
	
	public MoveConstraint[] getConstraints(){
		return constraints;
	}
}
