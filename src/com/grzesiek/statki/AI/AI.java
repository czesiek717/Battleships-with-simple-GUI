package com.grzesiek.statki.AI;

import java.util.Arrays;

import com.grzesiek.statki.fields.EnemysField;
import com.grzesiek.statki.fields.PlayersField;

public class AI
{
	/**
	 * bot difficulty
	 * <br>1 - easy
	 * <br>2 - medium
	 * <br>3 - hard
	 */
	private int botDifficulty;

	private boolean helperOne;
	private boolean helperTwo;
	private boolean helperThree;
	private boolean helperFour;
	

	private boolean missedOne;
	private boolean missedTwo;

	private int[][] placesFired;

	private int hitCount;
	/**
	 * 1 - horizontal 
	 * <br>2 - vertical
	 */
	private int position;
	private int shipsLength;
	private int shipFour;
	private int shipThree;
	private int shipTwo;
	private int shipOne;
	private int helperNegative;
	private int helperPositive;
	private int row;
	private int column;
	private int rowHelper;
	private int columnHelper;
	private int loopTracker;

	public AI(int difficulty)
	{
		this.botDifficulty = difficulty;
		hitCount = 0;
		position = 0;
		shipsLength = 0;
		helperNegative = -4;
		helperPositive = 4;
		row = 0;
		column = 0;
		rowHelper = 0;
		columnHelper = 0;
		loopTracker = 0;

		helperOne = true;
		helperTwo = true;
		helperThree = true;
		helperFour = true;
		missedOne = false;
		missedTwo = false;

		if(botDifficulty == 2) //only hard bot does that
		{
			placesFired = new int[10][10];
			for(int i = 0; i < placesFired.length; i++)
			{
				Arrays.fill(placesFired[i], 0);
			}

			shipFour = 1;
			shipThree = 2;
			shipTwo = 3;
			shipOne = 4;
		}
	}

	/**
	 * manages all methods included in ai class
	 * <br>decides where to shoot, substracts ships
	 * @param playersField - players field
	 * @param enemysField - enemys field
	 */
	public void manageEverything(PlayersField playersField, EnemysField enemysField)
	{
		int min = 0;
		int max = 0;

		if(botDifficulty >= 0) //all bots do that
		{
			if(hitCount == 0)
			{
				lookingForShips();
			}
			else
			{
				if(botDifficulty >= 1) //only medium and hard bots do that
				{
					if(hitCount == 1)
					{
						if(botDifficulty == 2)
						{
							if(shipFour + shipThree + shipTwo != 0)
							{
								shootingAround(playersField);
							}
							else
							{
								hitCount = 0;
							}
						}
						else
						{
							shootingAround(playersField);
						}
					}

					if(hitCount >= 2)
					{
						destroyingShip(playersField);
					}
				}
			}
			if(playersField.getField()[row + rowHelper][column + columnHelper].isEnabled())
			{
				if(botDifficulty == 2)
				{
					if(placesFired[row + rowHelper][column + columnHelper] == 0)
					{
						manageShooting(playersField, enemysField);
					}
					else
					{
						placesFired[row + rowHelper][column + columnHelper] = 2;
					}
				}
				if(botDifficulty < 2)
				{
					manageShooting(playersField, enemysField);
				}
			}
		}
	}

	/**
	 * shoots at random location looking for ship
	 * <br>resets needed parameters
	 */
	private void lookingForShips()
	{
		rowHelper = 0;
		columnHelper = 0;
		loopTracker = 0;
		helperOne = true;
		helperTwo = true;
		helperThree = true;
		helperFour = true;
		missedOne = false;
		missedTwo = false;

		if(botDifficulty == 2)
		{
			blockSpacesAround();
			subtractShips(shipsLength);
		}

		shipsLength = 0;
		position = 0;

		int min = 0;
		int max = 9;

		row = min + (int) (Math.random() * ((max - min) + 1));
		column = min + (int) (Math.random() * ((max - min) + 1));
	}

	/**
	 * in hard difficulty blocks spaces around ships to indicate that there cant be a ship placed
	 */
	private void blockSpacesAround()
	{
		if(position == 0)
		{
			if(placesFired[row + rowHelper][column + columnHelper] == 1)
			{
				//these two loops block spaces around given ship
				for(int i = -1; i < 2; i++)
				{
					for(int j = -1; j < 2; j++)
					{
						if((row + rowHelper + i) >= 0 && (row + rowHelper + i) <= 9 && (column + columnHelper + j) >= 0 && (column + columnHelper + j) <= 9)
						{
							placesFired[row + rowHelper + i][column + columnHelper + j] = 2;
						}
					}
				}
			}
		}
		if(position == 1 || position == 2)
		{
			//these loops look for any ships parts 
			for(int i = 0; i < placesFired.length; i++)
			{
				for(int j = 0; j < placesFired.length; j++)
				{
					if(placesFired[i][j] == 1)
					{
						//these loops block spaces around them
						for(int c = -4; c <= 4; c++)
						{
							for(int d = -4; d <= 4; d++)
							{
								if((i + c) >= 0 && (i + c) <= 9 && (j + d) >= 0 && (j + d) <= 9)
								{
									if(placesFired[i + c][j + d] == 1)
									{
										for(int e = -1; e <= 1; e++)
										{
											for(int f = -1; f <= 1; f++)
											{
												if((i + c + e) >= 0 && (i + c + e) <= 9 && (j + d + f) >= 0 && (j + d + f) <= 9)
												{
													if(!(placesFired[i + c + e][j + d + f] == 1))
													{
														placesFired[i + c + e][j + d + f] = 2;
													}
												}
											}
										}
									}
								}
							}
						}
						placesFired[i][j] = 2;
					}
				}
			}
		}
	}

	/**
	 * subtracts ship of given length
	 * <br> and manages the loop helpers
	 * @param length - ships length
	 */
	private void subtractShips(int length)
	{
		if(length == 1)
		{
			if(shipOne > 0)
			{
				shipOne--;
			}
		}
		if(length == 2)
		{
			if(shipTwo > 0)
			{
				shipTwo--;
			}
		}
		if(length == 3)
		{
			if(shipThree > 0)
			{
				shipThree--;
			}
		}
		if(length == 4)
		{
			if(shipFour > 0)
			{
				shipFour--;
			}
		}

		if(shipFour <= 0)
		{
			helperPositive = 3;
			helperNegative = -3;

			if(shipThree <= 0)
			{
				helperPositive = 2;
				helperNegative = -2;

				if(shipTwo <= 0)
				{
					helperPositive = 1;
					helperNegative = 1;
				}
			}
		}
	}

	/**
	 * is called when a ship has been found
	 * <br>shoots around the place to find other parts of given ship
	 * <br> max and min parameters are used to randomize the location of next try
	 * @param playersField - playersField to shoot at
	 */
	private void shootingAround(PlayersField playersField)
	{
		if(shipsLength == 1)
		{
			if(!helperOne && !helperTwo && !helperThree && !helperFour)
			{
				hitCount = 0;
			}

			int min = 1;
			int max = 4;
			int newPlaceToCheck = min + (int) (Math.random() * ((max - min) + 1));
			switch(newPlaceToCheck)
			{
			case 1:
				if(helperOne)
				{
					helperOne = false;
					if(((row - 1) >= 0 && (row - 1) <= 9))
					{
						if(playersField.getField()[row - 1][column].isEnabled())
						{
							rowHelper = -1;
							columnHelper = 0;
							if(playersField.getField()[row - 1][column].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								position = 2;
							}
						}
						else
						{
							if(playersField.getField()[row - 1][column].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								hitCount++;
							}
						}
					}
				}
				break;
			case 2:
				if(helperTwo)
				{
					helperTwo = false;
					if(((row + 1) >= 0 && (row + 1) <= 9))
					{
						if(playersField.getField()[row + 1][column].isEnabled())
						{
							rowHelper = 1;
							columnHelper = 0;
							if(playersField.getField()[row + 1][column].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								position = 2;
							}
						}
						else
						{
							if(playersField.getField()[row + 1][column].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								hitCount++;
							}
						}
					}
				}
				break;
			case 3:
				if(helperThree)
				{
					helperThree = false;
					if(((column - 1) >= 0 && (column - 1) <= 9))
					{
						if(playersField.getField()[row][column - 1].isEnabled())
						{
							columnHelper = -1;
							rowHelper = 0;
							if(playersField.getField()[row][column - 1].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								position = 1;
							}
						}
						else
						{
							if(playersField.getField()[row][column - 1].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								hitCount++;
							}
						}
					}
				}
				break;
			case 4:
				if(helperFour)
				{
					helperFour = false;
					if(((column + 1) >= 0 && (column + 1) <= 9))
					{
						if(playersField.getField()[row][column + 1].isEnabled())
						{
							columnHelper = 1;
							rowHelper = 0;
							if(playersField.getField()[row][column + 1].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								position = 1;
							}
						}
						else
						{
							if(playersField.getField()[row][column + 1].getDisabledIcon().equals(playersField.getHitIcon()))
							{
								hitCount++;
							}
						}
					}
				}
				break;
			}
		}
	}

	/**
	 * looks for ship and destroys it
	 * @param playersField
	 */
	private void destroyingShip(PlayersField playersField)
	{
		if(shipsLength <= 4)
		{
			if(shipsLength == 4)
			{
				hitCount = 0;
				return;
			}
			if(botDifficulty == 2)
			{
				if(shipsLength == 3 && shipFour == 0)
				{
					hitCount = 0;
					return;
				}
				if(shipsLength == 2 && shipThree == 0 && shipFour == 0)
				{
					hitCount = 0;
					return;
				}
			}

			if(rowHelper == -1 || rowHelper == 1)
			{
				position = 2; // ship is in vertical position
			}
			if(columnHelper == -1 || columnHelper == 1)
			{
				position = 1; // ship is in horizontal position
			}

			if(!missedOne)
			{
				for(int i = 0; i >= helperNegative; i--)
				{
					if(position == 1)
					{
						if(((column + i) >= 0 && (column + i) <= 9))
						{
							if(playersField.getField()[row][column + i].isEnabled())
							{
								columnHelper = i;
								break;
							}
							else
							{
								if(playersField.getField()[row][column + i].getDisabledIcon().equals(playersField.getMissedIcon()))
								{
									missedOne = true;
									break;
								}
								else
								{
									if(botDifficulty == 2)
									{
										if(placesFired[row][column + i] == 2)
										{
											missedOne = true;
											break;
										}
									}
								}
							}
						}
						else
						{
							missedOne = true;
							break;
						}
					}

					if(position == 2)
					{
						if(((row + i) >= 0 && (row + i) <= 9))
						{
							if(playersField.getField()[row + i][column].isEnabled())
							{
								rowHelper = i;
								break;
							}
							else
							{
								if(playersField.getField()[row + i][column].getDisabledIcon().equals(playersField.getMissedIcon()))
								{
									missedOne = true;
									break;
								}
								else
								{
									if(botDifficulty == 2)
									{
										if(placesFired[row + i][column] == 2)
										{
											missedOne = true;
											break;
										}
									}
								}
							}
						}
						else
						{
							missedOne = true;
							break;
						}
					}
				}
			}

			if(!missedTwo)
			{
				for(int i = 0; i <= helperPositive; i++)
				{
					if(position == 1)
					{
						if(((column + i) >= 0 && (column + i) <= 9))
						{
							if(playersField.getField()[row][column + i].isEnabled())
							{
								columnHelper = i;
								break;
							}
							else
							{
								if(playersField.getField()[row][column + i].getDisabledIcon().equals(playersField.getMissedIcon()))
								{
									missedTwo = true;
									break;
								}
								else
								{
									if(botDifficulty == 2)
									{
										if(placesFired[row][column + i] == 2)
										{
											missedTwo = true;
											break;
										}
									}
								}
							}
						}
						else
						{
							missedTwo = true;
							break;
						}
					}

					if(position == 2)
					{
						if(((row + i) >= 0 && (row + i) <= 9))
						{
							if(playersField.getField()[row + i][column].isEnabled())
							{
								rowHelper = i;
								break;
							}
							else
							{
								if(playersField.getField()[row + i][column].getDisabledIcon().equals(playersField.getMissedIcon()))
								{
									missedTwo = true;
									break;
								}
								else
								{
									if(botDifficulty == 2)
									{
										if(placesFired[row + i][column] == 2)
										{
											missedTwo = true;
											break;
										}
									}
								}
							}
						}
						else
						{
							missedTwo = true;
							break;
						}
					}
				}
			}

			if(missedOne && missedTwo)
			{
				if(loopTracker == 8)
				{
					hitCount = 0;
					loopTracker = 0;
					missedOne = false;
					missedTwo = false;
				}
				loopTracker++;
			}
		}
	}

	/**
	 * manages shooting, slows thread for 0.5s to not make instant
	 * <br>decision so player has some time to notice the action
	 * @param playersField - playersField
	 * @param enemysField - enemysField
	 */
	private void manageShooting(PlayersField playersField, EnemysField enemysField)
	{
		try
		{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

		enemysField.setShotsFired(false);
		enemysField.drawShipsPosition();
		playersField.setComputersTurn(true);
		if(botDifficulty == 2)
		{
			if(placesFired[row + rowHelper][column + columnHelper] == 0)
			{
				playersField.shoot(row + rowHelper, column + columnHelper);
			}
		}
		else
		{
			playersField.shoot(row + rowHelper, column + columnHelper);
		}
		if(botDifficulty >= 1)
		{
			if(playersField.getField()[row + rowHelper][column + columnHelper].getDisabledIcon().equals(playersField.getHitIcon()))
			{
				hitCount++;
				shipsLength++;
				if(botDifficulty == 2)
				{
					placesFired[row + rowHelper][column + columnHelper] = 1;
				}
			}
			else
			{
				if(botDifficulty == 2)
				{
					placesFired[row + rowHelper][column + columnHelper] = 2;
				}
				if(shipsLength >= 2)
				{
					rowHelper = 0;
					columnHelper = 0;
				}
			}
		}
		playersField.setShotsFired(true);
	}
}