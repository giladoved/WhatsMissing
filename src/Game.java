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

	String playerName;
	int cardNumber;
	int category;
	String guess;
	String answer;
	int score = 0;
	int rounds;
	int currentRound = 0;
	int missingItemIndex;
	double bestScore;

	JButton changeSettingsBtn = new JButton("Change Settings");
	JButton playAgainBtn = new JButton("Play Again");
	JLabel latestScoreTextLbl = new JLabel("Latest score: ");
	JLabel numberofRoundsTextLbl = new JLabel("Number of rounds played: ");
	JLabel bestScoreTextLbl = new JLabel("Best score percentage: ");
	JLabel categoryTextLbl = new JLabel("Category: ");
	JLabel percentRightTextLbl = new JLabel("Latest score percent correct: ");
	JLabel percentWrongTextLbl = new JLabel("Latest score percent wrong: ");

	JLabel latestScoreLbl= new JLabel("10 / 10");
	JLabel numberofRoundsLbl = new JLabel("70");
	JLabel bestScoreLbl = new JLabel("1005%");
	JLabel categoryLbl = new JLabel("animalsanimals");
	JLabel percentRightLbl = new JLabel("1308%");
	JLabel percentWrongLbl = new JLabel("2135%");
	
	boolean randomPictures = false;

	ArrayList arrayList = new ArrayList();
	
	Object items;
	String itemAsString;
	int itemIndex;
	
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

		playerName = Settings.name;
		cardNumber = Settings.cardNumber;
		category = Settings.category;
		rounds = Settings.numberOfRounds;
		
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
			items = arrayList.get(i);
			itemAsString = String.valueOf(items);
			itemIndex = Integer.valueOf(itemAsString);
			System.out.println("Value of array: " + itemIndex);
		}

		System.out.print(cardNumber);
		System.out.print(arrayList);
		
		GridBagConstraints gridC = new GridBagConstraints();

		gridC.gridx = 1;
		gridC.gridy = 0;
		endPanel.add(numberofRoundsTextLbl, gridC);

		gridC.gridx = 1;
		gridC.gridy = 1;
		endPanel.add(categoryTextLbl, gridC);

		gridC.gridx = 1;
		gridC.gridy = 2;
		endPanel.add(latestScoreTextLbl, gridC);

		gridC.gridx = 1;
		gridC.gridy = 3;
		endPanel.add(bestScoreTextLbl, gridC);

		gridC.gridx = 1;
		gridC.gridy = 4;
		endPanel.add(percentRightTextLbl, gridC);

		gridC.gridx = 1;
		gridC.gridy = 5;
		endPanel.add(percentWrongTextLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 0;
		endPanel.add(numberofRoundsLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 1;
		endPanel.add(categoryLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 2;
		endPanel.add(latestScoreLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 3;
		endPanel.add(bestScoreLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 4;
		endPanel.add(percentRightLbl, gridC);

		gridC.gridx = 2;
		gridC.gridy = 5;
		endPanel.add(percentWrongLbl, gridC);

		gridC.gridx = 0;
		gridC.gridy = 6;
		endPanel.add(changeSettingsBtn, gridC);

		gridC.gridx = 3;
		gridC.gridy = 6;
		endPanel.add(playAgainBtn, gridC);

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

		playAgainBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				playAgainBtnButtonActionPerfromed(e);
			}
		});

		changeSettingsBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeSettingsBtnButtonActionPerfromed(e);
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

	protected void changeSettingsBtnButtonActionPerfromed(ActionEvent e) 
	{
		Settings.main(null);
		setVisible(false);
	}

	protected void playAgainBtnButtonActionPerfromed(ActionEvent e) 
	{
		score = 0;
		currentRound = 0;
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
		randomPictures = false;
		currentRound = 0;
		numberofRoundsLbl.setText(String.valueOf(rounds));
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
		categoryLbl.setText(categoryS);
		latestScoreLbl.setText(String.valueOf(score) + " / " + String.valueOf(rounds));
		double rightPercent;
		String rightStr;
		if (score == 0)
		{
			rightPercent = 0;
		}
		else
		{
			rightPercent = (double)score / rounds;
			rightPercent *= 100;
		}
		System.out.println("RightInt: " + String.valueOf(rightPercent));
		rightStr = String.valueOf(rightPercent);
		percentRightLbl.setText(new DecimalFormat("0.00").format(rightPercent) + "%");

		double wrongPercent;
		String wrongStr;
		if (score == 0)
		{
			wrongPercent = 0;
		}
		else
		{
			wrongPercent = (double)(rounds - score) / rounds;
			wrongPercent *= 100;
		}
		wrongStr = String.valueOf(wrongPercent);
		percentWrongLbl.setText(new DecimalFormat("0.00").format(wrongPercent) + "%");

		if (bestScore == 0.0)
		{
			bestScore = rightPercent;
		}
		if (rightPercent > bestScore)
		{
			bestScore = rightPercent;
		}
		bestScoreLbl.setText(new DecimalFormat("0.00").format(bestScore) + "%");

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
				if (!randomPictures)
				{
					items = arrayList.get(i);
					itemAsString = String.valueOf(items);
					itemIndex = Integer.valueOf(itemAsString);
				}
				images[i] = new JLabel(category1[itemIndex]);
				images[i].setToolTipText(category1[itemIndex].getDescription());
			}
			DisplayImages();
			break;
		case 1:
			for (int i = 0; i < cardNumber; i++)
			{
				if (!randomPictures)
				{
					items = arrayList.get(i);
					itemAsString = String.valueOf(items);
					itemIndex = Integer.valueOf(itemAsString);
				}
				images[i] = new JLabel(category2[itemIndex]);
				images[i].setToolTipText(category2[itemIndex].getDescription());
			}
			DisplayImages();
			break;
		case 2:
			for (int i = 0; i < cardNumber; i++)
			{
				if (!randomPictures)
				{
					items = arrayList.get(i);
					itemAsString = String.valueOf(items);
					itemIndex = Integer.valueOf(itemAsString);
				}
				images[i] = new JLabel(category3[itemIndex]);
				images[i].setToolTipText(category3[itemIndex].getDescription());
			}
			DisplayImages();
			break;
		}

		for (int i = 0; i < images.length; i++)
		{
			gamePanel.add(images[i]);
		}

		currentRound++;
		roundLabel.setText("Round: " + currentRound);
	}
	
	public boolean isValidInput(String name)
	{
		if (name.matches("[a-zA-Z ]+")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter valid input.");
			inputTextField.setText("");
			inputTextField.requestFocus();
			return false;
		}
	}

	public void enterButtonActionPerfromed(ActionEvent e) 
	{
		if (isValidInput(inputTextField.getText().trim()))
		{
			guess = inputTextField.getText().trim().toLowerCase();
			if (guess.equals(images[missingItemIndex].getToolTipText().toLowerCase()))
			{
				JOptionPane.showMessageDialog(allPanel, "Correct!");
				if (currentRound - 1 < rounds)
				{
					score++;
					scoreLabel.setText("Score: " + String.valueOf(score));
				}
				else if (currentRound - 1 == rounds)
				{
					ShowEndGame();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(allPanel, "You got that wrong.");
			}
			if (currentRound - 1 < rounds)
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
			if (currentRound - 1 == rounds)
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
	Random randomGenerator = new Random();
	missingItemIndex = randomGenerator.nextInt(cardNumber);
	System.out.println(arrayList.indexOf(missingItemIndex));
	System.out.println("Missing number " + missingItemIndex);
	images[missingItemIndex].setVisible(false);
	Countdown.main(null);
	enterButton.setEnabled(true);
	inputTextField.setEditable(true);
	inputTextField.setEnabled(true);
	inputTextField.requestFocus();
	whatsmissingLabel.setVisible(true);
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
	category3[0] = new ImageIcon("Images/accordian.png", "Accordian");
	category3[1] = new ImageIcon("Images/bagpipes.jpg", "Bagpipes");
	category3[2] = new ImageIcon("Images/banjo.jpg", "Banjo");
	category3[3] = new ImageIcon("Images/bass.jpg", "Bass");
	category3[4] = new ImageIcon("Images/bongos.jpg", "Bongos");
	category3[5] = new ImageIcon("Images/cello.jpg", "Cello");
	category3[6] = new ImageIcon("Images/clarinet.jpg", "Clarinet");
	category3[7] = new ImageIcon("Images/drums.jpg", "Drums");
	category3[8] = new ImageIcon("Images/flute.jpg", "Flute");
	category3[9] = new ImageIcon("Images/french horn.png", "French Horn");
	category3[10] = new ImageIcon("Images/guitar.jpg", "Guitar");
	category3[11] = new ImageIcon("Images/harmonica.gif", "Harmonica");
	category3[12] = new ImageIcon("Images/harp.jpg", "Harp");
	category3[13] = new ImageIcon("Images/kazoo.jpg", "Kazoo");
	category3[14] = new ImageIcon("Images/mandolin.jpg", "Mandolin");
	category3[15] = new ImageIcon("Images/maracas.jpg", "Maracas");
	category3[16] = new ImageIcon("Images/microphone.jpg", "Microphone");
	category3[17] = new ImageIcon("Images/organ.jpg", "Organ");
	category3[18] = new ImageIcon("Images/piano.jpg", "Piano");
	category3[19] = new ImageIcon("Images/recorder.jpg", "Recorder");
	category3[20] = new ImageIcon("Images/sax.jpg", "Saxophone");
	category3[21] = new ImageIcon("Images/sitar.jpg", "Sitar");
	category3[22] = new ImageIcon("Images/synthesizer.jpg", "Synthesizer");
	category3[23] = new ImageIcon("Images/tambourine.jpg", "Tambourine");
	category3[24] = new ImageIcon("Images/triangle.jpg", "Triangle");
	category3[25] = new ImageIcon("Images/trombone.jpg", "Trombone");
	category3[26] = new ImageIcon("Images/trumpet.jpg", "Trumpet");
	category3[27] = new ImageIcon("Images/tuba.jpg", "Tuba");
	category3[28] = new ImageIcon("Images/ukulele.jpg", "Ukulele");
	category3[29] = new ImageIcon("Images/violin.jpg", "Violin");
}

private void SetCategory2Images() 
{
	category2[0] = new ImageIcon("Images/acaiberries.jpg", "Acai Berries");
	category2[1] = new ImageIcon("Images/apple.jpg", "Apple");
	category2[2] = new ImageIcon("Images/avocado.jpg", "Avocado");
	category2[3] = new ImageIcon("Images/bananas.jpg", "Bananas");
	category2[4] = new ImageIcon("Images/blueberries.jpg", "Blueberries");
	category2[5] = new ImageIcon("Images/carrots.jpg", "Carrots");
	category2[6] = new ImageIcon("Images/cherries.jpg", "Cherries");
	category2[7] = new ImageIcon("Images/cucumber.jpg", "Cucumber");
	category2[8] = new ImageIcon("Images/eggplant.jpg", "Eggplant");
	category2[9] = new ImageIcon("Images/grapes.png", "Grapes");
	category2[10] = new ImageIcon("Images/green beans.jpg", "Green Beans");
	category2[11] = new ImageIcon("Images/lemon.jpg", "Lemon");
	category2[12] = new ImageIcon("Images/lettuce.jpg", "Lettuce");
	category2[13] = new ImageIcon("Images/lima beans.jpg", "Lima Beans");
	category2[14] = new ImageIcon("Images/lime.jpg", "Lime");
	category2[15] = new ImageIcon("Images/mangos.jpg", "Mangos");
	category2[16] = new ImageIcon("Images/olive.jpg", "Olive");
	category2[17] = new ImageIcon("Images/onion.jpg", "Onion");
	category2[18] = new ImageIcon("Images/orange.jpg", "Orange");
	category2[19] = new ImageIcon("Images/peach.jpg", "Peach");
	category2[20] = new ImageIcon("Images/peas.jpg", "Peas");
	category2[21] = new ImageIcon("Images/pepper.jpg", "Pepper");
	category2[22] = new ImageIcon("Images/pickle.jpg", "Pickle");
	category2[23] = new ImageIcon("Images/pineapple.jpg", "Pineapple");
	category2[24] = new ImageIcon("Images/plum.jpg", "Plum");
	category2[25] = new ImageIcon("Images/potato.jpg", "Potato");
	category2[26] = new ImageIcon("Images/raisins.jpg", "Raisins");
	category2[27] = new ImageIcon("Images/squash.jpg", "Squash");
	category2[28] = new ImageIcon("Images/tomato.jpg", "Tomato");
	category2[29] = new ImageIcon("Images/watermelon.jpg", "Watermelon");
}

private void SetCategory1Images() 
{
	category1[0] = new ImageIcon("Images/bear.jpg", "Bear");
	category1[1] = new ImageIcon("Images/beaver.jpg", "Beaver");
	category1[2] = new ImageIcon("Images/bird.jpg", "Bird");
	category1[3] = new ImageIcon("Images/butterfly.jpg", "Butterfly");
	category1[4] = new ImageIcon("Images/camal.jpg", "Camel");
	category1[5] = new ImageIcon("Images/cat.png", "Cat");
	category1[6] = new ImageIcon("Images/deer.jpg", "Deer");
	category1[7] = new ImageIcon("Images/dog.jpg", "Dog");
	category1[8] = new ImageIcon("Images/eagle.jpg", "Eagle");
	category1[9] = new ImageIcon("Images/elephant.jpg", "Elephant");
	category1[10] = new ImageIcon("Images/fish.jpg", "Fish");
	category1[11] = new ImageIcon("Images/fly.gif", "Fly");
	category1[12] = new ImageIcon("Images/fox.jpg", "Fox");
	category1[13] = new ImageIcon("Images/giraffe.jpg", "Giraffe");
	category1[14] = new ImageIcon("Images/jellyfish.jpg", "Jellyfish");
	category1[15] = new ImageIcon("Images/ladybug.jpg", "Ladybug");
	category1[16] = new ImageIcon("Images/lion.jpg", "Lion");
	category1[17] = new ImageIcon("Images/monkey.jpg", "Monkey");
	category1[18] = new ImageIcon("Images/owl.jpg", "Owl");
	category1[19] = new ImageIcon("Images/parrot.jpg", "Parrot");
	category1[20] = new ImageIcon("Images/penguin.jpg", "Penguin");
	category1[21] = new ImageIcon("Images/pig.jpg", "Pig");
	category1[22] = new ImageIcon("Images/seal.jpg", "Seal");
	category1[23] = new ImageIcon("Images/sheep.jpg", "Sheep");
	category1[24] = new ImageIcon("Images/snake.jpg", "Snake");
	category1[25] = new ImageIcon("Images/spider.jpg", "Spider");
	category1[26] = new ImageIcon("Images/turkey.jpg", "Turkey");
	category1[27] = new ImageIcon("Images/turtle.jpg", "Turtle");
	category1[28] = new ImageIcon("Images/whale.jpg", "Whale");
	category1[29] = new ImageIcon("Images/zebra.jpg", "Zebra");
}

public static void main(String[] args) 
{
	Game game = new Game();
	game.setVisible(true);
}

}
