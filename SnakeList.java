public class SnakeList
{
	public SnakeBody First, Last;
	
	private int SquareSize;
	
	public SnakeList(int SquareSize)
	{
		this.SquareSize = SquareSize;
	}
	
	public void addSnakeBody(int Color, int DesignMode)
	{
		SnakeBody Input = new SnakeBody(0, 0, 0, 0, SquareSize, Color, DesignMode);
		
		Input.PosX = Last.LastPosX;
		Input.PosY = Last.LastPosY;		
		Input.ScrPosX = Last.LastScrPosX;	
		Input.ScrPosY = Last.LastScrPosY;	
		Input.Direction = Last.LastDirection;
		
		addSnakeBody(Input);
	}
	
	public void addSnakeBody(SnakeBody Input)
	{
		if(First == null)
		{
			First = Input;
			Last = Input;
		}
		else
		{
			Last.After = Input;
			Input.Before = Last;
			Last = Input;
		}
	}
}