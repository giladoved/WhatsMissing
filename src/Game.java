import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game extends JFrame{

	Toolkit toolkit;

	JLabel[] images = new JLabel[30];

	ImageIcon[] category1 = new ImageIcon[30];
	ImageIcon[] category2 = new ImageIcon[30];
	ImageIcon[] category3 = new ImageIcon[30];

	JButton startButton = new JButton("Start");
	JTextField inputTextField = new JTextField();
	JButton enterButton = new JButton("Enter");

	JPanel gamePanel = new JPanel();
	JPanel endPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	JPanel allPanel = new JPanel();

	JLabel whatsmissingLabel = new JLabel("What's missing?");
	JLabel scoreLabel = new JLabel("Score: 0  ");
	JLabel roundLabel = new JLabel("Round: 1");
	JButton backButton = new JButton("Quit");

	String name;
	int cardNumber;
	int category;
	String guess;
	String answer;
	int score = 0;
	int trys;
	int currenttrys = 0;

	Random r = new Random();

	int missingNumber;
	double bestScore;

	JButton changeSettings = new JButton("Change Settings");
	JButton playAgain = new JButton("Play Again");
	JLabel latestScoreLabel = new JLabel("Latest score: ");
	JLabel numberofRoundsLabel = new JLabel("Number of rounds played: ");
	JLabel bestScoreLabel = new JLabel("Best score percentage: ");
	JLabel categoryLabel = new JLabel("Category: ");
	JLabel percentRight = new JLabel("Latest score percent correct: ");
	JLabel percentWrong = new JLabel("Latest score percent wrong: ");

	JLabel latestScoreLabel2 = new JLabel("10 / 10");
	JLabel numberofRoundsLabel2 = new JLabel("70");
	JLabel bestScoreLabel2 = new JLabel("1005%");
	JLabel categoryLabel2 = new JLabel("animalsanimals");
	JLabel percentRight2 = new JLabel("1308%");
	JLabel percentWrong2 = new JLabel("2135%");
	
	boolean RandomPictures = false;

	ArrayList arrayList = new ArrayList();
	
	Object getI;
	String getIS;
	int getInt;
	
	public Game()
	{
		setTitle("Game");

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();

		gamePanel.setLayout(new FlowLayout());
		endPanel.setLayout(new GridBagLayout());
		scorePanel.setLayout(new FlowLayout());
		allPanel.setLayout(null);

		allPanel.setBounds(0,0,size.width, size.height);
		scorePanel.setBounds(0, (size.height * 4) / 5, size.width, (size.height * 4) / 5);
		gamePanel.setBounds(7, 7, size.width - 21, (size.height * 4) / 5 - (size.height / 40));
		endPanel.setBounds(7, 7, size.width - 21, (size.height * 4) / 5 - (size.height / 40));

		gamePanel.setBackground(Color.black);
		scorePanel.setBackground(Color.red);
		endPanel.setBackground(Color.yellow);
		allPanel.setBackground(Color.green);

		endPanel.setVisible(false);

		name = Settings.name;
		cardNumber = Settings.cardNumber;
		category = Settings.category;
		trys = Settings.trys;
		
		images = new JLabel[cardNumber];

		arrayList = new ArrayList();

		for (int i = 0; i < 30; i++)
		{
			arrayList.add(i);
		}
		
		Collections.shuffle(arrayList);
		
		for (int i = 29; i > cardNumber - 1; i--)
		{
			arrayList.remove(i);
		}
		
		for (int i = 0; i < cardNumber; i++)
		{
			getI = arrayList.get(i);
			getIS = String.valueOf(getI);
			getInt = Integer.valueOf(getIS);
			System.out.println("Value of array: " + getInt);
		}

		System.out.print(cardNumber);
		System.out.print(arrayList);
		
		GridBagConstraints gridC = new GridBagConstraints();

		gridC.gridx = 1;
		gridC.gridy = 0;
		endPanel.add(numberofRoundsLabel, gridC);

		gridC.gridx = 1;
		gridC.gridy = 1;
		endPanel.add(categoryLabel, gridC);

		gridC.gridx = 1;
		gridC.gridy = 2;
		endPanel.add(latestScoreLabel, gridC);

		gridC.gridx = 1;
		gridC.gridy = 3;
		endPanel.add(bestScoreLabel, gridC);

		gridC.gridx = 1;
		gridC.gridy = 4;
		endPanel.add(percentRight, gridC);

		gridC.gridx = 1;
		gridC.gridy = 5;
		endPanel.add(percentWrong, gridC);

		gridC.gridx = 2;
		gridC.gridy = 0;
		endPanel.add(numberofRoundsLabel2, gridC);

		gridC.gridx = 2;
		gridC.gridy = 1;
		endPanel.add(categoryLabel2, gridC);

		gridC.gridx = 2;
		gridC.gridy = 2;
		endPanel.add(latestScoreLabel2, gridC);

		gridC.gridx = 2;
		gridC.gridy = 3;
		endPanel.add(bestScoreLabel2, gridC);

		gridC.gridx = 2;
		gridC.gridy = 4;
		endPanel.add(percentRight2, gridC);

		gridC.gridx = 2;
		gridC.gridy = 5;
		endPanel.add(percentWrong2, gridC);

		gridC.gridx = 0;
		gridC.gridy = 6;
		endPanel.add(changeSettings, gridC);

		gridC.gridx = 3;
		gridC.gridy = 6;
		endPanel.add(playAgain, gridC);

		SetCategory1Images();
		SetCategory2Images();
		SetCategory3Images();

		Collections.shuffle(arrayList);
		
		PlayGame();

		scorePanel.add(startButton);

		whatsmissingLabel.setVisible(false);
		scorePanel.add(whatsmissingLabel);

		inputTextField.setColumns(15);
		inputTextField.setEditable(false);
		inputTextField.setEnabled(false);
		scorePanel.add(inputTextField);

		enterButton.setEnabled(false);
		scorePanel.add(enterButton);

		scorePanel.add(scoreLabel);
		scorePanel.add(roundLabel);
		
		scorePanel.add(backButton);

		allPanel.add(gamePanel);
		allPanel.add(scorePanel);
		allPanel.add(endPanel);

		add(allPanel);
	
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				startButtonActionPerfromed(e);
			}
		});

		enterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				enterButtonActionPerfromed(e);
			}
		});

		inputTextField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				enterButtonActionPerfromed(e);
			}
		});

		playAgain.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				playAgainButtonActionPerfromed(e);
			}
		});

		changeSettings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeSettingsButtonActionPerfromed(e);
			}
		});
		
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backButtonActionPerfromed(e);
			}
		});

		setSize(size.width, size.height);
	}

	protected void backButtonActionPerfromed(ActionEvent e) 
	{
		int n = JOptionPane.showConfirmDialog(allPanel, "Are you sure you want to quit?", "Are you sure?", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION)
		{
			Menu.main(null);
			setVisible(false);
		}
	}

	protected void changeSettingsButtonActionPerfromed(ActionEvent e) 
	{
		Settings.main(null);
		setVisible(false);
	}

	protected void playAgainButtonActionPerfromed(ActionEvent e) 
	{
		score = 0;
		currenttrys = 0;
		scoreLabel.setText("Score: " + String.valueOf(score));
		whatsmissingLabel.setVisible(false);
		inputTextField.setEditable(false);
		inputTextField.setEnabled(false);
		enterButton.setEnabled(false);
		gamePanel.setVisible(true);
		endPanel.setVisible(false);
		scoreLabel.setVisible(true);
		roundLabel.setVisible(true);
		startButton.requestFocus();
		PlayGame();
	}


	public void ShowEndGame()
	{
		System.out.print("game over!!!!");
		String categoryS = "";
		roundLabel.setVisible(false);
		scoreLabel.setVisible(false);
		startButton.setEnabled(false);
		enterButton.setEnabled(false);
		inputTextField.setEditable(false);
		inputTextField.setEnabled(false);
		RandomPictures = false;
		currenttrys = 0;
		numberofRoundsLabel2.setText(String.valueOf(trys));
		switch (category)
		{
		case 0:
			categoryS = Settings.categories[0];
			break;
		case 1:
			categoryS = Settings.categories[1];
			break;
		case 2:
			categoryS = Settings.categories[2];
			break;
		case 3:
			categoryS = Settings.categories[3];
			break;
		case 4:
			categoryS = Settings.categories[4];
			break;
		}
		categoryLabel2.setText(categoryS);
		latestScoreLabel2.setText(String.valueOf(score) + " / " + String.valueOf(trys));
		double rightInt;
		String right;
		if (score == 0)
		{
			rightInt = 0;
		}
		else
		{
			rightInt = (double)score / trys;
			rightInt *= 100;
		}
		System.out.println("RightInt: " + String.valueOf(rightInt));
		right = String.valueOf(rightInt);
		percentRight2.setText(new DecimalFormat("0.00").format(rightInt) + "%");

		double wrongInt;
		String wrong;
		if (score == 0)
		{
			wrongInt = 0;
		}
		else
		{
			wrongInt = (double)(trys - score) / trys;
			wrongInt *= 100;
		}
		wrong = String.valueOf(wrongInt);
		percentWrong2.setText(new DecimalFormat("0.00").format(wrongInt) + "%");

		if (bestScore == 0.0)
		{
			bestScore = rightInt;
		}
		if (rightInt > bestScore)
		{
			bestScore = rightInt;
		}
		bestScoreLabel2.setText(new DecimalFormat("0.00").format(bestScore) + "%");

		endPanel.setVisible(true);
		gamePanel.setVisible(false);
	}

	public void PlayGame()
	{
		whatsmissingLabel.setVisible(false);
		inputTextField.setEditable(false);
		inputTextField.setText("");
		enterButton.setEnabled(false);
		startButton.setEnabled(true);
		inputTextField.setEnabled(false);
		gamePanel.removeAll();

		switch (category)
		{
		case 0:		
			for (int i = 0; i < cardNumber; i++)
			{
				if (!RandomPictures)
				{
					getI = arrayList.get(i);
					getIS = String.valueOf(getI);
					getInt = Integer.valueOf(getIS);
				}
				images[i] = new JLabel(category1[getInt]);
				images[i].setToolTipText(category1[getInt].getDescription());
			}
			DisplayImages();
			break;
		case 1:
			for (int i = 0; i < cardNumber; i++)
			{
				if (!RandomPictures)
				{
					getI = arrayList.get(i);
					getIS = String.valueOf(getI);
					getInt = Integer.valueOf(getIS);
				}
				images[i] = new JLabel(category2[getInt]);
				images[i].setToolTipText(category2[getInt].getDescription());
			}
			DisplayImages();
			break;
		case 2:
			for (int i = 0; i < cardNumber; i++)
			{
				if (!RandomPictures)
				{
					getI = arrayList.get(i);
					getIS = String.valueOf(getI);
					getInt = Integer.valueOf(getIS);
				}
				images[i] = new JLabel(category3[getInt]);
				images[i].setToolTipText(category3[getInt].getDescription());
			}
			DisplayImages();
			break;
		}

		for (int i = 0; i < images.length; i++)
		{
			gamePanel.add(images[i]);
		}

		currenttrys++;
		roundLabel.setText("Round: " + currenttrys);
	}

	public void enterButtonActionPerfromed(ActionEvent e) 
	{
		if (CheckTextField(inputTextField))
		{
			guess = inputTextField.getText().trim().toLowerCase();
			if (guess.equals(images[missingNumber].getToolTipText().toLowerCase()))
			{
				JOptionPane.showMessageDialog(allPanel, "Correct!");
				if (currenttrys - 1 < trys)
				{
					score++;
					scoreLabel.setText("Score: " + String.valueOf(score));
				}
				else if (currenttrys - 1 == trys)
				{
					ShowEndGame();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(allPanel, "You got that wrong.");
			}
			if (currenttrys - 1 < trys)
			{
				PlayGame();
			}
			else
			{
				startButton.setEnabled(false);
				inputTextField.setEditable(false);
				enterButton.setEnabled(false);
				inputTextField.setText("");
				for (int i = 0; i < cardNumber; i++)
				{
					images[i].setVisible(false);
				}
			}
			if (currenttrys - 1 == trys)
			{
				ShowEndGame();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(allPanel, "Your answer was invalid. Try again.");
		}
	}


public void startButtonActionPerfromed(ActionEvent e) 
{
	startButton.setEnabled(false);
	missingNumber = r.nextInt(cardNumber);
	System.out.println(arrayList.indexOf(missingNumber));
	System.out.println("Missing number " + missingNumber);
	images[missingNumber].setVisible(false);
	Countdown.main(null);
	enterButton.setEnabled(true);
	inputTextField.setEditable(true);
	inputTextField.setEnabled(true);
	inputTextField.requestFocus();
	whatsmissingLabel.setVisible(true);
}

public boolean CheckTextField(JTextField tf)
{
	String s = tf.getText().trim();
	boolean valid = true;
	boolean hasSpace = false;
	int l = s.length();
	for (int i = 0; i < s.length(); i++)
	{
		char c = s.charAt(i);
		if (c >= 65 && c <= 122)
		{
			l -= 1;
			if (l == 0)
			{
				valid = true;
			}		
		}
		else if (c == 32 && hasSpace == false)
		{
			valid = true;
			hasSpace = true;
		}
		else
		{
			valid = false;
		}
	}
	if (!valid)
	{
		inputTextField.setText("");
		inputTextField.requestFocus();
	}
	else
	{
		inputTextField.setText(s);
	}
	return (valid);
}

private void DisplayImages() 
{
	for (int i = 0; i < images.length; i++)
	{
		if (i < cardNumber)
		{
			images[i].setVisible(true);
		}
		else
		{
			images[i].setVisible(false);
		}
	}
}

private void SetCategory3Images() 
{
	category3[0] = new ImageIcon("accordian.png", "Accordian");
	category3[1] = new ImageIcon("bagpipes.jpg", "Bagpipes");
	category3[2] = new ImageIcon("banjo.jpg", "Banjo");
	category3[3] = new ImageIcon("bass.jpg", "Bass");
	category3[4] = new ImageIcon("bongos.jpg", "Bongos");
	category3[5] = new ImageIcon("cello.jpg", "Cello");
	category3[6] = new ImageIcon("clarinet.jpg", "Clarinet");
	category3[7] = new ImageIcon("drums.jpg", "Drums");
	category3[8] = new ImageIcon("flute.jpg", "Flute");
	category3[9] = new ImageIcon("french horn.png", "French Horn");
	category3[10] = new ImageIcon("guitar.jpg", "Guitar");
	category3[11] = new ImageIcon("harmonica.gif", "Harmonica");
	category3[12] = new ImageIcon("harp.jpg", "Harp");
	category3[13] = new ImageIcon("kazoo.jpg", "Kazoo");
	category3[14] = new ImageIcon("mandolin.jpg", "Mandolin");
	category3[15] = new ImageIcon("maracas.jpg", "Maracas");
	category3[16] = new ImageIcon("microphone.jpg", "Microphone");
	category3[17] = new ImageIcon("organ.jpg", "Organ");
	category3[18] = new ImageIcon("piano.jpg", "Piano");
	category3[19] = new ImageIcon("recorder.jpg", "Recorder");
	category3[20] = new ImageIcon("sax.jpg", "Saxophone");
	category3[21] = new ImageIcon("sitar.jpg", "Sitar");
	category3[22] = new ImageIcon("synthesizer.jpg", "Synthesizer");
	category3[23] = new ImageIcon("tambourine.jpg", "Tambourine");
	category3[24] = new ImageIcon("triangle.jpg", "Triangle");
	category3[25] = new ImageIcon("trombone.jpg", "Trombone");
	category3[26] = new ImageIcon("trumpet.jpg", "Trumpet");
	category3[27] = new ImageIcon("tuba.jpg", "Tuba");
	category3[28] = new ImageIcon("ukulele.jpg", "Ukulele");
	category3[29] = new ImageIcon("violin.jpg", "Violin");
}

private void SetCategory2Images() 
{
	category2[0] = new ImageIcon("acaiberries.jpg", "Acai Berries");
	category2[1] = new ImageIcon("apple.jpg", "Apple");
	category2[2] = new ImageIcon("avocado.jpg", "Avocado");
	category2[3] = new ImageIcon("bananas.jpg", "Bananas");
	category2[4] = new ImageIcon("blueberries.jpg", "Blueberries");
	category2[5] = new ImageIcon("carrots.jpg", "Carrots");
	category2[6] = new ImageIcon("cherries.jpg", "Cherries");
	category2[7] = new ImageIcon("cucumber.jpg", "Cucumber");
	category2[8] = new ImageIcon("eggplant.jpg", "Eggplant");
	category2[9] = new ImageIcon("grapes.png", "Grapes");
	category2[10] = new ImageIcon("green beans.jpg", "Green Beans");
	category2[11] = new ImageIcon("lemon.jpg", "Lemon");
	category2[12] = new ImageIcon("lettuce.jpg", "Lettuce");
	category2[13] = new ImageIcon("lima beans.jpg", "Lima Beans");
	category2[14] = new ImageIcon("lime.jpg", "Lime");
	category2[15] = new ImageIcon("mangos.jpg", "Mangos");
	category2[16] = new ImageIcon("olive.jpg", "Olive");
	category2[17] = new ImageIcon("onion.jpg", "Onion");
	category2[18] = new ImageIcon("orange.jpg", "Orange");
	category2[19] = new ImageIcon("peach.jpg", "Peach");
	category2[20] = new ImageIcon("peas.jpg", "Peas");
	category2[21] = new ImageIcon("pepper.jpg", "Pepper");
	category2[22] = new ImageIcon("pickle.jpg", "Pickle");
	category2[23] = new ImageIcon("pineapple.jpg", "Pineapple");
	category2[24] = new ImageIcon("plum.jpg", "Plum");
	category2[25] = new ImageIcon("potato.jpg", "Potato");
	category2[26] = new ImageIcon("raisins.jpg", "Raisins");
	category2[27] = new ImageIcon("squash.jpg", "Squash");
	category2[28] = new ImageIcon("tomato.jpg", "Tomato");
	category2[29] = new ImageIcon("watermelon.jpg", "Watermelon");
}

private void SetCategory1Images() 
{
	category1[0] = new ImageIcon("bear.jpg", "Bear");
	category1[1] = new ImageIcon("beaver.jpg", "Beaver");
	category1[2] = new ImageIcon("bird.jpg", "Bird");
	category1[3] = new ImageIcon("butterfly.jpg", "Butterfly");
	category1[4] = new ImageIcon("camal.jpg", "Camel");
	category1[5] = new ImageIcon("cat.png", "Cat");
	category1[6] = new ImageIcon("deer.jpg", "Deer");
	category1[7] = new ImageIcon("dog.jpg", "Dog");
	category1[8] = new ImageIcon("eagle.jpg", "Eagle");
	category1[9] = new ImageIcon("elephant.jpg", "Elephant");
	category1[10] = new ImageIcon("fish.jpg", "Fish");
	category1[11] = new ImageIcon("fly.gif", "Fly");
	category1[12] = new ImageIcon("fox.jpg", "Fox");
	category1[13] = new ImageIcon("giraffe.jpg", "Giraffe");
	category1[14] = new ImageIcon("jellyfish.jpg", "Jellyfish");
	category1[15] = new ImageIcon("ladybug.jpg", "Ladybug");
	category1[16] = new ImageIcon("lion.jpg", "Lion");
	category1[17] = new ImageIcon("monkey.jpg", "Monkey");
	category1[18] = new ImageIcon("owl.jpg", "Owl");
	category1[19] = new ImageIcon("parrot.jpg", "Parrot");
	category1[20] = new ImageIcon("penguin.jpg", "Penguin");
	category1[21] = new ImageIcon("pig.jpg", "Pig");
	category1[22] = new ImageIcon("seal.jpg", "Seal");
	category1[23] = new ImageIcon("sheep.jpg", "Sheep");
	category1[24] = new ImageIcon("snake.jpg", "Snake");
	category1[25] = new ImageIcon("spider.jpg", "Spider");
	category1[26] = new ImageIcon("turkey.jpg", "Turkey");
	category1[27] = new ImageIcon("turtle.jpg", "Turtle");
	category1[28] = new ImageIcon("whale.jpg", "Whale");
	category1[29] = new ImageIcon("zebra.jpg", "Zebra");
}

public static void main(String[] args) 
{
	Game game = new Game();
	game.setVisible(true);
}

}
