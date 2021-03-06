package standardMoves;

import controllers.SpaceController;
import definitions.Move;

/* Touch.java - Robert Reed
 * This class is a "concrete" class that extends
 * the Move base class for the decorator pattern 
 * classes that comprise the object representation 
 * of a single move in nightmare chess. It is a non-
 * move, or a "touch," and is the most common type
 * of "base" move to be wrapped by ActualMove objects.
 */

public class Touch extends Move{
   public Touch(SpaceController initial, SpaceController finalDest){
     super(0,0, initial, finalDest);
   }
}
