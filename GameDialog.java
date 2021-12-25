import java.awt.event.KeyEvent;

public class GameDialog extends Menu
{
	private int DesignMode = 1;
	
	public GameDialog(Main CurrentMain)
	{
		super(CurrentMain, Menu.VERTICAL, "Neues Spiel");
		
		addMenuItem(new MenuItem(1, "Starten", true));
		addMenuItem(new MenuItem(2, "Design: Klassisch", true));
		addMenuItem(new MenuItem(3, "Abbrechen", true));
		
		setDefaultItem(1);
	}
	
	public void handleMenuItem()
	{
		if(Marked.getID() == 1)
		{
			CurrentMain.startNewGame(DesignMode);
		}
		else if(Marked.getID() == 2)
		{
			if(DesignMode == 1)
			{
				DesignMode = 2;
				getMenuItem(2).setText("Design: Bunt");
			}
			else if(DesignMode == 2)
			{
				DesignMode = 3;
				getMenuItem(2).setText("Design: Epilepsie");
			}
			else if(DesignMode == 3)
			{
				DesignMode = 1;
				getMenuItem(2).setText("Design: Klassisch");
			}
		}
		else if(Marked.getID() == 3)
		{
			CurrentMain.closeMenu();
		}
	}
	
	public void reset()
	{
		if(Default != null)
		{
			markMenuItem(Default);
		}
		DesignMode = 1;
		getMenuItem(2).setText("Design: Klassisch");
	}
}