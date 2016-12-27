package utility;
import components.Space;

/*
 * A transporter object used to return information about the
 * initial Space and destination space for a proposed move.
 */
public class MoveInput {
	private Space init;
	private Space dest;
	
	public MoveInput(Space init, Space dest){
		this.init = init;
		this.dest = dest;
	}
	
	// public getters
	public Space getInit(){
		return init;
	}
	
	public Space getDest(){
		return dest;
	}
}
