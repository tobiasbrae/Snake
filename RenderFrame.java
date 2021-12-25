import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class RenderFrame
{
	public RenderObject First, Last;
	public Color BackgroundColor = Color.black;
	
	public RenderFrame()
	{
	
	}
	
	public void addRenderObject(RenderObject Input)
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
	
	public void removeRenderObject(RenderObject Input)
	{
		if(First != null)
		{
			if(First == Last)
			{
				First = null;
				Last = null;
			}
			else if(First == Input)
			{
				Input.After.Before = null;
				First = Input.After;
			}
			else if(Last == Input)
			{
				Input.Before.After = null;
				Last = Input.Before;
			}
			else
			{
				Input.After.Before = Input.Before;
				Input.Before.After = Input.After;
			}
		}
	}
	
	public void paint(Graphics g)
	{
		RenderObject CurRenderObject = First;
		while(CurRenderObject != null)
		{
			CurRenderObject.paint(g);
			CurRenderObject = CurRenderObject.After;
		}
	}
	
	public void handleInput(KeyEvent InputKey)
	{
	
	}
	
	public void handleInputKey(KeyEvent InputKey)
	{
	
	}
}