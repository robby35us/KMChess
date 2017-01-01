package utilityContainers;


public class RankAndFileOffsets {
	private int rankOffset;
	private int fileOffset;
	public RankAndFileOffsets(int ro, int fo){
		rankOffset = ro;
		fileOffset = fo;
	}
	
	public int getRankOffset(){
		return rankOffset;
	}
	
	public int getFileOffset(){
		return fileOffset;
	}
}
