package com.github.jannled.hanoi;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Main 
{
	JFrame frame = new JFrame("Die Türme von Hanoi");
	Turm[] turm = {new Turm(), new Turm(), new Turm()};
	
	public Main()
	{
		frame.setLayout(new GridLayout(1, 0));
		frame.setVisible(true);
		spawnKlotze(3);
		
		frame.pack();
		frame.revalidate();
		frame.repaint();
	}
	
	public void spawnKlotze(int amount)
	{
		for(Turm t : turm)
		{
			frame.add(t);
		}
		
		for(int i=0; i<amount; i++)
		{
			Klotz k = new Klotz(i+1);
			frame.add(k);
			turm[0].add(k);
		}
		for(Turm t : turm)
		{
			t.repaint();
		}
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}

}
