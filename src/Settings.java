import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.*;

public class Settings extends JFrame implements ChangeListener, ActionListener{

	Toolkit toolkit;

	public static String[] categories = {"Animals", "Fruits and Vegetables", "Instruments", "Countries", "States"};

	JLabel nameLabel = new JLabel("Name:");
	JLabel cardNumberLabel = new JLabel("How many items do you want in play?");
	JLabel categoriesLabel = new JLabel("Choose a category.");
	JLabel cardsLabel = new JLabel("10");
	JLabel numberOfRoundsLabel = new JLabel("How many rounds do you want to play?");

	JTextField nameTextField = new JTextField();
	JSlider cardNumberSlider = new JSlider(JSlider.HORIZONTAL, 3, 30, 10);
	JComboBox categoriesBox = new JComboBox(categories);
	JSpinner numberOfRoundsSpinner = new JSpinner();

	JButton playButton = new JButton("Play");
	JButton backButton = new JButton("Back");

	public static int cardNumber = 10;
	public static String name;
	public static int category = 0;
	public static int numberOfRounds = 10;

	public Settings()
	{
		setTitle("Settings");

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});


		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();

		nameTextField.setColumns(15);
		cardNumberSlider.setMajorTickSpacing(3);
		cardNumberSlider.setMinorTickSpacing(1);
		cardNumberSlider.setPaintTicks(true);
		cardNumberSlider.setPaintLabels(true);
		cardNumberSlider.addChangeListener(this);
		categoriesBox.setSelectedIndex(0);

		Dimension d = new Dimension( 40, 25 );
		numberOfRoundsSpinner.setMinimumSize( d );
		numberOfRoundsSpinner.setPreferredSize( d );
		numberOfRoundsSpinner.setMaximumSize( d );

		final SpinnerNumberModel trysModel = new SpinnerNumberModel(5, 1, 15, 1);
		numberOfRoundsSpinner.setModel(trysModel);

		numberOfRoundsSpinner.addChangeListener( new ChangeListener()
		{
			public void stateChanged( ChangeEvent e )
			{
				numberOfRounds = trysModel.getNumber().intValue();
			}
		} 
		);


		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gridC = new GridBagConstraints();

		categoriesBox.addActionListener(this);

		gridC.gridx = 1;
		gridC.gridy = 0;
		getContentPane().add(nameLabel, gridC);

		gridC.gridx = 2;
		gridC.gridy = 0;
		getContentPane().add(nameTextField, gridC);

		gridC.gridx = 1;
		gridC.gridy = 1;
		getContentPane().add(cardNumberLabel, gridC);

		gridC.gridx = 2;
		gridC.gridy = 1;
		getContentPane().add(cardNumberSlider, gridC);

		gridC.gridx = 3;
		gridC.gridy = 1;
		getContentPane().add(cardsLabel, gridC);

		gridC.gridx = 1;
		gridC.gridy = 2;
		getContentPane().add(categoriesLabel, gridC);

		gridC.gridx = 2;
		gridC.gridy = 2;
		getContentPane().add(categoriesBox, gridC);

		gridC.gridx = 1;
		gridC.gridy = 3;
		getContentPane().add(numberOfRoundsLabel, gridC);

		gridC.gridx = 2;
		gridC.gridy = 3;
		getContentPane().add(numberOfRoundsSpinner, gridC);

		gridC.gridx = 0;
		gridC.gridy = 5;
		getContentPane().add(backButton, gridC);

		gridC.gridx = 3;
		gridC.gridy = 5;
		getContentPane().add(playButton, gridC);

		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backButtonActionPerformed(e);
			}
		});

		playButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				playButtonActionPerformed(e);
			}
		});

		setSize(size.width, size.height);
	}

	public void playButtonActionPerformed(ActionEvent e) 
	{
		if (isValidInput(nameTextField.getText().trim()))
		{
			name = nameTextField.getText().trim();
			Game.main(null);
			setVisible(false);
		}
		if (numberOfRounds == 0)
		{
			numberOfRounds = 10;
		}
	}

	public void backButtonActionPerformed(ActionEvent e) 
	{
		Menu.main(null);
		setVisible(false);
	}

	public static void main(String[] args) 
	{
		Settings settings = new Settings();
		settings.setVisible(true);
	}

	public boolean isValidInput(String name)
	{
		if (name.matches("[a-zA-Z ]+")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid name.");
			nameTextField.setText("");
			nameTextField.requestFocus();
			return false;
		}
	}

	public void stateChanged(ChangeEvent e) 
	{
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting())
		{
			cardNumber = (int)source.getValue();
			cardsLabel.setText(String.valueOf(cardNumber));
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		JComboBox cb = (JComboBox)e.getSource();
		category = cb.getSelectedIndex();
	}

}
