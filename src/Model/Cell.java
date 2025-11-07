package Model;

public class Cell {
	public Cell()
	{
		row = 0;
		col = 0;
		surroundingMines = 0;
		isMine = false;
		isRevealed = false;
		isMarked = false;
	}
	
	public Cell(int row, int col)
	{
		this.row = row;
		this.col = col;
		surroundingMines = 0;
		isMine = false;
		isRevealed = false;
		isMarked = false;
	}
	
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public boolean getIsMine() 
	{
		return isMine;
	}
	public boolean getIsRevealed()
	{
		return isRevealed;
	}
	public boolean getIsMarked()
	{
		return isMarked;
	}
	public int getSurroundingMines()
	{
		return surroundingMines;
	}
	public void setIsMine(boolean isMine) 
	{
		this.isMine=isMine;
	}
	public void setIsRevealed(boolean isRevealed)
	{
		this.isRevealed = isRevealed;
	}
	public void setIsMarked(boolean isMarked)
	{
		this.isMarked = isMarked;
	}
	public void setSurroundingMines(int surroundingMines)
	{
		this.surroundingMines = surroundingMines;
	}
	
	
	private int row;
	private int col;
	private int surroundingMines;
	private boolean isMine;
	private boolean isRevealed;
	private boolean isMarked;
}
