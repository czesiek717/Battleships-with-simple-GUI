package com.grzesiek.statki.fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PlayersField extends Field implements ActionListener
{
	private boolean computersTurn;
	private boolean helper;
	private boolean done;
	private boolean randomized;
	private boolean pvp;

	public PlayersField()
	{
		computersTurn = false;
		done = false;
		randomized = false;
		pvp = false;
		helper = true;
		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field.length; j++)
			{
				field[i][j].addActionListener(this);
			}
		}

		for(int i = 0; i < placedShips.length; i++)
		{
			Arrays.fill(placedShips[i], true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int i = Integer.parseInt(((JButton) e.getSource()).getName());
		int j = Integer.parseInt(e.getActionCommand());

		if((shipFour + shipThree + shipTwo + shipOne) > 0)
		{
			if(shipsLength <= 4 && shipsLength > 0)
			{
				if(horizontal)
				{
					if(isShipAvailable(shipsLength))
					{
						if(checkForHorizontalSpace(i, j))
						{
							putShipHorizontally(i, j);
							substractShip(shipsLength);
							if((shipFour + shipThree + shipTwo + shipOne) == 0) done = true;
						}
						else
						{
							JOptionPane.showMessageDialog(this, "<html><body><center>Not enought space to put choosen ship there!</center></body></html>");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
					}
				}
				else
				{
					if(isShipAvailable(shipsLength))
					{
						if(checkForVerticalSpace(i, j))
						{
							putShipVertically(i, j);
							substractShip(shipsLength);
							if((shipFour + shipThree + shipTwo + shipOne) == 0) done = true;
						}
						else
						{
							JOptionPane.showMessageDialog(this, "<html><body><center>Not enought space to put choosen ship there!</center></body></html>");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "You have no ships of a given length left!");
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "An error occured!");
			}
		}
		else
		{
			if(helper && !pvp)
			{
				JOptionPane.showMessageDialog(this,
						"<html><body><div style=\"margin-left: 30px\">You have placed all ships!<br>It's time to fight!</div></body></html>");
				helper = false;
			}
			if(pvp)
			{
				if(computersTurn)
				{
					shoot(i, j);
					shotsFired = true;
				}
			}
		}

	}

	public void placeShipsRandomly()
	{
		randomized = true;
		while((shipFour + shipThree + shipTwo + shipOne) > 0)
		{
			drawPosition();
			drawShipsLength();
			drawShipsPosition();

			if(shipsLength <= 4 && shipsLength > 0)
			{
				if(draw == 0) // horizontal
				{
					if(isShipAvailable(shipsLength))
					{
						if(checkForHorizontalSpace(row, column))
						{
							putShipHorizontally(row, column);
							substractShip(shipsLength);
						}
					}
				}
				if(draw == 1) // vertical
				{
					if(isShipAvailable(shipsLength))
					{
						if(checkForVerticalSpace(row, column))
						{
							putShipVertically(row, column);
							substractShip(shipsLength);
						}
					}
				}
			}
		}
		done = true;
	}

	public void clear()
	{
		shipFour = 1;
		shipThree = 2;
		shipTwo = 3;
		shipOne = 4;
		randomized = false;
		for(int i = 0; i < field.length; i++)
		{
			Arrays.fill(placedShips[i], true);
			for(int j = 0; j < field.length; j++)
			{
				field[i][j].setEnabled(true);
				field[i][j].setIcon(empty);
				field[i][j].setDisabledIcon(ship);
			}
		}
	}

	public void placeProperIcons()
	{
		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field.length; j++)
			{
				if(placedShips[i][j])
				{
					field[i][j].setDisabledIcon(empty);
				}
				else
				{
					field[i][j].setDisabledIcon(ship);
				}
				field[i][j].setIcon(empty);
			}
		}
		fillArray(1);
	}

	public void blockField(boolean emptyField)
	{
		if(emptyField)
		{
			for(int i = 0; i < field.length; i++)
			{
				for(int j = 0; j < field.length; j++)
				{
					field[i][j].setEnabled(false);
					field[i][j].setDisabledIcon(empty);
				}
			}
		}
		else
		{
			for(int i = 0; i < field.length; i++)
			{
				for(int j = 0; j < field.length; j++)
				{
					if(field[i][j].getDisabledIcon().equals(hit) || field[i][j].getIcon().equals(hit))
					{
						field[i][j].setIcon(hit);
					}
					if(field[i][j].getDisabledIcon().equals(missed) || field[i][j].getIcon().equals(missed))
					{
						field[i][j].setIcon(missed);
					}
					field[i][j].setEnabled(true);
				}
			}
		}
	}

	public void unblockField()
	{
		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field.length; j++)
			{
				field[i][j].setEnabled(true);
				field[i][j].setDisabledIcon(ship);
				field[i][j].setIcon(empty);
				if(!placedShips[i][j] || !field[i][j].isEnabled())
				{
					field[i][j].setEnabled(false);
				}
				if(field[i][j].getDisabledIcon().equals(hit))
				{
					System.out.println("6");
				}
				if(field[i][j].getIcon().equals(hit))
				{
					System.out.println("7");
				}
				if(field[i][j].getDisabledIcon().equals(missed))
				{
					System.out.println("8");
				}
				if(field[i][j].getIcon().equals(missed))
				{
					System.out.println("9");
				}
			}
		}
	}

	public void unblockButtons()
	{
		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field.length; j++)
			{
				if(!placedShips[i][j])
				{
					field[i][j].setEnabled(true);
					field[i][j].setIcon(ship);
				}
			}
		}
	}

	public synchronized void setComputersTurn(boolean change)
	{
		computersTurn = change;
	}

	public void setShipsLength(int length)
	{
		this.shipsLength = length;
	}

	public void setHorizontal(boolean horizontal)
	{
		this.horizontal = horizontal;
	}

	public synchronized void setDone(boolean change)
	{
		done = change;
	}

	public void setPVP(boolean change)
	{
		pvp = change;
	}

	public boolean isHorizontal()
	{
		return horizontal;
	}

	public synchronized boolean getComputersTurn()
	{
		return computersTurn;
	}

	public synchronized boolean getDone()
	{
		return done;
	}

	public boolean getRandomized()
	{
		return randomized;
	}
}