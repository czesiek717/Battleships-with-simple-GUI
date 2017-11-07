package com.grzesiek.statki.fields;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Field extends JPanel
{
	protected JButton[][] field;

	protected boolean[][] placedShips;

	protected boolean horizontal = true;
	protected boolean shotsFired = false;

	protected int shipsLength;
	protected int shipFour = 1;
	protected int shipThree = 2;
	protected int shipTwo = 3;
	protected int shipOne = 4;
	protected int placesChecked = 0;
	protected int draw;
	protected int row;
	protected int column;

	private int min;
	private int max;

	protected ImageIcon missed;
	protected ImageIcon hit;
	protected ImageIcon ship;
	protected ImageIcon empty;

	public Field()
	{
		setField(new JButton[10][10]);
		missed = new ImageIcon(Field.class.getResource("/missed.png"));
		hit = new ImageIcon(Field.class.getResource("/hit.png"));
		ship = new ImageIcon(Field.class.getResource("/ship.png"));
		empty = new ImageIcon(Field.class.getResource("/empty.png"));
		placedShips = new boolean[10][10];
		setVisible(true);
		setLayout(new GridLayout(10, 10));

		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field.length; j++)
			{
				field[i][j] = new JButton();
				field[i][j].setName(String.valueOf(i));
				field[i][j].setActionCommand(String.valueOf(j));
				field[i][j].setIcon(empty);
				field[i][j].setDisabledIcon(ship);
				add(field[i][j]);
			}
		}
		shipsLength = 1;
	}

	protected void putShipHorizontally(int i, int j)
	{
		if(j <= field.length - shipsLength)
		{
			for(int k = 0; k < shipsLength; k++)
			{
				field[i][j + k].setEnabled(false);
			}
		}
	}

	protected void putShipVertically(int i, int j)
	{
		if(i <= field.length - shipsLength)
		{
			for(int k = 0; k < shipsLength; k++)
			{
				field[i + k][j].setEnabled(false);
			}
		}
	}

	/**
	 * checks if at given location ship can be placed horizontally
	 * @param i - number of row
	 * @param j - number of col
	 * @return true if possible
	 */
	protected boolean checkForHorizontalSpace(int i, int j)
	{
		int helperOne = -1;
		int helperTwo = 1;

		if(i == 0)
		{
			helperOne = 0;
			helperTwo = 1;
		}
		else if(i > 0 && i < 9)
		{
			helperOne = -1;
			helperTwo = 1;
		}
		else if(i == 9)
		{
			helperOne = -1;
			helperTwo = 0;
		}
		else
		{
			return false;
		}

		if(j > 0 && j < field.length - shipsLength)
		{
			for(int k = helperOne; k <= helperTwo; k++)
			{
				for(int l = -1; l < shipsLength + 1; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else if(j == 0)
		{
			for(int k = helperOne; k <= helperTwo; k++)
			{
				for(int l = 0; l < shipsLength + 1; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else if(j == field.length - shipsLength)
		{
			for(int k = helperOne; k <= helperTwo; k++)
			{
				for(int l = -1; l < shipsLength; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * checks if at given location can ship be placed vertically
	 * @param i - number of row
	 * @param j - number of col
	 * @return true if possible
	 */
	protected boolean checkForVerticalSpace(int i, int j)
	{
		int helperOne = -1;
		int helperTwo = 1;

		if(j == 0)
		{
			helperOne = 0;
			helperTwo = 1;
		}
		else if(j > 0 && j < 9)
		{
			helperOne = -1;
			helperTwo = 1;
		}
		else if(j == 9)
		{
			helperOne = -1;
			helperTwo = 0;
		}
		else
		{
			return false;
		}

		if(i > 0 && i < field.length - shipsLength)
		{
			for(int k = -1; k < shipsLength + 1; k++)
			{
				for(int l = helperOne; l <= helperTwo; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else if(i == 0)
		{
			for(int k = 0; k < shipsLength + 1; k++)
			{
				for(int l = helperOne; l <= helperTwo; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else if(i == field.length - shipsLength)
		{
			for(int k = -1; k < shipsLength; k++)
			{
				for(int l = helperOne; l <= helperTwo; l++)
				{
					if(!field[i + k][j + l].isEnabled())
					{
						return false;
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * checks if ship of a given length is available
	 * @param shipsLength - ships to check
	 * @return true if possible
	 */
	protected boolean isShipAvailable(int shipsLength)
	{
		if(shipsLength == 4)
		{
			if(shipFour > 0) return true;
			else return false;
		}
		else if(shipsLength == 3)
		{
			if(shipThree > 0) return true;
			else return false;
		}
		else if(shipsLength == 2)
		{
			if(shipTwo > 0) return true;
			else return false;
		}
		else if(shipsLength == 1)
		{
			if(shipOne > 0) return true;
			else return false;
		}
		else
		{
			return false;
		}
	}

	/**
	 * subtracts ship of given length
	 * @param shipsLength - ship to subtract
	 */
	protected void substractShip(int shipsLength)
	{
		if(shipsLength == 4)
		{
			shipFour -= 1;
		}
		else if(shipsLength == 3)
		{
			shipThree -= 1;
		}
		else if(shipsLength == 2)
		{
			shipTwo -= 1;
		}
		else if(shipsLength == 1)
		{
			shipOne -= 1;
		}
	}

	/**
	 * after placing all ships fills array
	 * <br>so game knows where ships have been placed
	 */
	public void fillArray(int change)
	{
		for(int k = 0; k < placedShips.length; k++)
		{
			for(int l = 0; l < placedShips.length; l++)
			{
				if(field[k][l].isEnabled())
				{
					placedShips[k][l] = true;
					if(change == 1)
					{
						//field[k][l].setIcon(ship);
					}
					else
					{
						field[k][l].setIcon(empty);
					}
				}
				else
				{
					placedShips[k][l] = false;
					if(change == 1)
					{
						field[k][l].setIcon(ship);
					}
					else
					{
						field[k][l].setIcon(empty);
					}
				}
				if(change != 3)
				{
					field[k][l].setEnabled(true);
				}
			}
		}
	}
	
	public void testMethod(int change)
	{
		for(int k = 0; k < placedShips.length; k++)
		{
			for(int l = 0; l < placedShips.length; l++)
			{
				if(placedShips[k][l])
				{
					if(change == 1)
					{
						field[k][l].setIcon(ship);
					}
					else
					{
						field[k][l].setIcon(empty);
					}
				}
				else
				{
					if(change == 1)
					{
						field[k][l].setIcon(ship);
					}
					else
					{
						field[k][l].setIcon(empty);
					}
				}
				field[k][l].setEnabled(true);
			}
		}
	}

	/**
	 * shoots at given location
	 * @param k - number of row
	 * @param l - number of col
	 */
	public synchronized void shoot(int k, int l)
	{
		if(placesChecked < 20)
		{
			if(placedShips[k][l])
			{
				field[k][l].setIcon(missed);
				field[k][l].setDisabledIcon(missed);
			}
			else
			{
				field[k][l].setIcon(hit);
				field[k][l].setDisabledIcon(hit);
				placesChecked++;
			}
			field[k][l].setEnabled(false);
		}
	}

	protected void drawPosition()
	{
		min = 0;
		max = 1;
		draw = min + (int) (Math.random() * ((max - min) + 1));
	}

	public void drawShipsPosition()
	{
		min = 0;
		max = 9;
		row = min + (int) (Math.random() * ((max - min) + 1));
		column = min + (int) (Math.random() * ((max - min) + 1));
	}

	protected void drawShipsLength()
	{
		min = 0;
		max = 4;
		shipsLength = min + (int) (Math.random() * ((max - min) + 1));
	}

	public void setShotsFired(boolean change)
	{
		shotsFired = change;
	}

	public void setField(JButton[][] field)
	{
		this.field = field;
	}

	public void setPlacesChecked(int change)
	{
		placesChecked = change;
	}

	public synchronized void incrementPlacesChecked()
	{
		placesChecked++;
	}

	public boolean getShotsFired()
	{
		return shotsFired;
	}

	public synchronized int getPlacesChecked()
	{
		return placesChecked;
	}

	public synchronized ImageIcon getHitIcon()
	{
		return hit;
	}

	public synchronized ImageIcon getMissedIcon()
	{
		return missed;
	}

	public synchronized int getShipsLength()
	{
		return shipsLength;
	}

	public synchronized int getShipFour()
	{
		return shipFour;
	}

	public synchronized int getShipThree()
	{
		return shipThree;
	}

	public synchronized int getShipTwo()
	{
		return shipTwo;
	}

	public synchronized int getShipOne()
	{
		return shipOne;
	}

	public JButton[][] getField()
	{
		return field;
	}

	public boolean getPlacedShips(int i, int j)
	{
		return placedShips[i][j];
	}
}