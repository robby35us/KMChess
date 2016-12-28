A MoveConstraint is simply a restriction on a move. Each constraint class 
must implement the meetsConstraint method, which returns true if the
constraint conditions are satisfied, and false otherwise. meetsConstraint
takes a Move object(can be an actual move if this is a "combination" move)
the next move to wrap around that move, and the initial Space the move is 
originating from.