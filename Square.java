import java.awt.Graphics;
import java.awt.Color;

public class Square extends RenderObject
{
	public Square Before, After;
	
	public int PosX, PosY, ScrPosX, ScrPosY, Size, Color;
	
	public Square(int PosX, int PosY, int ScrPosX, int ScrPosY, int Size, int Color)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.ScrPosX = ScrPosX;
		this.ScrPosY = ScrPosY;
		this.Size = Size;
		this.Color = Color;
	}
	
	public void setColor(int Color)
	{
		this.Color = Color;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(Color));
		
		g.fillRect(ScrPosX, ScrPosY, Size, Size);
	}
	
	public void setPosition(int PosX, int PosY, int ScrPosX, int ScrPosY)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.ScrPosX = ScrPosX;
		this.ScrPosY = ScrPosY;
	}
}