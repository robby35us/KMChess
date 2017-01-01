package utilityContainers;

import control.SpaceControl;

/*
 * A transporter object used to return information about the
 * initial Space and destination space for a proposed move.
 */
public class MoveInput {
	private SpaceControl init;
	private SpaceControl dest;
	
	public MoveInput(SpaceControl init, SpaceControl dest){
		this.init = init;
		this.dest = dest;
	}
	
	// public getters
	public SpaceControl getInit(){
		return init;
	}
	
	public SpaceControl getDest(){
		return dest;
	}
}
