package forHackAThon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class front
{
	public static int size = 0;
	public static boolean singlePlayer = false;
	static JFrame frame;

	public static void main(String[] args)
	{
		// schedule this for the event dispatch thread (edt)
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				displayJFrame();
			}
		});
	}
	
	

	public int getSize() {
		return size;
	}
	
	public boolean getSinglePlayer()
	{
		return singlePlayer;
	}


	static void displayJFrame()
	{
		frame = new JFrame("Our JButton listener example");

		
		JButton singlePlayerB = new JButton("single player?");					// create our jbuttons
		JButton showDialogButton1 = new JButton("4");
		JButton showDialogButton2 = new JButton("5");
		JButton showDialogButton3 = new JButton("6");
		JButton showDialogButton4 = new JButton("7");
		JButton showDialogButton5 = new JButton("8");

		singlePlayerB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)						// if clicked makes the game single player
			{
				Dots4Win.setSinglePlayer(true);
				singlePlayer = true;
				
			}
		});
		
		
		showDialogButton1.addActionListener(new ActionListener()			// makes the graph of size 4
		{
			public void actionPerformed(ActionEvent e)
			{
				Dots4Win.setSize(4);
				size = 4;
				JFrame frame= new JFrame("Dots and Lines");					//starts the game
				frame.setVisible(true);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);
				
			}
		});


		showDialogButton2.addActionListener(new ActionListener()			// makes the graph of size 5
		{
			public void actionPerformed(ActionEvent e)
			{
				Dots4Win.setSize(5);
				size = 5;
				JFrame frame= new JFrame("Dots and Lines");					//starts the game
				frame.setVisible(true);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);
			}
		});

		showDialogButton3.addActionListener(new ActionListener()			// makes the graph of size 6
		{
			public void actionPerformed(ActionEvent e)
			{
				Dots4Win.setSize(6);
				size = 6;
				JFrame frame= new JFrame("Dots and Lines");					//starts the game
				frame.setVisible(true);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);
			}
		});

		showDialogButton4.addActionListener(new ActionListener()			//makes the graph of size 7
		{
			public void actionPerformed(ActionEvent e)
			{
				Dots4Win.setSize(7);
				size = 7;
				JFrame frame= new JFrame("Dots and Lines");					//starts the game
				frame.setVisible(true);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);
			}
		});

		showDialogButton5.addActionListener(new ActionListener()			// makes the graph of size 8
		{
			public void actionPerformed(ActionEvent e)
			{
				Dots4Win.setSize(8);
				size = 8;
				JFrame frame= new JFrame("Dots and Lines");						//starts the game
				frame.setVisible(true);
				frame.getContentPane().add(new Dots4Win());
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setBackground(Color.white);
			}
		});

		
		frame.getContentPane().setLayout(new FlowLayout());						// put the buttons on the frame
		frame.add(singlePlayerB);
		frame.add(showDialogButton1);
		frame.add(showDialogButton2);
		frame.add(showDialogButton3);
		frame.add(showDialogButton4);
		frame.add(showDialogButton5);

		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);			// set up the jframe, then display it
		frame.setPreferredSize(new Dimension(600, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}