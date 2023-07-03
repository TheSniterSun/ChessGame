package fl.ed.suncoast.jdbp.chess.solution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*
 * CHESS PIECE
 * 
 * The main component of the game.
 * Each piece has a color on the board,
 * as well as a state (captured or not).
 * 
 */

public class ChessPiece
{
	protected BufferedImage bi_spriteSheet;
	
	private BufferedImage bi_sprite;
	private String name;
	private boolean b_isWhite;

	enum State
	{
		UNMOVED,
		EN_PASSANT,
		MOVED,
		CHECKMATED,
		CAPTURED
	};
	private State state;
	
	enum AbsolutePin
	{
		FREE,
		UP,
		UP_LEFT,
		LEFT,
		DOWN_LEFT,
		DOWN,
		DOWN_RIGHT,
		RIGHT,
		UP_RIGHT
	}
	private AbsolutePin pin;
	
	
	//Creates a new chess piece.
	public ChessPiece(boolean color)
	{
		//Load spritesheet.
		try
		{
			this.bi_spriteSheet = ImageIO.read(new File("ChessPiecesArray.png"));
		} 
		catch (IOException ioe)
		{
			System.out.println("Couldn't load sprites.");
			System.exit(0);
		}
		
		this.setColor(color);
		this.setState(State.UNMOVED);
		this.setPin(AbsolutePin.FREE);
	}
	
	//Gets
	public String getName()
	{
		return this.name;
	}

	public boolean getColor()
	{
		return this.b_isWhite;
	}
	
	public BufferedImage getSprite()
	{
		return this.bi_sprite;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public AbsolutePin getPin()
	{
		return this.pin;
	}
	
		
	//Sets
	public void setName(String name)
	{
		this.name = name;
	}

	public void setColor(boolean color)
	{
		this.b_isWhite = color;
	}
	
	public void setSprite(BufferedImage sprite)
	{
		this.bi_sprite = sprite;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public void setPin(AbsolutePin pin)
	{
		this.pin = pin;
	}
	
	
	//Gets and displays the full legal moveset of this ChessPiece.
	public ArrayList<Square> getLegalMoves(int x, int y, boolean real)
	{
		return new ArrayList<Square>();
	}
	
	//Tries to move orthogonally left.
	public ArrayList<Square> tryMoveLeft(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x-1; i >= 0; i--)
		{
			if(squares[i][y].getChesspiece() == null)
			{
				list.add(squares[i][y]);
			}
			else if(squares[i][y].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][y]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move orthogonally right.
	public ArrayList<Square> tryMoveRight(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x+1; i <= 7; i++)
		{
			if(squares[i][y].getChesspiece() == null)
			{
				list.add(squares[i][y]);
			}
			else if(squares[i][y].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][y]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move orthogonally up.
	public ArrayList<Square> tryMoveUp(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int j = y-1; j >= 0; j--)
		{
			if(squares[x][j].getChesspiece() == null)
			{
				list.add(squares[x][j]);
			}
			else if(squares[x][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[x][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move orthogonally down.
	public ArrayList<Square> tryMoveDown(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int j = y+1; j <= 7; j++)
		{
			if(squares[x][j].getChesspiece() == null)
			{
				list.add(squares[x][j]);
			}
			else if(squares[x][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[x][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move diagonally up and left.
	public ArrayList<Square> tryMoveUpLeft(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--)
		{
			if(squares[i][j].getChesspiece() == null)
			{
				list.add(squares[i][j]);
			}
			else if(squares[i][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move diagonally up and right.
	public ArrayList<Square> tryMoveUpRight(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x+1, j = y-1; i <= 7 && j >= 0; i++, j--)
		{
			if(squares[i][j].getChesspiece() == null)
			{
				list.add(squares[i][j]);
			}
			else if(squares[i][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move diagonally down and left.
	public ArrayList<Square> tryMoveDownLeft(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x-1, j = y+1; i >= 0 && j <= 7; i--, j++)
		{
			if(squares[i][j].getChesspiece() == null)
			{
				list.add(squares[i][j]);
			}
			else if(squares[i][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
	
	//Tries to move diagonally down and right.
	public ArrayList<Square> tryMoveDownRight(int x, int y)
	{
		Square[][] squares = ChessGUI.ChessBoard.getSquares();
		ArrayList<Square> list = new ArrayList<Square>();
		
		for(int i = x+1, j = y+1; i <= 7 && j <= 7; i++, j++)
		{
			if(squares[i][j].getChesspiece() == null)
			{
				list.add(squares[i][j]);
			}
			else if(squares[i][j].getChesspiece().getColor() != this.getColor())
			{
				list.add(squares[i][j]);
				break;
			}
			else break;
		}
		
		return list;
	}
}