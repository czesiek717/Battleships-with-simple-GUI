package com.grzesiek.statki;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.grzesiek.statki.AI.AI;
import com.grzesiek.statki.fields.EnemysField;
import com.grzesiek.statki.fields.PlayersField;

public abstract class GameWindow extends JFrame
{
	private final int WIDTH = 890;
	private final int HEIGHT = 420;

	private Dimension dimension = new Dimension(WIDTH, HEIGHT);

	protected JButton[] shipSizes;
	protected JButton position;
	protected JButton shipsPosition;
	protected JButton randomize;
	protected JButton clear;
	protected JButton endTurn;

	protected JLabel currentPosition;
	protected JLabel currentShip;
	protected JLabel numberFour;
	protected JLabel numberThree;
	protected JLabel numberTwo;
	protected JLabel numberOne;
	protected JLabel labelOne;
	protected JLabel labelTwo;
	protected JLabel labelThree;
	
	protected ImageIcon horizontalFour;
	protected ImageIcon horizontalThree;
	protected ImageIcon horizontalTwo;
	protected ImageIcon horizontalOne;
	protected ImageIcon verticalFour;
	protected ImageIcon verticalThree;
	protected ImageIcon verticalTwo;
	
	public GameWindow()
	{
		super("Battleships");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		
		shipSizes = new JButton[4];
		position = new JButton();
		shipsPosition = new JButton();
		randomize = new JButton();
		clear = new JButton();
		endTurn = new JButton();
		
		currentPosition = new JLabel();
		currentShip = new JLabel();
		numberFour = new JLabel();
		numberThree = new JLabel();
		numberTwo = new JLabel();
		numberOne = new JLabel();
		labelOne = new JLabel();
		labelTwo = new JLabel();
		labelThree = new JLabel();
		
		horizontalFour = new ImageIcon(GameWindow.class.getResource("/horizontalFour.png"));
		horizontalThree = new ImageIcon(GameWindow.class.getResource("/horizontalThree.png"));
		horizontalTwo = new ImageIcon(GameWindow.class.getResource("/horizontalTwo.png"));
		horizontalOne = new ImageIcon(GameWindow.class.getResource("/horizontalOne.png"));
		verticalFour = new ImageIcon(GameWindow.class.getResource("/verticalFour.png"));
		verticalThree = new ImageIcon(GameWindow.class.getResource("/verticalThree.png"));
		verticalTwo = new ImageIcon(GameWindow.class.getResource("/verticalTwo.png"));

		add(position);
		for(int i = 0; i < shipSizes.length; i++)
		{
			shipSizes[i] = new JButton();
			add(shipSizes[i]);
		}
		
		add(currentPosition);
		add(currentShip);
		add(numberFour);
		add(numberThree);
		add(numberTwo);
		add(numberOne);
		add(shipsPosition);
		add(labelOne);
		add(labelTwo);
		add(labelThree);
		add(randomize);
		add(clear);
		add(endTurn);
		
		position.setBounds(15, 10, 100, 30);
		shipSizes[0].setBounds(15, 65, 100, 30);
		shipSizes[1].setBounds(15, 120, 100, 30);
		shipSizes[2].setBounds(15, 175, 100, 30);
		shipSizes[3].setBounds(15, 230, 100, 30);
		randomize.setBounds(125, 250, 45, 45);
		clear.setBounds(125, 300, 45, 45);
		endTurn.setBounds(125, 350, 45, 45);

		
		currentPosition.setBounds(10, 45, 115, 14);
		currentShip.setBounds(17, 375, 115, 14);
		numberFour.setBounds(35, 100, 130, 14);
		numberThree.setBounds(35, 155, 130, 14);
		numberTwo.setBounds(35, 210, 130, 14);
		numberOne.setBounds(35, 265, 130, 14);
		shipsPosition.setBounds(15, 280, 100, 90);
		labelOne.setBounds(440, 0, 200, 14);
		labelTwo.setBounds(330, 15, 150, 14);
		labelThree.setBounds(690, 15, 150, 14);
		
		shipsPosition.setEnabled(false);
		shipsPosition.setIcon(horizontalFour);
		shipsPosition.setDisabledIcon(horizontalOne);

		position.setActionCommand("Position");
		shipSizes[0].setActionCommand("Ship 4x1");
		shipSizes[1].setActionCommand("Ship 3x1");
		shipSizes[2].setActionCommand("Ship 2x1");
		shipSizes[3].setActionCommand("Ship 1x1");
		randomize.setActionCommand("random");
		clear.setActionCommand("clear");
		endTurn.setActionCommand("end");
		
		currentPosition.setText("Postion: horizontal");
		currentShip.setText("Current ship: 1x1");
		position.setText("Position");
		shipSizes[0].setText("Ship 4x1");
		shipSizes[1].setText("Ship 3x1");
		shipSizes[2].setText("Ship 2x1");
		shipSizes[3].setText("Ship 1x1");
		randomize.setText("R");
		clear.setText("C");
		endTurn.setText("E");
	}
}