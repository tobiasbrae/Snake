import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.SwingUtilities;

public class Label extends RenderObject
{
	private int PosX, PosY, Size, TextColor;
	private String Text;
	
	public Label(int PosX, int PosY, int Size, int TextColor, String Text)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.Size = Size;
		this.TextColor = TextColor;
		this.Text = Text;
	}
	
	public void paint(Graphics g)
	{
		g.setFont(new Font(Font.SERIF, Font.BOLD, Size));
		g.setColor(new Color(TextColor));
		
		g.drawString(Text, PosX, PosY);
	}
	
	public int getWidth()
	{		
		FontMetrics CurrentFontMetrics = new Canvas().getFontMetrics(new Font(Font.SERIF, Font.BOLD, Size));
		
		return CurrentFontMetrics.stringWidth(Text);
	}
	
	public void setPosition(int PosX, int PosY)
	{
		this.PosX = PosX;
		this.PosY = PosY;
	}
	
	public void setBounds(int PosX, int PosY, int Size)
	{
		this.PosX = PosX;
		this.PosY = PosY;
		this.Size = Size;
	}
	
	public void setText(String Text)
	{
		this.Text = Text;
	}
	
	public String getText()
	{
		return Text;
	}
	
	public void setColor(int TextColor)
	{
		this.TextColor = TextColor; 
	}
}