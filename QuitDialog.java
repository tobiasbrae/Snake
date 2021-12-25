import java.awt.event.KeyEvent;

public class QuitDialog extends Menu
{
	public QuitDialog(Main CurrentMain)
	{
		super(CurrentMain, Menu.HORIZONTAL, "Beenden?");
		
		addMenuItem(new MenuItem(1, "Ja", true));
		addMenuItem(new MenuItem(2, "Nein", true));
		
		setDefaultItem(2);
	}
	
	public void handleMenuItem()
	{
		if(Marked.getID() == 1)
		{
			CurrentMain.shutdown();
		}
		else if(Marked.getID() == 2)
		{
			CurrentMain.closeMenu();
		}
	}
}