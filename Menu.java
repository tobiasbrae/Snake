import java.awt.event.KeyEvent;

public class Menu extends RenderFrame
{
	public static int HORIZONTAL = 1;
	public static int VERTICAL = 2;	
	
	protected Main CurrentMain;
	protected MenuItem First, Last, Marked, Default;
	private Label Heading;
	
	protected int PosX, PosY, SizeX, SizeY, Direction;
	protected int ItemCounter, ItemsWidth;
	protected int ItemSize, ItemSpace;
	
	public Menu(Main CurrentMain, int Direction, String Heading)
	{
		this.CurrentMain = CurrentMain;
		this.PosX = CurrentMain.MenuPosX;
		this.PosY = CurrentMain.MenuPosY;
		this.SizeX = CurrentMain.MenuSizeX;
		this.SizeY = CurrentMain.MenuSizeY;
		this.ItemSize = CurrentMain.ItemSize;
		this.ItemSpace = CurrentMain.ItemSpace;
		this.Direction = Direction;
		
		this.Heading = new Label(PosX, PosY, 20, CurrentMain.LabelColor, Heading);
		
		int PositionX = PosX + SizeX / 2;
		PositionX -= this.Heading.getWidth() / 2;
		
		this.Heading.setPosition(PositionX, PosY);
		
		addRenderObject(this.Heading);
	}
	
	public void addMenuItem(MenuItem Input)
	{
		Input.setBounds(0, 0, ItemSize);
		
		if(Direction == HORIZONTAL)
		{
			ItemCounter++;
			ItemsWidth += Input.getWidth();
		}
		
		if(First == null)
		{
			First = Input;
			Last = Input;
			if(Input.isEnabled())
			{
				Marked = Input;
				Input.setMarked(true);
			}
		}
		else
		{
			Last.After = Input;
			Input.Before = Last;
			Last = Input;
			if(Marked == null && Input.isEnabled())
			{
				Marked = Input;
				Input.setMarked(true);
			}
		}
		
		calculatePositions();
		
		addRenderObject(Input);
	}
	
	protected void calculatePositions()
	{
		if(First == Last)
		{
			First.setPosition(PosX + ItemSpace, PosY + ItemSpace + 30);
		}
		else
		{
			MenuItem CurMenuItem = First;
			if(Direction == HORIZONTAL)
			{
				int Space = PosX + (SizeX - ItemsWidth) / (ItemCounter + 1);
				int PositionX = Space;
				while(CurMenuItem != null)
				{
					CurMenuItem.setPosition(PositionX, PosY + ItemSpace + 30);
					PositionX += ItemSpace + CurMenuItem.getWidth();
					CurMenuItem = CurMenuItem.After;
				}
			}
			else if(Direction == VERTICAL)
			{			
				int PositionY = PosY + ItemSpace + 30;
				while(CurMenuItem != null)
				{
					int PositionX = PosX + (SizeX - CurMenuItem.getWidth()) / 2;					
					CurMenuItem.setPosition(PositionX, PositionY);
					PositionY += ItemSpace + ItemSize;
					
					CurMenuItem = CurMenuItem.After;
				}
			}
		}
	}
	
	public void handleInput(KeyEvent InputKey)
	{
		if(InputKey.getKeyCode() == KeyEvent.VK_RIGHT && Direction == 1 || InputKey.getKeyCode() == KeyEvent.VK_DOWN && Direction == 2)
		{
			markNext();
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_LEFT && Direction == 1 || InputKey.getKeyCode() == KeyEvent.VK_UP && Direction == 2)
		{
			markPrevious();
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_ENTER)
		{
			handleMenuItem();
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			CurrentMain.closeMenu();
		}
	}
	
	public void handleMenuItem()
	{

	}
	
	public void markNext()
	{
		if(First != Last)
		{
			Marked.setMarked(false);
			MenuItem CurMenuItem = Marked;
			CurMenuItem = giveNextMenuItem(CurMenuItem);
			while(!CurMenuItem.isEnabled())
			{
				CurMenuItem = giveNextMenuItem(CurMenuItem);
			}
			Marked = CurMenuItem;
			Marked.setMarked(true);
		}
	}
	
	public MenuItem giveNextMenuItem(MenuItem CurMenuItem)
	{
		if(CurMenuItem == Last)
		{
			return First;
		}
		else
		{
			return CurMenuItem.After;
		}
	}
	
	public void markPrevious()
	{
		if(First != Last)
		{
			Marked.setMarked(false);
			MenuItem CurMenuItem = Marked;
			CurMenuItem = givePreviosMenuItem(CurMenuItem);
			while(!CurMenuItem.isEnabled())
			{
				CurMenuItem = givePreviosMenuItem(CurMenuItem);
			}
			Marked = CurMenuItem;
			Marked.setMarked(true);
		}
	}
	
	public MenuItem givePreviosMenuItem(MenuItem CurMenuItem)
	{
		if(CurMenuItem == First)
		{
			return Last;
		}
		else
		{
			return CurMenuItem.Before;
		}
	}
	
	protected MenuItem getMenuItem(int ID)
	{
		MenuItem CurMenuItem = First;
		while(CurMenuItem != null)
		{
			if(CurMenuItem.getID() == ID)
			{
				return CurMenuItem;
			}
			CurMenuItem = CurMenuItem.After;
		}
		return null;
	}
	
	protected void markMenuItem(MenuItem CurMenuItem)
	{
		Marked.setMarked(false);
		Marked = CurMenuItem;
		Marked.setMarked(true);
	}
	
	public void setDefaultItem(int ID)
	{
		Default = getMenuItem(ID);
	}
	
	public void reset()
	{
		if(Default != null)
		{
			markMenuItem(Default);
		}
	}
}