package testIntegration;

import enums.File;
import enums.PieceColor;
import enums.PieceType;
import enums.Rank;

public class PieceInfo {
	private PieceType type;
	private PieceColor color;
	private Rank rank;
	private File file;

	public PieceInfo(PieceType type, PieceColor color, Rank rank, File file){
		this.type = type;
		this.color = color;
		this.rank = rank;
		this.file = file;
	}
	
	public PieceType getType(){
		return type;
	}
	
	public PieceColor getColor(){
		return color;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public File getFile(){
		return file;
	}
}
