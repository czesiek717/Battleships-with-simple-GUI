package com.grzesiek.statki.menus;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Menu extends JFrame implements ActionListener
{
	private final int WIDTH = 300;
	private final int HEIGHT = 350;

	private Dimension dimension = new Dimension(WIDTH, HEIGHT);

	protected JButton buttonOne;
	protected JButton buttonTwo;
	protected JButton buttonThree;

	protected JLabel labelOne;

	public Menu()
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

		buttonOne = new JButton();
		buttonTwo = new JButton();
		buttonThree = new JButton();
		labelOne = new JLabel();

		add(buttonOne);
		add(buttonTwo);
		add(buttonThree);
		add(labelOne);

		buttonOne.setBounds(50, 30, 200, 50);
		buttonTwo.setBounds(50, 130, 200, 50);
		buttonThree.setBounds(50, 230, 200, 50);
		labelOne.setBounds(85, 10, 200, 14);

		buttonOne.addActionListener(this);
		buttonTwo.addActionListener(this);
		buttonThree.addActionListener(this);
	}
}