package com.github.jannled.hanoi;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	Turm[] turme;
	
	public Canvas(Turm[] turme)
	{
		this.turme = turme;
		setLayout(new GridLayout(1,0));
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		
	}
}
