package utilityContainers;
import gameComponents.SpacePresentation;

/*
 * A transporter object used to return information about the
 * initial Space and destination space for a proposed move.
 */
public class MoveInput {
	private SpacePresentation init;
	private SpacePresentation dest;
	
	public MoveInput(SpacePresentation init, SpacePresentation dest){
		this.init = init;
		this.dest = dest;
	}
	
	// public getters
	public SpacePresentation getInit(){
		return init;
	}
	
	public SpacePresentation getDest(){
		return dest;
	}
}
