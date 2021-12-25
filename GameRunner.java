public class GameRunner extends Thread
{
	private Main CurrentMain;
	
	private long LastStep;
	
	public boolean Close;
	
	public GameRunner(Main CurrentMain)
	{
		this.CurrentMain = CurrentMain;
	}
	
	public void run()
	{		
		LastStep = System.currentTimeMillis();
		while(!Close)
		{			
			try
			{
				sleep(10);
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
			if(CurrentMain.CurrentFrame == CurrentMain.GameFrame)
			{				
				if(CurrentMain.isFocused())
				{				
					if(System.currentTimeMillis() - LastStep >= CurrentMain.GameSpeed)
					{
						LastStep = System.currentTimeMillis();
						CurrentMain.GameFrame.moveForward();
					}
				}
				else
				{
					CurrentMain.openMainMenu();
				}
			}
			else
			{
				LastStep = System.currentTimeMillis();
			}
		}
	}
}