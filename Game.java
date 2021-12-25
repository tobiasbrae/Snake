import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Game extends RenderFrame
{
	private Main CurrentMain;
	private Label Label_Score;
	private SquareList Walls;
	private SnakeList Snake;
	private SnakeHead Head;
	private PickupItem Pickup;
	
	private int PosX, PosY, SizeX, SizeY, SquareSize;
	private int Squares_Horizontal, Squares_Vertical;
	private int Colors[];
	private int DesignMode = 1, Score;
	private boolean White = true;
	
	public Game(Main CurrentMain, int DesignMode)
	{		
		this.CurrentMain = CurrentMain;
		this.PosX = CurrentMain.GamePosX;
		this.PosY = CurrentMain.GamePosY;
		this.SizeX = CurrentMain.GameSizeX;
		this.SizeY = CurrentMain.GameSizeY;
		this.SquareSize = CurrentMain.SquareSize;
		this.DesignMode = DesignMode;
		
		Colors = new int[7];
		Colors[0] = 16747786;
		Colors[1] = 12782090;
		Colors[2] = 5053931;
		Colors[3] = 16771085;
		Colors[4] = 1621049;
		Colors[5] = 14490603;
		Colors[6] = 1829860;
		
		Label_Score = new Label(PosX, PosY, 20, getRandomColor(), "Punktestand:  0");
		Label_Score.setPosition(PosX + (SizeX - Label_Score.getWidth()) / 2, PosY);
		
		addRenderObject(Label_Score);
		
		Walls = new SquareList();
		
		Squares_Horizontal = SizeX / SquareSize;
		Squares_Vertical = SizeY / SquareSize;
		
		int LowerPosY = Squares_Vertical - 1;
		int LowerScrPosY = PosY + LowerPosY * SquareSize;
		
		int HigherPosX = Squares_Horizontal - 1;
		int HigherScrPosX = PosX + HigherPosX * SquareSize;
		
		for(int i = 0; i < Squares_Horizontal; i++)
		{
			addWallSquare(new Square(i, 0, PosX + SquareSize * i, PosY + 30, SquareSize, getRandomColor()));
			addWallSquare(new Square(i, LowerPosY, PosX + SquareSize * i, LowerScrPosY + 30, SquareSize, getRandomColor()));
		}
		for(int i = 0; i < Squares_Vertical; i++)
		{
			addWallSquare(new Square(0, i, PosX, PosY + SquareSize * i + 30, SquareSize, getRandomColor()));
			addWallSquare(new Square(HigherPosX, i, HigherScrPosX, PosY + SquareSize * i + 30, SquareSize, getRandomColor()));
		}
		
		Snake = new SnakeList(SquareSize);
		for(int i = 0; i < 7; i++)
		{
			SnakeBody Input = new SnakeBody(30 - i, 20, PosX + SquareSize * (30 - i), PosY + SquareSize * 20 + 30, SquareSize, getRandomColor(), DesignMode);
			Snake.addSnakeBody(Input);
			addRenderObject(Input);
		}
		
		Head = new SnakeHead(31, 20, PosX + SquareSize * 31, PosY + SquareSize * 20 + 30, SquareSize, getRandomColor(), 0, DesignMode);
		Head.After = Snake.First;
		Snake.First.Before = Head;
		addRenderObject(Head);
		
		Pickup = new PickupItem(PosX, PosY + 30, Squares_Horizontal - 2, Squares_Vertical - 2, SquareSize, DesignMode);
		Pickup.calculateNewPosition(Head, getRandomColor());
		addRenderObject(Pickup);		
	}
	
	private int getRandomColor()
	{
		if(DesignMode == 1)
		{
			return 16777215;
		}
		else
		{
			return Colors[(int) (Math.random() * (double) Colors.length)];
		}
	}
	
	public void addSnakeBody()
	{
		Snake.addSnakeBody(Pickup.getColor(), DesignMode);
		addRenderObject(Snake.Last);
	}
	
	public void addSnakeBody(SnakeBody Input)
	{
		Snake.addSnakeBody(Input);
		addRenderObject(Input);
	}
	
	public void addWallSquare(Square Input)
	{
		Walls.addSquare(Input);
		addRenderObject(Input);
	}
	
	public void moveForward()
	{
		if(Head.collidesWithWall(Squares_Horizontal - 1, Squares_Vertical - 1) || Head.collidesWithSnake())
		{
			CurrentMain.setGameOver(Score, DesignMode);
		}
		else
		{
			if(DesignMode == 3)
			{
					White = !White;
			}
			else
			{
				White = false;
			}
			
			if(White)
			{
				BackgroundColor = Color.white;
			}
			else
			{
				BackgroundColor = Color.black;
			}
			
			if(Head.collidesWithSquare(Pickup))
			{
				addSnakeBody();
				higherScore();
				if(DesignMode == 2)
				{
					Label_Score.setColor(Pickup.Color);
				}
				Pickup.calculateNewPosition(Head, getRandomColor());
			}
			SnakeBody CurBody = Head;
			while(CurBody != null)
			{
				CurBody.moveForward();
				if(DesignMode == 3)
				{
					CurBody.setColor(getRandomColor());
				}
				CurBody = CurBody.After;
			}
			if(DesignMode == 3)
			{
				Label_Score.setColor(getRandomColor());
				Pickup.setColor(getRandomColor());
				Square CurSquare = Walls.First;
				while(CurSquare != null)
				{
					CurSquare.setColor(getRandomColor());
					CurSquare = CurSquare.After;
				}
			}
		}
	}
	
	public void higherScore()
	{
		Score += 10;
		Label_Score.setText("Punktestand:  " + Score);
	}
	
	public void handleInput(KeyEvent InputKey)
	{
		if(InputKey.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			CurrentMain.openMainMenu();
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(!Head.collidesDirection(1))
			{
				Head.setDirection(1);
			}
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if(!Head.collidesDirection(2))
			{
				Head.setDirection(2);
			}
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(!Head.collidesDirection(3))
			{
				Head.setDirection(3);
			}
		}
		else if(InputKey.getKeyCode() == KeyEvent.VK_UP)
		{
			if(!Head.collidesDirection(4))
			{
				Head.setDirection(4);
			}
		}
	}
	
	public int getDesignMode()
	{
		return DesignMode;
	}
}