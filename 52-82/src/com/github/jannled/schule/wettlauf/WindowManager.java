package com.github.jannled.schule.wettlauf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class WindowManager implements ActionListener
{
	public static WindowManager w = new WindowManager();
	JFrame window;
	JPanel panel;
	JTextField lauferZahl;
	JTextField[] namen;
	JButton confirm;
	Wettlauf wettlauf;
	
	public WindowManager()
	{
		window = new JFrame("Wettrennen");
		panel = new JPanel(new GridLayout(0, 1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(panel);
		
		lauferZahl = new JTextField("8");
		lauferZahl.setBorder(new TitledBorder("Zahl der Laufer"));
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		panel.add(lauferZahl);
		panel.add(confirm);
		
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		
	}
	
	public void setLaufer(Laufer[] laufer)
	{
		panel.removeAll();
		namen = new JTextField[laufer.length];
		for(int i=0; i<laufer.length; i++)
		{
			namen[i] = new JTextField("Läufer " + i+1);
			panel.add(namen[i]);
		}
		panel.add(confirm);
		window.revalidate();
		window.repaint();
		window.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==confirm)
		{
			if(wettlauf==null)
			{
				int anzahl = Integer.parseInt(lauferZahl.getText());
				System.out.println("Erstelle neues Wettrennen mit " + anzahl);
				wettlauf = new Wettlauf(anzahl);
				System.out.println("Dat wars");
			}
			else
			{
				String[] lauferNamen = new String[namen.length];
				for(int i=0; i<namen.length; i++)
				{
					lauferNamen[i] = namen[i].getText();
				}
				wettlauf.anDenStart(lauferNamen);
			}
		}
	}
}
