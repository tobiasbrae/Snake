import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

public class UserInput implements KeyListener
{
	private Main CurrentMain;
	
	public UserInput(Main CurrentMain)
	{
		this.CurrentMain = CurrentMain;
		CurrentMain.addKeyListener(this);
	}
	
	public void keyTyped(KeyEvent e)
	{
		CurrentMain.handleInputKey(e);
	}
	
	public void keyPressed(KeyEvent e)
	{
        CurrentMain.handleInput(e);
    }

    public void keyReleased(KeyEvent e)
	{
        //Nothing to do here
    } 
}