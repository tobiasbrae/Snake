import java.awt.event.KeyEvent;

public class OverDialog extends Menu
{
	private int DesignMode;
	
	public OverDialog(Main CurrentMain)
	{
		super(CurrentMain, Menu.HORIZONTAL, "Verloren! - Spiel neu starten?");
		
		addMenuItem(new MenuItem(1, "Ja", true));
		addMenuItem(new MenuItem(2, "Nein", true));
		
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
			CurrentMain.closeMenu();
		}
	}
	
	public void reset(int DesignMode)
	{
		if(Default != null)
		{
			markMenuItem(Default);
		}
		this.DesignMode = DesignMode;
	}
}