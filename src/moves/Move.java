package moves;
import components.Space;

/* Move.java 
 * This class is the base class type for a chess move. 
 * Chess moves are composited together one space at a
 * time to facilitate every conceivable move possible.
 * This is achieved through the use of the Decorator 
 * with all chess moves being the composite of a concrete 
 * Move object and one or more decorator Move objects.
 */
public abstract class Move {
  protected int rankOffset;
  protected int fileOffset;
  protected Space initialSpace;
  protected Space destinationSpace;
  
  /*
   * The super constructor for all Move objects
   */
  protected Move(int rankOffset, int fileOffset, Space initial){
	this.rankOffset = rankOffset;
	this.fileOffset = fileOffset;
	initialSpace = initial;
	setDestination();
  }
  
  /*
   * Uses the set rankOffset and fileOffset to iterate across the board
   * to find the Destination Space and sets it to this.destinationSpace.
   */
  protected void setDestination() {
	int ro = rankOffset;
	int fo = fileOffset;
	Space space = initialSpace;
	while(space != null && (ro != 0 || fo != 0 )){
		if(ro > 0){ 
			space = space.getSpaceForward();
			ro--;
		}
		else if(ro < 0){
			space = space.getSpaceBackward();
			ro++;
		}
		if(fo > 0){
			space = space.getSpaceRight();
			fo--;
		}
		else if(fo < 0){
			space = space.getSpaceLeft();
			fo++;
		}
	}
	destinationSpace = space;
  }	


  // public getters
  public int getRankOffset(){
  	return rankOffset;
  }
  
  public int getFileOffset(){
	return fileOffset;
  }
  
  public Space getInitialSpace(){
	return initialSpace;
  } 
  

  public Space getDestinationSpace() {
	return destinationSpace;
  }
}
