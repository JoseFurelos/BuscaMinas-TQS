package Model;

public class Cell {
	public Cell()
	{
		
	}
	
	public Cell(int col, int row)
	{
		
	}
	
	public void mark()
	{
		
	}
	public void reveal()
	{
		
	}
	
	public int getRow()
	{
		return 0;
	}
	public int getCol()
	{
		return 0;
	}
	public boolean getIsMine() 
	{
		return true;
	}
	public boolean getIsRevealed()
	{
		return true;
	}
	public boolean getIsMarked()
	{
		return true;
	}
	public int getSurroundingMines()
	{
		return -1;
	}
	public void setIsMine(boolean mine) 
	{
		
	}
	public void setIsRevealed(boolean revealed)
	{
		
	}
	public void setIsMarked(boolean marked)
	{
		
	}
	public void setSurroundingMines(int mines)
	{
		
	}
	
	
	private int row;
	private int col;
	private int surroundingMines;
	private boolean isMine;
	private boolean isRevealed;
	private boolean isMarked;
}
