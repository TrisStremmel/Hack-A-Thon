package forHackAThon;
import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle.*;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class DotsGame
{
	public static void main( String[ ] args)
	{

				JFrame frame= new JFrame("Dots and Lines");					//creates the jframe that displays the game
				frame.setVisible(false);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);

	}
}

class Dots4Win extends JPanel
{
	public static void setSize(int s)												//so can be set from outside of class
	{
		size = s;
	}
	public static void setSinglePlayer(boolean s)										//so can be set from outside of class
	{
		singlePlayer = s;
	}
	public static int size; 															//creates initial values for the graph and other variables
	public static boolean singlePlayer;
	private int[] lastMove = new int[3];
	private Rectangle[][] dots = new Rectangle[size+1][size+1] ;
	private Rectangle[][] hr = new Rectangle[size+1][size+1] ;
	private Rectangle[][] vr = new Rectangle[size+1][size+1] ;
	private int[][] vstatus = new int[size+1][size] ;
	private int[][] hstatus = new int[size][size+1] ;
	private int[][] boxes;
	private int score1 = 0;
	private int score2 = 0;
	private int turn = 0;
	private int numRec;
	private int recDif;
	private int recTemp;
	


	Dots4Win()																		//controls the game
	{
		size = front.size;
		boxes = new int [size-1][size-1];
		turn = 2;
		for ( int i = 0; i < size; i++ )													//creates the graph of nodes and edges for the game
		{
			for ( int j = 0; j < size; j++ )
			{
				dots[i][j] = new Rectangle( 100 + 100*i, 100 + 100*j, 25, 25 ) ;
				hr[i][j] = new Rectangle(105 + 100*i, 107 + 100*j, 100, 10);
				vr[i][j] = new Rectangle(107 + 100*i, 105 + 100*j, 10, 100);
			}
		}
		for ( int i = 0; i < size; i++ )													//setting all the initial edges to zero
		{
			for ( int j = 0; j < size-1; j++ )
			{
				vstatus[i][j] = 0 ;
			}
		}
		for ( int i = 0; i < size-1; i++ )													//setting all the initial edges to zero
		{
			for ( int j = 0; j < size; j++ )
			{
				hstatus[i][j] = 0 ;
			}
		}
		addMouseListener( new MouseCatcher() ) ;        											// add a mouse listener
		setVisible( true );
	}
	public void paint( Graphics gc )																								//draws the graph and updates based on user input
	{
		numRec=0;
		for ( int i = 0; i < size; i++ )																							//checks for boxes and paints them the color of player who finished it
		{
			for ( int j = 0; j < size-1; j++ )
			{

				if(hstatus[i][j] == 1 && hstatus[i][j+1] == 1 && vstatus[i][j] == 1 && vstatus[i+1][j] == 1) {
					numRec++;
					if(turn == 1)
					{
						if(boxes[i][j] != 1 && boxes[i][j] != 2)
							boxes[i][j] = 1;
					}
					else if(turn == 2)
					{
						if(boxes[i][j] != 1 && boxes[i][j] != 2)
							boxes[i][j] = 2;
					}
					if(boxes[i][j] == 1)
						gc.setColor(Color.green);
					if(boxes[i][j] == 2)
						gc.setColor(Color.MAGENTA);

					gc.fillRect(hr[i][j].x +7, hr[i][j].y, 100, 100);

				}
				gc.setColor(Color.lightGray);
				if(vstatus[i][j] == 1) 
					gc.setColor(Color.RED);
				if(lastMove[0] == i && lastMove[1] == j && lastMove[2] == 1) {
					gc.setColor(Color.BLUE);
				}
				gc.fillRect(vr[i][j].x, vr[i][j].y, vr[i][j].width, vr[i][j].height);
			}
		}
		recDif = numRec - recTemp;																	//calculates score
		recTemp = numRec;
		if(turn == 1) 
		{
			score1+=recDif;
			turn = 2;
			if(recDif != 0)
				turn = 1;
		}
		else if (turn == 2)
		{
			score2+=recDif;
			turn = 1;
			if(recDif != 0)
				turn = 2;
		}
		for ( int i = 0; i < size-1; i++ )																			//draws edges and checks if it has been activated
		{
			for ( int j = 0; j < size; j++ )
			{
				gc.setColor(Color.lightGray);
				if(hstatus[i][j] == 1) 
					gc.setColor(Color.RED);
				if(lastMove[0] == i && lastMove[1] == j && lastMove[2] == 2) {
					gc.setColor(Color.BLUE);
				}
				gc.fillRect(hr[i][j].x, hr[i][j].y, hr[i][j].width, hr[i][j].height);

			}
		}

		for ( int i = 0; i < size; i++ )																			//draws the dots
		{
			for ( int j = 0; j < size; j++ )
			{
				gc.setColor(Color.BLACK);
				gc.fillOval(dots[i][j].x, dots[i][j].y, dots[i][j].width, dots[i][j].height);
			}
		}
		gc.setColor(Color.white);																							//draws the scores
		gc.fillRect(205, 65, 40, 20);
		gc.fillRect(420, 65, 40, 20);
		gc.fillRect(815, 65, 40, 20);
		gc.setColor(Color.black);
		gc.setFont(new Font("Courier", Font.BOLD,50));
		gc.drawString("Points", 100, 50);
		gc.drawString("Turn:", 600, 50);
		gc.setFont(new Font("Courier", Font.BOLD,30));
		gc.drawString("Player 1: " + score1, 25, 85);
		gc.drawString("Player 2: " + score2, 250, 85);
		gc.drawString("Player " + Integer.toString(turn), 700, 85);

		if((score1 + score2) == ((size-1)*(size-1))) {
			gc.setColor(Color.white);
			gc.fillRect(0, 0, 1000, 1000);
			gc.setFont(new Font("Courier", Font.BOLD,50));
			gc.setColor(Color.black);
			if(score1>score2) {
				gc.drawString("Player 1 wins with " + score1 + " points!", 100, 400);
			}
			else if (score2>score1){
				gc.drawString("Player 2 wins with " + score2 + " points!", 100, 400);
			}
			else {
				gc.setFont(new Font("Courier", Font.BOLD,35));
				gc.drawString("Player 1 and Player 2 tie with " + score2 + " points each!", 35, 400);
			}
		}
	}
	class MouseCatcher extends MouseAdapter																		//takes user input and fills edges based on player click
	{
		public void mousePressed(MouseEvent evt )
		{
			singlePlayer = front.singlePlayer;
			if(singlePlayer)
			{
				if(turn == 1)
				{
					Point ptMouse = evt.getPoint() ;
					for ( int i = 0; i < size; i++ )
					{
						for ( int j = 0; j < size-1; j++ )
						{
							if ( vr[i][j].contains( ptMouse ) && (vstatus[i][j] == 0))
							{
								vstatus[i][j] = 1 ;
								lastMove[0] = i;
								lastMove[1] = j;
								lastMove[2] = 1;
		
								repaint();
							}
						}
					}
					for ( int i = 0; i < size-1; i++ )
					{
						for ( int j = 0; j < size; j++ )
						{
							if(hr[i][j].contains( ptMouse ) && (hstatus[i][j] == 0) ) {
								hstatus[i][j] = 1 ;
								lastMove[0] = i;
								lastMove[1] = j;
								lastMove[2] = 2;
		
								repaint();
							}
						}
					}
				}
				else
				{
					int[] r = whatCanClose();												//AI
					if(r[2] == 0)
					{
						int k = (int)(Math.random()*((1)+1));
						outer: if(k == 0)
						{
							for ( int i = 0; i < size; i++ )
							{
								for ( int j = 0; j < size - 1; j++ )
								{
									if((vstatus[i][j] == 0))
									{
										break outer;
									}
								}
							}
							k = 1;
						}
						outer: if(k == 1)
						{
							for ( int i = 0; i < size-1; i++ )
							{
								for ( int j = 0; j < size; j++ )
								{
									if((hstatus[i][j] == 0))
									{
										break outer;
									}
								}
							}
							k = 0;
						}
						
						if(k == 0)//if((vstatus[i][j] == 0))//wants go virticle 
						{
							int i = (int)(Math.random()*((size-1)+1));
							int j = (int)(Math.random()*((size-2)+1));
							while((vstatus[i][j] != 0))
							{
								i = (int)(Math.random()*((size-1)+1));
								j = (int)(Math.random()*((size-2)+1));
							}
							System.out.println("vir " + j + " " + i);
							vstatus[i][j] = 1 ;
							lastMove[0] = i;
							lastMove[1] = j;
							lastMove[2] = 1;
							repaint();
						}
						else//if((hstatus[i][j] == 0))
						{
							int i = (int)(Math.random()*((size-2)+1));
							int j = (int)(Math.random()*((size-1)+1));
							while((hstatus[i][j] != 0))
							{
								i = (int)(Math.random()*((size-2)+1));
								j = (int)(Math.random()*((size-1)+1));
							}
							System.out.println("hor " + j + " " + i);
							hstatus[i][j] = 1 ;
							lastMove[0] = i;
							lastMove[1] = j;
							lastMove[2] = 2;
		
							repaint();
						}
	
					}
					else 
					{
						if(r[2] == 1)
						{
							System.out.println("vir " + r[0] + " " + r[1]);
							vstatus[r[0]][r[1]] = 1 ;
							lastMove[0] = r[0];
							lastMove[1] = r[1];
							lastMove[2] = 1;
							repaint();
						}
						if(r[2] == 2)
						{
							System.out.println("hor " + r[0] + " " + r[1]);
							hstatus[r[0]][r[1]] = 1 ;
							lastMove[0] = r[0];
							lastMove[1] = r[1];
							lastMove[2] = 1;
							repaint();
						}
						
					}
				}
			}
			else
			{
				Point ptMouse = evt.getPoint() ;
				for ( int i = 0; i < size; i++ )
				{
					for ( int j = 0; j < size-1; j++ )
					{
						if ( vr[i][j].contains( ptMouse ) && (vstatus[i][j] == 0))
						{
							vstatus[i][j] = 1 ;
							lastMove[0] = i;
							lastMove[1] = j;
							lastMove[2] = 1;
	
							repaint();
							//turn++;
						}
					}
				}
				for ( int i = 0; i < size-1; i++ )
				{
					for ( int j = 0; j < size; j++ )
					{
						if(hr[i][j].contains( ptMouse ) && (hstatus[i][j] == 0) ) {
							hstatus[i][j] = 1 ;
							lastMove[0] = i;
							lastMove[1] = j;
							lastMove[2] = 2;
	
							repaint();
							//turn++;
						}
					}
				}
			}
		}
	}
	
	public int[] whatCanClose()
	{
		int[] r = new int[3]; // r[3] == v/h     v = 1     h = 2    none = 0
		for(int x = 0; x < (size-1); x++)
		{
			for(int y = 0; y < (size-1); y++)
			{
				int count = 0;
				if(hstatus[x][y] != 0)//above
					count ++;
				else
				{
					r[0] = x;
					r[1] = y;
					r[2] = 2;
				}
				if(hstatus[x][y+1] != 0)//below
					count++;
				else
				{
					r[0] = x;
					r[1] = y+1;
					r[2] = 2;
				}
				if(vstatus[x][y] != 0)//left
					count++;
				else
				{
					r[0] = x;
					r[1] = y;
					r[2] = 1;
				}
				if(vstatus[x+1][y] != 0)//right
					count++;
				else
				{
					r[0] = x+1;
					r[1] = y;
					r[2] = 1;
				}
				
				if(count == 3)
				{
					return r;
				}
				else
				{
					r[0] = -1;
					r[1] = -1;
					r[2] = 0;
				}
			}
		}
		return r;
	}
}
