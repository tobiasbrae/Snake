public class SquareList
{
	public Square First, Last;
	
	public SquareList()
	{
	
	}
	
	public void addSquare(Square Input)
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