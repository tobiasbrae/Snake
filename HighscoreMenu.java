import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;

public class HighscoreMenu extends Menu
{
	private MenuItem Editing;
	private int Score;
	private int NameLength = 10;
	private int ScoreLength = 5;
	
	public HighscoreMenu(Main CurrentMain)
	{
		super(CurrentMain, Menu.VERTICAL, "Rekorde");
		
		int Counter = 1;
		
		try
		{
			Scanner Data = new Scanner(CurrentMain.HighDat.openStream());
			while(Data.hasNextLine() && Counter < 11)
			{
				Scanner CurrentLine = new Scanner(Data.nextLine());
				MenuItem Input = new MenuItem(Counter, "", false);
				Input.Name = CurrentLine.next();
				Input.Score = CurrentLine.nextInt();
				Input.setText(Counter + ". " + lengthString(Input.Name, NameLength, false) + "   " + lengthString(Input.Score + "", ScoreLength, true));
				Input.setColor(CurrentMain.LabelColor);
				addMenuItem(Input);
				Counter++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		while(Counter < 11)
		{
			MenuItem Input = new MenuItem(Counter, Counter + ". " + lengthString("...", NameLength, false) + "   " + lengthString("...", ScoreLength, true), false);
			Input.Name = "...";
			Input.Score = 0;
			addMenuItem(Input);
			Counter++;
		}
		
		addMenuItem(new MenuItem(11, "", false));
		addMenuItem(new MenuItem(12, "Zurück", true));
		setDefaultItem(12);
	}
	
	public int getLowestScore()
	{
		return getMenuItem(10).Score;
	}
	
	public void openEditing(int Score)
	{
		MenuItem CurMenuItem = First;
		while(CurMenuItem.getID() < 11)
		{
			if(CurMenuItem.Score <= Score)
			{
				break;
			}
			CurMenuItem = CurMenuItem.After;
		}
		
		Editing = new MenuItem(20, "", false);
		Editing.Name = "Name";
		Editing.Score = Score;
		Editing.setBounds(0, 0, 20);
		Editing.setColor(CurrentMain.LabelColor);
		
		if(CurMenuItem == First)
		{
			First.Before = Editing;
			Editing.After = First;
			First = Editing;
		}
		else
		{
			Editing.After = CurMenuItem;
			Editing.Before = CurMenuItem.Before;
			Editing.Before.After = Editing;
			Editing.After.Before = Editing;
		}
		
		MenuItem RemoveItem = getMenuItem(10);
		RemoveItem.After.Before = RemoveItem.Before;
		RemoveItem.Before.After = RemoveItem.After;
		
		removeRenderObject(RemoveItem);
		
		reNumber();
		setEditingText(false);
		
		addRenderObject(Editing);
	}
	
	private void reNumber()
	{
		int Counter = 1;
		MenuItem CurMenuItem = First;
		while(Counter < 11)
		{
			CurMenuItem.setID(Counter);
			CurMenuItem.setText(Counter + ". " + lengthString(CurMenuItem.Name, NameLength, false) + "   " + lengthString(CurMenuItem.Score + "", ScoreLength, true));
			Counter++;
			CurMenuItem = CurMenuItem.After;
		}
	}
	
	private void setEditingText(boolean Finished)
	{
		if(Finished)
		{
			Editing.setText(Editing.getID() + ". " + lengthString(Editing.Name, NameLength, false) + "   " + lengthString(Editing.Score + "", ScoreLength, true));
		}
		else
		{
			Editing.setText(Editing.getID() + ". " + lengthString(Editing.Name + "_", NameLength + 1, false) + "  " + lengthString(Editing.Score + "", ScoreLength, true));
		}
		calculatePositions();
	}
	
	public void handleInput(KeyEvent InputKey)
	{
		if(Editing == null)
		{
			if(InputKey.getKeyCode() == KeyEvent.VK_RIGHT && Direction == 1 || InputKey.getKeyCode() == KeyEvent.VK_DOWN && Direction == 2)
			{
				markNext();
			}
			else if(InputKey.getKeyCode() == KeyEvent.VK_LEFT && Direction == 1 || InputKey.getKeyCode() == KeyEvent.VK_UP && Direction == 2)
			{
				markPrevious();
			}
			else if(InputKey.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(Marked.getID() == 12)
				{
					CurrentMain.closeMenu();
				}
			}
			else if(InputKey.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				CurrentMain.closeMenu();
			}
		}
		else
		{
			if(InputKey.getKeyCode() == KeyEvent.VK_ENTER)
			{
				finishEditing();
			}
			else if(InputKey.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				Editing.Name = "Name";
				finishEditing();
			}
			else if(InputKey.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			{
				if(Editing.Name.length() > 0)
				{
					Editing.Name = Editing.Name.substring(0, Editing.Name.length() - 1);
					setEditingText(false);
				}
			}
		}
	}
	
	public void handleInputKey(KeyEvent InputKey)
	{
		if(Editing != null)
		{
			if(Character.getNumericValue(InputKey.getKeyChar()) > 0)
			{
				if(Editing.Name.length() < NameLength)
				{
					Editing.Name = Editing.Name + InputKey.getKeyChar();
					setEditingText(false);
				}
			}
		}
	}
	
	private void finishEditing()
	{
		try
		{
			File Target = new File("highscores.dat");
			Target.createNewFile();
			FileOutputStream TargetOutput = new FileOutputStream(Target);
			
			MenuItem CurMenuItem = First;
			while(CurMenuItem != null && CurMenuItem.Score > 0)
			{
				String Output = CurMenuItem.Name + " " + CurMenuItem.Score + String.format("%n");
				TargetOutput.write(Output.getBytes());
				CurMenuItem = CurMenuItem.After;
			}
		
			TargetOutput.close();
			setEditingText(true);
			Editing = null;
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private String lengthString(String Input, int Length, boolean Before)
	{
		if(Input.length() > Length)
		{
			return Input.substring(0, Length);
		}
		else
		{
			while(Input.length() < Length)
			{
				if(Before)
				{
					Input = " " + Input;
				}
				else
				{
					Input = Input + " ";
				}
			}
			return Input;
		}
	}
}