import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame{

	JButton newGameButton = new JButton("New Game");
	JButton settingsButton = new JButton("Change Settings");
	JButton exitButton = new JButton("Exit");
	
	Toolkit toolkit;
	
	public Menu()
	{
		setTitle("Menu");
		
		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		
		getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints gridC = new GridBagConstraints();
		
		gridC.gridx = 0;
		gridC.gridx = 0;
		getContentPane().add(newGameButton, gridC);
		
		gridC.gridx = 0;
		gridC.gridy = 1;
		getContentPane().add(settingsButton, gridC);
		
		gridC.gridx = 0;
		gridC.gridy = 2;
		getContentPane().add(exitButton, gridC);
		
		newGameButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				newGameButtonActionPerformed(e);
			}
		});
		
		settingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingsButtonActionPerformed(e);
			}
		});
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				exitButtonActionPerformed(e);
			}
		});
		
		setSize(size.width, size.height);
	}

	
	public void exitButtonActionPerformed(ActionEvent e) 
	{
		System.exit(0);
	}


	public void settingsButtonActionPerformed(ActionEvent e) 
	{
		Settings.main(null);
		setVisible(false);
	}


	public void newGameButtonActionPerformed(ActionEvent e) 
	{
		Game.main(null);
		setVisible(false);
	}


	public static void main(String[] args) 
	{
		Menu mg = new Menu();
		mg.setVisible(true);
	}

}
