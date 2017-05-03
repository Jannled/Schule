package com.github.jannled.hanoi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements ActionListener
{
	JFrame frame = new JFrame("Die Türme von Hanoi");
	JPanel root = new JPanel(new BorderLayout());
	Turm[] turm = {new Turm(), new Turm(), new Turm()};
	Canvas canvas = new Canvas(turm);
	
	JPanel buttons = new JPanel();
	JButton b1 = new JButton("Turm 1");
	JButton b2 = new JButton("Turm 2");
	JButton b3 = new JButton("Turm 3");
	
	Turm selected = null;
	
	public Main()
	{
		frame.add(root);
		root.add(canvas, BorderLayout.CENTER);
		root.add(buttons, BorderLayout.SOUTH);
		frame.setVisible(true);
		spawnKlotze(20);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		buttons.setLayout(new GridLayout(1, 0));
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		
		frame.pack();
	}
	
	public void spawnKlotze(int amount)
	{
		for(Turm t : turm)
		{
			canvas.add(t);
		}
		for(int i=0; i<amount; i++)
		{
			Klotz k = new Klotz(i+1);
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == b1)
		{
			if(selected==null)
			{
				selected = turm[0];
				b1.setEnabled(false);
			}
			else
			{
				turm[0].add(selected.takeKlotz());
				selected = null;
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
			}
		} 
		else if(e.getSource() == b2)
		{
			if(selected==null)
			{
				selected = turm[1];
				b2.setEnabled(false);
			}
			else
			{
				turm[1].add(selected.takeKlotz());
				selected = null;
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
			}
		}
		else if(e.getSource() == b3)
		{
			if(selected==null)
			{
				selected = turm[2];
				b3.setEnabled(false);
			}
			else
			{
				turm[2].add(selected.takeKlotz());
				selected = null;
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
			}
		}
		canvas.repaint();
	}
}
