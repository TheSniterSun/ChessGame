package fl.ed.suncoast.jdbp.chess.solution;

import java.util.ArrayList;

import fl.ed.suncoast.jdbp.chess.solution.ChessGUI.ChessBoard;

/*
 * BISHOP
 * 
 * One of the six chess pieces.
 * This piece can move in any diagonal direction any number of spaces.
 * 
 */

public class Bishop extends ChessPiece
{
	public Bishop(boolean color)
	{
		super(color);
		this.setName(color?"\u265d":"\u2657");		
		this.setSprite(super.bi_spriteSheet.getSubimage(240, this.getColor()?60:0, 60, 60));
	}
	
	@Override
	//Gets and displays the full legal moveset of this ChessPiece.
	public ArrayList<Square> getLegalMoves(int x, int y, boolean real)
	{
		ArrayList<Square> list = super.getLegalMoves(x,y,real);
		
		//Try to move normally.
		if(this.getPin() == AbsolutePin.FREE || this.getPin() == AbsolutePin.UP_LEFT || this.getPin() == AbsolutePin.DOWN_RIGHT)
		{
			list.addAll(super.tryMoveUpLeft(x,y));
			list.addAll(super.tryMoveDownRight(x,y));
		}
		
		if(this.getPin() == AbsolutePin.FREE || this.getPin() == AbsolutePin.UP_RIGHT || this.getPin() == AbsolutePin.DOWN_LEFT)
		{
			list.addAll(super.tryMoveUpRight(x,y));
			list.addAll(super.tryMoveDownLeft(x,y));
		}

		if(real) list = Square.intersection(list, Square.union(ChessBoard.getBlockingSquares(), ChessBoard.getCapturingSquares()));
		
		return list;
	}
}