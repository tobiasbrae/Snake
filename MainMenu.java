import java.awt.event.KeyEvent;

public class MainMenu extends Menu
{
	private boolean Music;
	
	public MainMenu(Main CurrentMain)
	{
		super(CurrentMain, Menu.VERTICAL, "Hauptmenü");
		
		addMenuItem(new MenuItem(1, "Zurück", false));
		addMenuItem(new MenuItem(2, "Neues Spiel", true));
		addMenuItem(new MenuItem(3, "Rekorde", true));
		addMenuItem(new MenuItem(4, "Beenden", true));
		
		setDefaultItem(2);
	}
	
	public void handleMenuItem()
	{
		if(Marked.getID() == 1)
		{
			CurrentMain.resumeToGame();
		}
		else if(Marked.getID() == 2)
		{
			CurrentMain.openGameDialog();
		}
		else if(Marked.getID() == 3)
		{
			CurrentMain.openHighscoreMenu();
		}
		else if(Marked.getID() == 4)
		{
			CurrentMain.openQuitDialog();
		}
	}
	
	public void enableGameOptions(boolean Enabled)
	{
		getMenuItem(1).setEnabled(Enabled);
	}
}