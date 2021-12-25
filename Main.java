import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.io.File;
import java.applet.Applet;
import java.applet.AudioClip;

public class Main extends JFrame
{
	public static int WinSizeX = 900;
	public static int WinSizeY = 700;
	
	public static int MenuPosX = 350;
	public static int MenuPosY = 150;
	public static int MenuSizeX = 200;
	public static int MenuSizeY = 400;
	public static int ItemSize = 20;
	public static int ItemSpace = 10;
	
	public static int GamePosX = 50;
	public static int GamePosY = 50;
	public static int GameSizeX = 800;
	public static int GameSizeY = 600;
	public static int SquareSize = 10;
	public static int GameSpeed = 40;  //20-60
	
	int LabelColor = 14474460;
	
	public URL HighDat;
	
	private Renderer CurrentRenderer;
	private GameRunner RunGame;
	private UserInput Keyboard;
	
	public RenderFrame CurrentFrame;
	public MainMenu Menu_Main;
	public HighscoreMenu Menu_Highscore;
	
	public QuitDialog Dialog_Quit;
	public GameDialog Dialog_Game;
	public OverDialog Dialog_Over;
	
	public Game GameFrame;
	
	public static void main(String args[])
	{
		Main main = new Main();
	}
	
	public Main()
	{
		super("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(WinSizeX, WinSizeY);
		setVisible(true);
		
		HighDat = Main.class.getResource("highscores.dat");
		
		Menu_Main = new MainMenu(this);
		Menu_Highscore = new HighscoreMenu(this);
		
		Dialog_Quit = new QuitDialog(this);
		Dialog_Game = new GameDialog(this);
		Dialog_Over = new OverDialog(this);
		
		RunGame = new GameRunner(this);
		RunGame.start();
		
		openMainMenu();
		
		CurrentRenderer = new Renderer(this);
		CurrentRenderer.start();
		
		Keyboard = new UserInput(this);
	}
	
	public void handleInput(KeyEvent InputKey)
	{
		if(CurrentFrame != null)
		{
			CurrentFrame.handleInput(InputKey);
		}
	}
	
	public void handleInputKey(KeyEvent InputKey)
	{
		if(CurrentFrame != null)
		{
			CurrentFrame.handleInputKey(InputKey);
		}
	}
	
	public void openMainMenu()
	{
		Menu_Main.reset();
		CurrentFrame = Menu_Main;
	}
	
	public void openHighscoreMenu()
	{
		Menu_Highscore.reset();
		CurrentFrame = Menu_Highscore;
	}
	
	public void startNewGame(int DesignMode)
	{		
		GameFrame = new Game(this, DesignMode);
		CurrentFrame = GameFrame;
		Menu_Main.enableGameOptions(true);
		Menu_Main.setDefaultItem(1);
	}
	
	public void setGameOver(int Score, int DesignMode)
	{
		if(Score >= Menu_Highscore.getLowestScore())
		{
			Menu_Highscore.reset();
			Menu_Highscore.openEditing(Score);
			CurrentFrame = Menu_Highscore;
		}
		else
		{
			Dialog_Over.reset(DesignMode);
			CurrentFrame = Dialog_Over;
		}
		GameFrame = null;
		Menu_Main.enableGameOptions(false);
		Menu_Main.setDefaultItem(2);
	}
	
	public void resumeToGame()
	{
		CurrentFrame = GameFrame;
	}
	
	public void openQuitDialog()
	{
		Dialog_Quit.reset();
		CurrentFrame = Dialog_Quit;
	}
	
	public void openGameDialog()
	{
		Dialog_Game.reset();
		CurrentFrame = Dialog_Game;
	}
	
	public void closeMenu()
	{
		if(CurrentFrame == Menu_Main)
		{
			if(GameFrame != null)
			{
				resumeToGame();
			}
			else
			{
				openQuitDialog();
			}
		}
		else
		{
			openMainMenu();
		}
	}
	
	public void shutdown()
	{
		try
		{
			CurrentRenderer.interrupt();
			RunGame.Close = true;
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}