package com.grzesiek.statki.menus;

import java.awt.event.ActionEvent;

import com.grzesiek.statki.Game;

public class ChooseGameType extends Menu
{
	private String text = "Choose game type:";
	
	public ChooseGameType()
	{
		buttonOne.setText("1v1 on the same machine");
		buttonTwo.setText("<html><center>1v1 through internet<b> connection</center></html>");
		buttonThree.setText("1v1 vs AI");
		labelOne.setText(text);
		
		buttonOne.setActionCommand("Machine");
		buttonTwo.setActionCommand("Internet");
		buttonThree.setActionCommand("AI");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		if(button.equals("Machine"))
		{
			/*dispose();
			new Game(3);*/
		}
		
		if(button.equals("Internet"))
		{

		}
		
		if(button.equals("AI"))
		{
			dispose();
			new ChooseAI();
		}
	}
}