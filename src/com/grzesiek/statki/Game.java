package com.grzesiek.statki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.grzesiek.statki.AI.AI;
import com.grzesiek.statki.fields.EnemysField;
import com.grzesiek.statki.fields.PlayersField;

public class Game extends GameWindow implements ActionListener, Runnable
{
	private PlayersField playersField;
	private PlayersField playersField2;
	private EnemysField enemysField;
	private AI ai;

	private Thread run;

	private boolean isRunning;
	private boolean helper;
	private boolean helperTwo;
	private boolean helperThree;
	private boolean game;
	private boolean endP1;
	private boolean endP2;

	private int difficulty;

	public Game(int difficulty)
	{
		this.difficulty = difficulty;
		if(difficulty <= 2)
		{
			playersField = new PlayersField();
			enemysField = new EnemysField();

			add(playersField);
			add(enemysField);

			playersField.setBounds(175, 40, 350, 350);
			enemysField.setBounds(535, 40, 350, 350);

			labelOne.setText("<html><body>Game type: <font color=red>player</font> vs <font color=blue>computer</font></body></html>");
			labelTwo.setText("<html><body><font color=red>Player1</font></body></html>");

			if(difficulty == 0)
			{
				labelThree.setText("<html><body><font color=blue>Computer1</font></body></html>");
			}
			if(difficulty == 1)
			{
				labelThree.setText("<html><body><font color=blue>Computer2</font></body></html>");
			}
			if(difficulty == 2)
			{
				labelThree.setText("<html><body><font color=blue>Computer3</font></body></html>");
			}

			ai = new AI(difficulty);
		}

		if(difficulty == 3)
		{
			playersField = new PlayersField();
			playersField2 = new PlayersField();

			add(playersField);
			add(playersField2);

			playersField.setBounds(175, 40, 350, 350);
			playersField2.setBounds(535, 40, 350, 350);

			labelOne.setText("<html><body>Game type: <font color=red>player</font> vs <font color=blue>player</font></body></html>");
			labelTwo.setText("<html><body><font color=red>Player1</font></body></html>");
			labelThree.setText("<html><body><font color=blue>Player2</font></body></html>");

			endP1 = false;
			endP2 = false;

			playersField2.blockField(true);
		}

		for(int i = 0; i < shipSizes.length; i++)
		{
			shipSizes[i].addActionListener(this);
		}
		position.addActionListener(this);
		randomize.addActionListener(this);
		clear.addActionListener(this);
		endTurn.addActionListener(this);

		isRunning = true;
		helper = true;
		helperTwo = true;
		helperThree = true;
		game = false;

		run = new Thread(this, "Game");
		run.start();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String name = e.getActionCommand();
		if(difficulty < 3)
		{
			updatePlayersLabels(playersField, name);
		}

		if(difficulty == 3)
		{
			if(!endP1)
			{
				updatePlayersLabels(playersField, name);
			}
			else if(!endP2 && endP1)
			{
				updatePlayersLabels(playersField2, name);
			}
		}

		if(name.equals("random"))
		{
			if(difficulty < 3)
			{
				if(!game)
				{
					if(helperTwo)
					{
						helperTwo = false;
						playersField.clear();
						playersField.setPlacesChecked(0);
						enemysField.setPlacesChecked(0);
						playersField.placeShipsRandomly();
						playersField.placeProperIcons();
						helperTwo = true;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "The game has already begun!");
				}
			}
			else if(difficulty == 3)
			{
				if(!game)
				{
					if(helperTwo)
					{
						if(!endP1)
						{
							helperTwo = false;
							playersField.clear();
							playersField.setPlacesChecked(0);
							playersField.placeShipsRandomly();
							playersField.placeProperIcons();
							helperTwo = true;
						}
						else if(!endP2 && endP1)
						{
							helperTwo = false;
							playersField2.clear();
							playersField2.setPlacesChecked(0);
							playersField2.placeShipsRandomly();
							playersField2.placeProperIcons();
							helperTwo = true;
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "The game has already begun!");
				}
			}
		}
		else if(name.equals("clear"))
		{
			if(difficulty < 3)
			{
				if(!game)
				{
					if(helperThree)
					{
						helperThree = false;
						playersField.fillArray(1);
						playersField.clear();
						playersField.setPlacesChecked(0);
						enemysField.setPlacesChecked(0);
						helperThree = true;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "The game has already begun!");
				}
			}
			else if(difficulty == 3)
			{
				if(!game)
				{
					if(helperThree)
					{
						if(!endP1)
						{
							helperThree = false;
							playersField.fillArray(1);
							playersField.clear();
							playersField.setPlacesChecked(0);
							helperThree = true;
						}
						else if(!endP2 && endP1)
						{
							helperThree = false;
							playersField2.fillArray(1);
							playersField2.clear();
							playersField2.setPlacesChecked(0);
							helperThree = true;
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "The game has already begun!");
				}
			}
		}
		else if(name.equals("end"))
		{
			if(difficulty == 3)
			{
				if(!endP1 && !endP2 && ((playersField.getShipFour() + playersField.getShipThree() + playersField.getShipTwo() + playersField.getShipOne()) == 0) && !game)
				{
					endP1 = true;
					playersField2.unblockField();
					if(playersField.getRandomized())
					{
						playersField.testMethod(2);
					}
					else
					{
						playersField.fillArray(2);
					}
					playersField2.unblockButtons();
				}
				else if(endP1 && !endP2 && ((playersField2.getShipFour() + playersField2.getShipThree() + playersField2.getShipTwo() + playersField2.getShipOne()) == 0) && !game)
				{
					endP2 = true;
					playersField.unblockField();
					if(playersField2.getRandomized())
					{
						playersField2.testMethod(2);
					}
					else
					{
						playersField2.fillArray(2);
					}
					playersField.unblockButtons();
				}

				else if(game)
				{
					if(endP1 && !helper)
					{
						playersField.blockField(false);
						//playersField2.unblockField();
						playersField.setShotsFired(false);
						playersField.setComputersTurn(false);
						playersField.setComputersTurn(true);
						endP1 = true;
					}
					else if(endP2 && !helper)
					{
						playersField2.blockField(false);
						//playersField.unblockField();
						playersField2.setShotsFired(false);
						playersField2.setComputersTurn(false);
						playersField2.setComputersTurn(true);
						endP2 = true;
					}
				}
			}
		}
	}

	private void updatePlayersLabels(PlayersField playersField, String name)
	{
		if(name.equals("Position"))
		{
			playersField.setHorizontal(!playersField.isHorizontal());
			if(playersField.isHorizontal())
			{
				currentPosition.setText("Postion: horizontal");
			}
			else
			{
				currentPosition.setText("Postion: vertical");
			}
		}
		else if(name.equals("Ship 4x1"))
		{
			if(playersField.getShipFour() <= 0)
			{
				JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
			}
			else
			{
				playersField.setShipsLength(4);
				currentShip.setText("Current ship: 4x1");
			}
		}
		else if(name.equals("Ship 3x1"))
		{
			if(playersField.getShipThree() <= 0)
			{
				JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
			}
			else
			{
				currentShip.setText("Current ship: 3x1");
				playersField.setShipsLength(3);
			}
		}
		else if(name.equals("Ship 2x1"))
		{
			if(playersField.getShipTwo() <= 0)
			{
				JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
			}
			else
			{
				currentShip.setText("Current ship: 2x1");
				playersField.setShipsLength(2);
			}
		}
		else if(name.equals("Ship 1x1"))
		{
			if(playersField.getShipOne() <= 0)
			{
				JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
			}
			else
			{
				currentShip.setText("Current ship: 1x1");
				playersField.setShipsLength(1);
			}
		}
	}

	@Override
	public void run()
	{
		while(isRunning)
		{
			handleShooting();
			updateInfo();
		}
	}

	/**
	 * handles shooting
	 * <br>
	 * runs the ai main function
	 * <br>
	 * sets all used booleans accordingly to players or computers turn
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private synchronized void handleShooting() throws ArrayIndexOutOfBoundsException
	{
		if(difficulty < 3)
		{
			if(playersField.getDone())
			{
				if(helper)
				{
					if(!playersField.getRandomized())
					{
						playersField.fillArray(1);
					}
					enemysField.setPlayersTurn(true);
					helper = false;
				}
				if(playersField.getShotsFired())
				{
					game = true;
					playersField.setComputersTurn(false);
					playersField.setShotsFired(false);
					enemysField.setPlayersTurn(true);
				}
				if(enemysField.getShotsFired())
				{
					game = true;
					enemysField.setPlayersTurn(false);
					ai.manageEverything(playersField, enemysField);
				}
				if(playersField.getPlacesChecked() == 20)
				{
					playersField.incrementPlacesChecked();
					JOptionPane.showMessageDialog(this, "<html><body><div style=\"margin-left: 40px\">Defeat!</div><div style=\"margin-left: 30px\">Computer won!</div></body></html>");
					isRunning = false;
				}
				if(enemysField.getPlacesChecked() == 20)
				{
					enemysField.incrementPlacesChecked();
					JOptionPane.showMessageDialog(this, "<html><body><div style=\"margin-left: 40px\">Victory!</div><div style=\"margin-left: 30px\">You won!</div></body></html>");
					isRunning = false;
				}
			}
		}
		else if(difficulty == 3)
		{
			if(endP1 && endP2)
			{
				if(helper)
				{
					playersField.setComputersTurn(false);
					playersField2.setComputersTurn(true);
					playersField.setPVP(true);
					playersField2.setPVP(true);
					helper = false;
					game = true;
				}
			}
			if(playersField.getShotsFired())
			{
				System.out.println("30");
				playersField.setComputersTurn(false);
				playersField.setShotsFired(false);
				endP1 = false;
				endP2 = true;
			}
			if(playersField2.getShotsFired())
			{
				System.out.println("31");
				playersField2.setComputersTurn(false);
				playersField2.setShotsFired(false);
				endP1 = true;
				endP2 = false;
			}
			if(playersField.getPlacesChecked() == 20)
			{
				playersField.incrementPlacesChecked();
				JOptionPane.showMessageDialog(this,
						"<html><body><div style=\"margin-left: 40px\">Defeat!</div><div style=\"margin-left: 30px\">Computer won!</div></body></html>");
				isRunning = false;
			}
		}
	}

	private synchronized void updateInfo()
	{
		if(difficulty < 3)
		{
			updatePlayersInfo(playersField);
		}

		if(difficulty == 3)
		{
			if(!endP1)
			{
				updatePlayersInfo(playersField);
			}
			else if(!endP2 && endP1)
			{
				updatePlayersInfo(playersField2);
			}
		}
	}

	private void updatePlayersInfo(PlayersField playersField)
	{
		if(playersField.isHorizontal())
		{
			if(playersField.getShipsLength() == 4)
			{
				shipsPosition.setDisabledIcon(horizontalFour);
			}
			if(playersField.getShipsLength() == 3)
			{
				shipsPosition.setDisabledIcon(horizontalThree);
			}
			if(playersField.getShipsLength() == 2)
			{
				shipsPosition.setDisabledIcon(horizontalTwo);
			}
			if(playersField.getShipsLength() == 1)
			{
				shipsPosition.setDisabledIcon(horizontalOne);
			}
		}
		else
		{
			if(playersField.getShipsLength() == 4)
			{
				shipsPosition.setDisabledIcon(verticalFour);
			}
			if(playersField.getShipsLength() == 3)
			{
				shipsPosition.setDisabledIcon(verticalThree);
			}
			if(playersField.getShipsLength() == 2)
			{
				shipsPosition.setDisabledIcon(verticalTwo);
			}
			if(playersField.getShipsLength() == 1)
			{
				shipsPosition.setDisabledIcon(horizontalOne);
			}
		}
		numberFour.setText("ships left: " + String.valueOf(playersField.getShipFour()));
		numberThree.setText("ships left: " + String.valueOf(playersField.getShipThree()));
		numberTwo.setText("ships left: " + String.valueOf(playersField.getShipTwo()));
		numberOne.setText("ships left: " + String.valueOf(playersField.getShipOne()));
	}
}