package com.grzesiek.statki.fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EnemysField extends Field implements ActionListener
{
	private boolean playersTurn;

	public EnemysField()
	{
		playersTurn = false;
		for(int k = 0; k < field.length; k++)
		{
			for(int l = 0; l < field.length; l++)
			{
				field[k][l].addActionListener(this);
			}
		}
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
		fillArray(2);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int k = Integer.parseInt(((JButton) e.getSource()).getName());
		int l = Integer.parseInt(e.getActionCommand());

		if(playersTurn)
		{
			shoot(k, l);
			shotsFired = true;
		}
		else
		{
			JOptionPane
					.showMessageDialog(this, "<html><body><div style=\"margin-left: 20px\">It's not your turn yet!</div></body></html>");
		}
	}

	public synchronized void setPlayersTurn(boolean change)
	{
		playersTurn = change;
	}

	public synchronized boolean getPlayersTurn()
	{
		return playersTurn;
	}
}