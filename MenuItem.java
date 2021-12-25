import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class MenuItem extends Label
{
	private int ColorDisabled = 11842740;
	private int ColorMarked = 7721946; //16777215;
	private int ColorDefault = 16777215; //14474460;	
	
	public MenuItem Before, After;
	private boolean Enabled, Marked;
	private int ID, PosX, PosY, Size;
	public int Score;
	public String OldName, Name;
	
	public MenuItem(int ID, String Caption, boolean Enabled)
	{
		super(0, 0, 0, 0, Caption);
		this.ID = ID;
		this.Enabled = Enabled;
		if(Enabled)
		{
			setColor(ColorDefault);
		}
		else
		{
			setColor(ColorDisabled);
		}
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public void setEnabled(boolean Enabled)
	{
		this.Enabled = Enabled;
		if(Enabled)
		{
			setColor(ColorDefault);
		}
		else
		{
			setColor(ColorDisabled);
		}
	}
	
	public boolean isEnabled()
	{
		return Enabled;
	}
	
	public void setMarked(boolean Marked)
	{
		this.Marked = Marked;
		if(Marked)
		{
			setColor(ColorMarked);
		}
		else
		{
			setColor(ColorDefault);
		}
	}
}