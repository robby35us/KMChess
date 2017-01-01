package enums;

/*This is a substitution for the standard Color enum. The idea is 
 * to enforce the restriction to two color. This enum is coupled 
 * tightly with the Turn enum, such that Color.White.ordinal() returns
 * the same value as Turn.Player1.ordinal() and likewise for 
 * Color.Black.ordinal and turn.Player2.ordinal(). In fact, these 
 * could be reduced to the same course, but these names are used 
 * to make the language in the code clear.
 */
public enum PieceColor {
	White, Black;
}
