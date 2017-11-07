package com.grzesiek.statki.menus;

import java.awt.event.ActionEvent;

import com.grzesiek.statki.Game;

public class ChooseAI extends Menu
{
	private String text = "Choose AI difficulty:";
	
	public ChooseAI()
	{
		buttonOne.setText("Easy");
		buttonTwo.setText("Medium");
		buttonThree.setText("Hard");
		labelOne.setText(text);
		
		buttonOne.setActionCommand("Easy");
		buttonTwo.setActionCommand("Medium");
		buttonThree.setActionCommand("Hard");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		if(button.equals("Easy"))
		{
			dispose();
			new Game(0);
		}
		
		if(button.equals("Medium"))
		{
			dispose();
			new Game(1);
		}
		
		if(button.equals("Hard"))
		{
			dispose();
			new Game(2);
		}
	}
}