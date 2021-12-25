import java.awt.Graphics;
import java.awt.Color;

public class SnakeHead extends SnakeBody
{	
	private int Color2;
	
	public SnakeHead(int PosX, int PosY, int ScrPosX, int ScrPosY, int Size, int Color1, int Color2, int DesignMode)
	{
		super(PosX, PosY, ScrPosX, ScrPosY, Size, Color1, DesignMode);
		this.Color2 = Color2;
	}
	
	public boolean collidesWithSnake()
	{
		SnakeBody CurSnakeBody = After;
		
		while(CurSnakeBody != null)
		{			
			if(Direction == 1)
			{
				if(PosX + 1 == CurSnakeBody.PosX && PosY == CurSnakeBody.PosY)
				{
					return true;
				}
			}
			else if(Direction == 2)
			{
				if(PosY + 1 == CurSnakeBody.PosY && PosX == CurSnakeBody.PosX)
				{
					return true;
				}
			}
			else if(Direction == 3)
			{
				if(PosX - 1 == CurSnakeBody.PosX && PosY == CurSnakeBody.PosY)
				{
					return true;
				}
			}
			else if(Direction == 4)
			{
				if(PosY - 1 == CurSnakeBody.PosY && PosX == CurSnakeBody.PosX)
				{
					return true;
				}
			}
			CurSnakeBody = CurSnakeBody.After;
		}
		return false;
	}
	
	public boolean collidesWithSquare(Square CurSquare)
	{
		if(Direction == 1)
		{
			if(PosX + 1 == CurSquare.PosX && PosY == CurSquare.PosY)
			{
				return true;
			}
		}
		else if(Direction == 2)
		{
			if(PosY + 1 == CurSquare.PosY && PosX == CurSquare.PosX)
			{
				return true;
			}
		}
		else if(Direction == 3)
		{
			if(PosX - 1 == CurSquare.PosX && PosY == CurSquare.PosY)
			{
				return true;
			}
		}
		else if(Direction == 4)
		{
			if(PosY - 1 == CurSquare.PosY && PosX == CurSquare.PosX)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean collidesWithWall(int MaxX, int MaxY)
	{
		if(Direction == 1)
		{
			if(PosX + 1 == MaxX)
			{
				return true;
			}
		}
		else if(Direction == 2)
		{
			if(PosY + 1 == MaxY)
			{
				return true;
			}
		}
		else if(Direction == 3)
		{
			if(PosX - 1 == 0)
			{
				return true;
			}
		}
		else if(Direction == 4)
		{
			if(PosY - 1 == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public void setDirection(int Direction)
	{
		this.Direction = Direction;
	}
	
	public boolean collidesDirection(int Direction)
	{
		if(LastDirection == 1 && Direction == 3)
		{
			return true;
		}
		else if(LastDirection == 2 && Direction == 4)
		{
			return true;
		}
		else if(LastDirection == 3 && Direction == 1)
		{
			return true;
		}
		else if(LastDirection == 4 && Direction == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void moveForward()
	{
		LastPosX = PosX;
		LastPosY = PosY;
		LastScrPosX = ScrPosX;
		LastScrPosY = ScrPosY;
		LastDirection = Direction;	
		
		if(Direction == 1)
		{
			PosX += 1;
			ScrPosX += Size;
		}
		else if(Direction == 2)
		{
			PosY += 1;
			ScrPosY += Size;
		}
		else if(Direction == 3)
		{
			PosX -= 1;
			ScrPosX -= Size;
		}
		else if(Direction == 4)
		{
			PosY -= 1;
			ScrPosY -= Size;
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(Color));
		if(DesignMode == 1)
		{
			g.fillRect(ScrPosX, ScrPosY, Size, Size);
		}
		else if(Direction == 1)
		{
			g.fillRect(ScrPosX + 1, ScrPosY + 0,  6, 10);
			g.fillRect(ScrPosX + 0, ScrPosY + 1,  8, 8);
			g.fillRect(ScrPosX + 8, ScrPosY + 2,  1, 6);
			g.fillRect(ScrPosX + 9, ScrPosY + 3,  1, 4);
			
			g.setColor(new Color(Color2));
			g.fillRect(ScrPosX + 4, ScrPosY + 2, 2, 2);
			g.fillRect(ScrPosX + 4, ScrPosY + 6, 2, 2);
		}
		else if(Direction == 2)
		{
			g.fillRect(ScrPosX + 0, ScrPosY + 1, 10, 6);
			g.fillRect(ScrPosX + 1, ScrPosY + 0, 8, 8);
			g.fillRect(ScrPosX + 2, ScrPosY + 8, 6, 1);
			g.fillRect(ScrPosX + 3, ScrPosY + 9, 4, 1);
			
			g.setColor(new Color(Color2));
			g.fillRect(ScrPosX + 2, ScrPosY + 4, 2, 2);
			g.fillRect(ScrPosX + 6, ScrPosY + 4, 2, 2);
		}
		else if(Direction == 3)
		{
			g.fillRect(ScrPosX + 0, ScrPosY + 3, 1, 4);
			g.fillRect(ScrPosX + 1, ScrPosY + 2, 1, 6);
			g.fillRect(ScrPosX + 2, ScrPosY + 1, 8, 8);
			g.fillRect(ScrPosX + 3, ScrPosY + 0, 6, 10);
			
			g.setColor(new Color(Color2));
			g.fillRect(ScrPosX + 4, ScrPosY + 2, 2, 2);
			g.fillRect(ScrPosX + 4, ScrPosY + 6, 2, 2);
		}
		else if(Direction == 4)
		{
			g.fillRect(ScrPosX + 3, ScrPosY + 0,  4, 1);
			g.fillRect(ScrPosX + 2, ScrPosY + 1,  6, 1);
			g.fillRect(ScrPosX + 1, ScrPosY + 2,  8, 8);
			g.fillRect(ScrPosX + 0, ScrPosY + 3, 10, 6);
			
			g.setColor(new Color(Color2));
			g.fillRect(ScrPosX + 2, ScrPosY + 4, 2, 2);
			g.fillRect(ScrPosX + 6, ScrPosY + 4, 2, 2);
		}
	}
}