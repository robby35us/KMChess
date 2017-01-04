package abstraction;

import java.util.ArrayList;

import control.SpaceController;
import enums.File;
import enums.Rank;
import presentation.BoardPresentation;

import static constants.Constants.*;

public class BoardModel {
	ArrayList<SpaceModel> spaces;

	public BoardModel(BoardPresentation view){
	spaces = new ArrayList<SpaceModel>(numRanks * numFiles);
	for(int i = numRanks - 1;  i >=0 ; i--)
		for(int j = 0; j < numFiles; j++){
			new SpaceController(Rank.values()[i], File.values()[j], this));

	}
}