import java.awt.Color;
import java.awt.Graphics;

public class PickupItem extends Square
{
	private int GamePosX, GamePosY, MaxWallX, MaxWallY;
	
	private int DesignMode;
	
	public PickupItem(int GamePosX, int GamePosY, int MaxWallX, int MaxWallY, int Size, int DesignMode)
	{
		super(0, 0, 0, 0, Size, 0);
		this.GamePosX = GamePosX;
		this.GamePosY = GamePosY;
		this.MaxWallX = MaxWallX;
		this.MaxWallY = MaxWallY;
		this.DesignMode = DesignMode;
	}
	
	public int getColor()
	{
		return Color;
	}
	
	public void setBounds(int PosX, int PosY, int ScrPosX, int ScrPosY, int Color)
	{
		setPosition(PosX, PosY, ScrPosX, ScrPosY);
		this.Color = Color;
	}
	
	public void calculateNewPosition(SnakeBody Head, int Color)
	{
		this.Color = Color;
		
		calculateNewPosition();
		while(collidesWithSnake(Head))
		{
			calculateNewPosition();
		}
		ScrPosX = GamePosX + PosX * Size;
		ScrPosY = GamePosY + PosY * Size;
	}
	
	
	private void calculateNewPosition()
	{
		double DPosX = Math.random() * (double) MaxWallX + 1.0;
		double DPosY = Math.random() * (double) MaxWallY + 1.0;
		PosX = (int) DPosX;
		PosY = (int) DPosY;
	}
	
	private boolean collidesWithSnake(SnakeBody CurBody)
	{
		while(CurBody != null)
		{
			if(collidesWithBody(CurBody))
			{
				return true;
			}
			CurBody = CurBody.After;
		}
		return false;
	}
	
	private boolean collidesWithBody(SnakeBody CurBody)
	{
		if(PosX == CurBody.PosX && PosY == CurBody.PosY)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(Color));
		if(DesignMode == 1)
		{
			g.fillRect(ScrPosX, ScrPosY, Size, Size);
		}
		else
		{
			g.fillRect(ScrPosX + 2, ScrPosY + 0,  6, 1);
			g.fillRect(ScrPosX + 1, ScrPosY + 1,  8, 1);
			g.fillRect(ScrPosX + 0, ScrPosY + 2, 10, 3);
			g.fillRect(ScrPosX + 3, ScrPosY + 4,  4, 5);
		}
	}
}