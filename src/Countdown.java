import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Countdown extends JFrame{

	JLabel countdown = new JLabel("3");
	
	Timer timer = new Timer(550, new ActionListener() { //delay to wait for the screen to show up
        public void actionPerformed(ActionEvent e) {
            timerTick(e);
        }
     });
	
	int time = 3;
	Toolkit toolkit;
	
	public Countdown()
	{
		setTitle("Game");
		
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
		getContentPane().add(countdown, gridC);
		
		timer.start();
		
		setSize(size.width, size.height);
	}

	
	public void timerTick(ActionEvent e) 
	{
		time--;
		countdown.setText(String.valueOf(time));
		if (time == 0)
		{
			setVisible(false);
			time = 3;
		}
	}

	public static void main(String[] args) 
	{
		Countdown cd = new Countdown();
		cd.setVisible(true);
	}

}
