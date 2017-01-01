components.Readme.txt

The components package contains the expected object files that are 
used to represent a chess game's state, like Board, Piece, and Space. You 
will also find Player and PlayerSet, which represent a player and a set of
pieces for that player, respectively. The King class is the only class that
currently extends Piece. A king has additional functions that are used for
the purposes of preventing self-check, and recognizing Checkmate/Stalemate.