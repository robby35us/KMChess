package utilityContainers;

import control.SpaceController;

/*
 * A transporter object used to return information about the
 * initial Space and destination space for a proposed move.
 */
public class MoveInput {
	private SpaceController init;
	private SpaceController dest;
	
	public MoveInput(SpaceController init, SpaceController dest){
		this.init = init;
		this.dest = dest;
	}
	
	// public getters
	public SpaceController getInit(){
		return init;
	}
	
	public SpaceController getDest(){
		return dest;
	}
}
