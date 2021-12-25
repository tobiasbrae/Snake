import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

public class Renderer extends Thread
{
	private Main CurrentMain;
	private Graphics CurrentGraphics;
	private Image BackBuffer;
	private boolean White;
	
	public Renderer(Main CurrentMain)
	{
		this.CurrentMain = CurrentMain;
		BackBuffer = CurrentMain.createImage(CurrentMain.WinSizeX, CurrentMain.WinSizeY);
		this.CurrentGraphics = BackBuffer.getGraphics();
	}
	
	public void run()
	{
		while(true)
		{			
			if(CurrentMain.CurrentFrame != null)
			{
				CurrentGraphics.setColor(CurrentMain.CurrentFrame.BackgroundColor);
				CurrentGraphics.fillRect(0, 0, CurrentMain.WinSizeX, CurrentMain.WinSizeY);
				CurrentMain.CurrentFrame.paint(CurrentGraphics);
			}			
			
			CurrentMain.getGraphics().drawImage(BackBuffer, 0, 0, null);
		}
	}
}