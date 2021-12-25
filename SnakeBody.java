import java.awt.Graphics;
import java.awt.Color;

public class SnakeBody extends Square
{
	public SnakeBody Before, After;
	
	public int LastPosX, LastPosY, LastScrPosX, LastScrPosY, Direction = 1, LastDirection = 1;
	
	protected int DesignMode;
	
	public SnakeBody(int PosX, int PosY, int ScrPosX, int ScrPosY, int Size, int Color, int DesignMode)
	{
		super(PosX, PosY, ScrPosX, ScrPosY, Size, Color);
		this.DesignMode = DesignMode;
	}
	
	public void moveForward()
	{
		LastPosX = PosX;
		LastPosY = PosY;
		LastScrPosX = ScrPosX;
		LastScrPosY = ScrPosY;
		LastDirection = Direction;
		
		PosX = Before.LastPosX;
		PosY = Before.LastPosY;
		ScrPosX = Before.LastScrPosX;
		ScrPosY = Before.LastScrPosY;
		Direction = Before.LastDirection;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(Color));
		if(DesignMode == 1)
		{
			g.fillRect(ScrPosX, ScrPosY, Size, Size);
		}		
		else if(After != null)
		{
			g.fillRect(ScrPosX + 1, ScrPosY, Size - 2, Size);
			g.fillRect(ScrPosX, ScrPosY + 1, Size, Size - 2);
		}
		else
		{
			if(Direction == 1)
			{
				g.fillRect(ScrPosX + 0, ScrPosY + 4, 2, 2);
				g.fillRect(ScrPosX + 2, ScrPosY + 3, 2, 4);
				g.fillRect(ScrPosX + 4, ScrPosY + 2, 2, 6);
				g.fillRect(ScrPosX + 6, ScrPosY + 1, 4, 8);
				g.fillRect(ScrPosX + 7, ScrPosY + 0, 2, 10);
			}
			else if(Direction == 2)
			{
				g.fillRect(ScrPosX + 4, ScrPosY + 0, 2, 2);
				g.fillRect(ScrPosX + 3, ScrPosY + 2, 4, 2);
				g.fillRect(ScrPosX + 2, ScrPosY + 4, 6, 2);
				g.fillRect(ScrPosX + 1, ScrPosY + 6, 8, 4);
				g.fillRect(ScrPosX + 0, ScrPosY + 7, 10, 2);
			}
			else if(Direction == 3)
			{
				g.fillRect(ScrPosX + 1, ScrPosY + 0, 2, 10);
				g.fillRect(ScrPosX + 0, ScrPosY + 1, 4, 8);
				g.fillRect(ScrPosX + 4, ScrPosY + 2, 2, 6);
				g.fillRect(ScrPosX + 6, ScrPosY + 3, 2, 4);
				g.fillRect(ScrPosX + 8, ScrPosY + 4, 2, 2);
			}
			else if(Direction == 4)
			{
				g.fillRect(ScrPosX + 0, ScrPosY + 1, 10, 2);
				g.fillRect(ScrPosX + 1, ScrPosY + 0, 8, 4);
				g.fillRect(ScrPosX + 2, ScrPosY + 4, 6, 2);
				g.fillRect(ScrPosX + 3, ScrPosY + 6, 4, 2);
				g.fillRect(ScrPosX + 4, ScrPosY + 8, 2, 2);
			}
		}
	}
}