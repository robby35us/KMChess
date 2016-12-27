package definitions;

/*
 * This enum holds the information about the Rank(or row) and File (or Column) 
 * offsets for each move type from the white player's perspective. For each 
 * move type with the name MoveXxx, there is a corresponding MoveType with the 
 * name Xxx.
 */
public enum MoveType {
	Forward(1,0), ForwardLeft(1,-1), ForwardRight(1,1), ForwardTwo(2,0), 
	LForwardLeft(2,-1), LForwardRight(2,1), LRightForward(1,2), LRightBackward(-1,2), 
	LBackwardRight(-2,1), LBackwardLeft(-2,-1), LLeftBackward(-1,-2), LLeftForward(1,-2), 
	BackwardRight(-1,1), BackwardLeft(-1,-1), Right(0,1), Backward(-1,0), Left(0,-1), 
	ReverseKingSideCastle(0,-2), ReverseQueenSideCastle(0,3), 
	KingSideCastle(0,2), QueenSideCastle(0,-2), EnPassantRight(1,1), EnPassantLeft(1,-1);

	private int rankOffset;
	private int fileOffset;
	
	/*
	 * The rank and offset should be set from the white player's perspective.
	 */
	private MoveType(int rankOffset, int fileOffset){
		this.rankOffset = rankOffset;
		this.fileOffset = fileOffset;
	}
	
	/*
	 * Returns the Rank(or Row) offset from the white player's perspective.
	 */
	public int getRankOffset(){
		return rankOffset;
	}
	
	/*
	 * Returns the File(or Column) offset from the white player's perspective.
	 */
	public int getFileOffset(){
		return fileOffset;
	}
}
