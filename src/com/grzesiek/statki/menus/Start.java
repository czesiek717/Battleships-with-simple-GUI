package com.grzesiek.statki.menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class Start extends Menu
{
	private String gameVer = "v1.7 by Grzegorz Ścigaj";
	
	public Start()
	{
		buttonOne.setText("New Game");
		buttonTwo.setText("Load Game");
		buttonThree.setText("About");
		labelOne.setText(gameVer);

		buttonOne.setActionCommand("New Game");
		buttonTwo.setActionCommand("Load Game");
		buttonThree.setActionCommand("About");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		if(button.equals("New Game"))
		{
			dispose();
			new ChooseGameType();
		}
		
		if(button.equals("Load Game"))
		{

		}
		
		if(button.equals("About"))
		{

		}
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Start frame = new Start();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}